import React, { useEffect, useState } from 'react';
import DefaultLayout from '../../defaultLayout/DefaultLayout';
import styles from './AudioBookManagement.module.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { FaEdit } from "react-icons/fa";
import { MdDelete } from "react-icons/md";
import { BiDetail } from "react-icons/bi";
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import DetailAudioBook from '../../component/popupDetailReadingBook/DetailAudioBook';
import EditAudioBook from '../../component/popupEdit/EditAudioBook';
import PopupShowAudio from '../../component/popupShowAudio/PopupShowAudio';

const cx = classNames.bind(styles);

function AudioBookManagement() {
  const [open, setOpen] = useState(false);
  const [openEdit, setOpenEdit] = useState(false);
  const [openShowAudio, setOpenShowAudio] = useState(false);
  const [bookSelected, setBookSelected] = useState({}); 
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const handleOpenEdit = () => setOpenEdit(true);
  const handleCloseEdit = () => setOpenEdit(false);
  const handleOpenShowAudio = () => setOpenShowAudio(true);
  const handleCloseShowAudio = () => setOpenShowAudio(false);

  function handleSelectBook(book){
    setBookSelected(book);
  }

  const [listBookSearch, setListBookSearch] = useState({
    page: 1,
    totalPage: 0,
    audioBooks: [],
  });

  const [dataSearch, setDataSearch] = useState({
    audioBookName: "",
    audioBookCategory: "",
    audioBookType: null,
    audioBookAuthor: "",
    page: listBookSearch.page,
    size: 10
  })

  function handleInputSearch(event) {
    setDataSearch({
      ...dataSearch,
      [event.target.name]: event.target.value
    })
    console.log(dataSearch);
  }

  useEffect(() => {
    axios
      .post("http://localhost:9191/api/podcast-service/management/search-audio-book-by-page", {
        ...dataSearch,
        page: listBookSearch.page
      })
      .then((response) => {
        setListBookSearch({
          ...listBookSearch,
          totalPage: response.data.data.totalPage,
          audioBooks: [...response.data.data.audioBooks],
        });
      })
      .catch();
  }, [listBookSearch.page])

  function handleSearch() {
    axios
      .post("http://localhost:9191/api/podcast-service/management/search-audio-book-by-page", {
        ...dataSearch,
        page: listBookSearch.page
      })
      .then((response) => {
        setListBookSearch({
          ...listBookSearch,
          totalPage: response.data.data.totalPage,
          audioBooks: [...response.data.data.audioBooks],
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

  function handkeDelete(bookId){
    axios
      .get("http://localhost:9191/api/podcast-service/management/delete", {
        params: {
          bookId: bookId,
        },
    })
      .then((response) => {
        alert("Delete success")
        handleSearch()
      })
      .catch();
  }

  return (
    <div className={cx('wrapper')}>
      <DefaultLayout>
      <PopupShowAudio open={openShowAudio} handleOpen={handleOpenShowAudio} handleClose={handleCloseShowAudio} preview = {bookSelected.preview} />
      <DetailAudioBook className={cx("detail-popup")} clas open={open} handleOpen={handleOpen} handleClose={handleClose} dataDetail = {bookSelected} />
      <EditAudioBook className={cx("edit-popup")} clas open={openEdit} handleOpen={handleOpenEdit} handleClose={handleCloseEdit} dataEdit = {bookSelected} />
        <div className={cx("header-tab")}>
          <Link className={cx("reading-book-management")} to="/audiobookmanagement">
            Audio book management
          </Link>
          <Link className={cx("add-reading-book")} to="/addnewaudiobook" >
            Add new audio book
          </Link>
        </div>
        <div className={cx("grid-container")}>
          <div className={cx("grid-item")}>
            <label>Audio_book_name</label>
          </div>
          <div className={cx("grid-item")}>
            <input name="audioBookName" type='text' value={dataSearch.audioBookName} onChange={(event) => { handleInputSearch(event) }}></input>
          </div>
          <div className={cx("grid-item")}></div>
          <div className={cx("grid-item")}></div>
          <div className={cx("grid-item")}>
            <label>Audio_book_category</label>
          </div>
          <div className={cx("grid-item")}>
            <input name="audioBookCategory" type='text' value={dataSearch.audioBookCategory} onChange={(event) => { handleInputSearch(event) }}></input>
          </div>
          <div className={cx("grid-item")}><button className={cx('button-search')} onClick={handleSearch}>Search</button></div>
          <div className={cx("grid-item")}>
            <label>Audio_book_author</label>
          </div>
          <div className={cx("grid-item")}>
            <input name="audioBookAuthor" type='text' value={dataSearch.audioBookAuthor} onChange={(event) => { handleInputSearch(event) }}></input>
          </div>
          <div className={cx("grid-item")}>
          </div>
          <div className={cx("grid-item")}></div>
          <div className={cx("grid-item")}>
            <label>Audio_book_option</label>
          </div>
          <div className={cx("grid-item")}>
            <select name="audioBookType" onChange={(event) => { handleInputSearch(event) }}>
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
              {listBookSearch.audioBooks.map((book, i) => (
                <>
                <tr key={i}>
                  <td>{i + 1}</td>
                  <td>{book.audioBookName}</td>
                  <td>{book.audioBookCategory}</td>
                  <td>
                    {book.audioBookType == 1 && (<span>Free reader waka</span>)}
                    {book.audioBookType == 2 && (<span>For member waka</span>)}
                    {book.audioBookType == 3 && (<span>Pay fee</span>)}
                  </td>
                  <td>{book.audioBookAuthor}</td>
                  <td>{book.view}</td>
                  <td><Link href='#' onClick={()=>{handleOpenShowAudio(); handleSelectBook(book)}} state={book} >{book.preview.split("\\")[1]}</Link></td>
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

export default AudioBookManagement;
