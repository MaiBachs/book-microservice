import React, { useEffect, useState } from 'react';
import DefaultLayout from '../../defaultLayout/DefaultLayout';
import styles from './ReadingBookManagement.module.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { FaEdit } from "react-icons/fa";
import { MdDelete } from "react-icons/md";
import { BiDetail } from "react-icons/bi";
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import DetailReadingBook from '../../component/popupDetailReadingBook/DetailReadingBook';
import EditReadingBook from '../../component/popupEdit/EditReadingBook';

const cx = classNames.bind(styles);

function ReadingBookManagement() {
  const [open, setOpen] = useState(false);
  const [openEdit, setOpenEdit] = useState(false);
  const [bookSelected, setBookSelected] = useState({}); 
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const handleOpenEdit = () => setOpenEdit(true);
  const handleCloseEdit = () => setOpenEdit(false);

  function handleSelectBook(book){
    setBookSelected(book);
  }

  function handkeDelete(bookId){
    axios
      .get("http://localhost:9191/api/e-book-service/management/delete", {
        params: {
          bookId: bookId,
        },
    })
      .then((response) => {
        alert("Delete success");
        handleSearch();
      })
      .catch();
  }

  const [listBookSearch, setListBookSearch] = useState({
    page: 1,
    totalPage: 0,
    bookEntityList: [],
  });

  const [dataSearch, setDataSearch] = useState({
    bookName: "",
    bookCategory: "",
    bookType: null,
    bookAuthor: "",
    page: listBookSearch.page,
    size: 10
  })

  function handleInputSearch(event) {
    setDataSearch({
      ...dataSearch,
      [event.target.name]: event.target.value
    })
  }

  useEffect(() => {
    axios
      .post("http://localhost:9191/api/e-book-service/management/search-book-by-page", {
        ...dataSearch,
        page: listBookSearch.page
      })
      .then((response) => {
        setListBookSearch({
          ...listBookSearch,
          totalPage: response.data.data.totalPage,
          bookEntityList: [...response.data.data.bookEntityList],
        });
      })
      .catch();
  }, [listBookSearch.page])

  function handleSearch() {
    axios
      .post("http://localhost:9191/api/e-book-service/management/search-book-by-page", dataSearch)
      .then((response) => {
        setListBookSearch({
          ...listBookSearch,
          totalPage: response.data.data.totalPage,
          bookEntityList: [...response.data.data.bookEntityList],
        });
      })
      .catch();
  };

  let indexPage = [];
    for (let i = 1; i <= listBookSearch.totalPage; i++) {
        indexPage.push(i);
    };

  const handleChangePage = (index) => {
    if (index === 'previos') {
      setListBookSearch({
        ...listBookSearch,
        page: listBookSearch.page - 1,
      });
    } else if (index === 'next') {
      setListBookSearch({
        ...listBookSearch,
        page: listBookSearch.page + 1,
      });
    } else {
      setListBookSearch({
        ...listBookSearch,
        page: index,
      });
    }
  };

  return (
    <div className={cx('wrapper')}>
      <DefaultLayout>
      <DetailReadingBook className={cx("detail-popup")} clas open={open} handleOpen={handleOpen} handleClose={handleClose} dataDetail = {bookSelected} />
      <EditReadingBook className={cx("edit-popup")} clas open={openEdit} handleOpen={handleOpenEdit} handleClose={handleCloseEdit} dataEdit = {bookSelected} />
        <div className={cx("header-tab")}>
          <Link className={cx("reading-book-management")} to="/readingbookmanagement">
            Reading book management
          </Link>
          <Link className={cx("add-reading-book")} to="/addnewreadingbook" >
            Add new reading book
          </Link>
        </div>
        <div className={cx("grid-container")}>
          <div className={cx("grid-item")}>
            <label>Book_name</label>
          </div>
          <div className={cx("grid-item")}>
            <input name="bookName" type='text' value={dataSearch.bookName} onChange={(event) => { handleInputSearch(event) }}></input>
          </div>
          <div className={cx("grid-item")}></div>
          <div className={cx("grid-item")}></div>
          <div className={cx("grid-item")}>
            <label>Book_category</label>
          </div>
          <div className={cx("grid-item")}>
            <input name="bookCategory" type='text' value={dataSearch.bookCategory} onChange={(event) => { handleInputSearch(event) }}></input>
          </div>
          <div className={cx("grid-item")}><button className={cx('button-search')} onClick={handleSearch}>Search</button></div>
          <div className={cx("grid-item")}>
            <label>Book_author</label>
          </div>
          <div className={cx("grid-item")}>
            <input name="bookAuthor" type='text' value={dataSearch.bookAuthor} onChange={(event) => { handleInputSearch(event) }}></input>
          </div>
          <div className={cx("grid-item")}>
          </div>
          <div className={cx("grid-item")}></div>
          <div className={cx("grid-item")}>
            <label>Book_option</label>
          </div>
          <div className={cx("grid-item")}>
            <select name="bookType" onChange={(event) => { handleInputSearch(event) }}>
              <option value="" >All</option>
              <option value="1" >Free reader waka</option>
              <option value="2" >For member waka</option>
              <option value="3" >Pay fee</option>
            </select>
          </div>
          <div className={cx("grid-item")}></div>
        </div>
        <div className={cx("result-search")}>
          <table className={cx("books")}>
            <tbody>
              <tr>
                <th>Stt</th>
                <th>Book name</th>
                <th>Book category</th>
                <th>Book option</th>
                <th>Book author</th>
                <th>View</th>
                <th>Show file pdf</th>
                <th className={cx("action")}>Detail</th>
                <th className={cx("action")}>Edit</th>
                <th className={cx("action")}>Delete</th>
              </tr>
              {listBookSearch.bookEntityList.map((book, i) => (
                <>
                <tr key={i}>
                  <td>{i + 1}</td>
                  <td>{book.bookName}</td>
                  <td>{book.bookCategory}</td>
                  <td>
                    {book.bookType == 1 && (<span>Free reader waka</span>)}
                    {book.bookType == 2 && (<span>For member waka</span>)}
                    {book.bookType == 3 && (<span>Pay fee</span>)}
                  </td>
                  <td>{book.bookAuthor}</td>
                  <td>{book.view}</td>
                  <td><Link href='#' to="/pdffileview" state={book} >{book.preview}</Link></td>
                  <td className={cx("action")}><BiDetail className={cx("detail")} onClick={()=>{handleOpen(); handleSelectBook(book)}} handleClose={handleClose} /></td>
                  <td className={cx("action")}><FaEdit className={cx("edit")} onClick={()=>{handleOpenEdit(); handleSelectBook(book)}} handleClose={handleCloseEdit} /></td>
                  <td className={cx("action")}><MdDelete className={cx("delete")} onClick={()=>{handkeDelete(book.id)}}/></td>
                </tr>
                </>
              ))}
            </tbody>
          </table>
        </div>
        <div className="pagination-box">
          <nav aria-label="pagination " className="pagin mt-3">
            <ul class="pagination pagination-sm justify-content-end mr-5">
              {listBookSearch.page <= 1 ? (
                <li class="page-item disabled" key={'previos'}>
                  <button class="page-link" tabindex="-1">
                    {'<'}
                  </button>
                </li>
              ) : (
                <li
                  class="page-item"
                  key={'previos'}
                  onClick={() => {
                    handleChangePage('previos');
                  }}
                >
                  <button class="page-link" tabindex="-1">
                    {'<'}
                  </button>
                </li>
              )}
              {indexPage.map((index) => {
                return (
                  <>
                    {index === listBookSearch.page ? (
                      <li key={index} class="page-item active ms-2">
                        <button class="page-link" style={{backgroundColor: 'beige', color: 'black'}}>{index}</button>
                      </li>
                    ) : (
                      <li
                        key={index}
                        class="page-item ms-2"
                        onClick={() => {
                          handleChangePage(index);
                        }}
                      >
                        <button class="page-link"  style={{backgroundColor: 'beige'}}>{index}</button>
                      </li>
                    )}
                    ;
                  </>
                );
              })}
              {listBookSearch.page >= listBookSearch.totalPage ? (
                <li class="page-item disabled" key={'next'}>
                  <button class="page-link ms-2">{'>'}</button>
                </li>
              ) : (
                <li
                  class="page-item"
                  key={'next'}
                  onClick={() => {
                    handleChangePage('next');
                  }}
                >
                  <button class="page-link ms-2">{'>'}</button>
                </li>
              )}
            </ul>
          </nav>
        </div>
      </DefaultLayout>
    </div>
  );
}

export default ReadingBookManagement;
