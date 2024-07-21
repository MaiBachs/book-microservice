import React, { useState } from 'react';
import DefaultLayout from '../../defaultLayout/DefaultLayout';
import styles from './AddNewReadingBook.module.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import axios from 'axios';

const cx = classNames.bind(styles);

const AddNewReadingBook = () => {
    const [fileData, setFileData] = useState()

    const [readingBook, setReadingBook] = useState(
      {
        bookName: "",
        bookAuthor: "",
        bookCategory: "",
        bookDescription: "",
        coverBook: "",
        bookPrice: 0,
        lastUser: "admin"
      }
    )

    function handleInputReadingBook(event) {
      setReadingBook({
        ...readingBook,
        [event.target.name]: event.target.value
      })
    }

    function handleInputFilePdf(event) {
      setFileData(event.target.files[0])
    }

    function handleClickAddNew(){
      console.log(readingBook)
      if(readingBook.bookName.length == 0){
        alert("Book_name required");
      }
      else if(readingBook.bookAuthor.length == 0){
        alert("Book_author required");
      }
      else if(readingBook.bookCategory.length == 0){
        alert("Book_category required");
      }
      else if(readingBook.bookDescription.length == 0){
        alert("Book_description required");
      }
      else if(readingBook.coverBook.length == 0){
        alert("Book_cover required");
      }
      else if(fileData == null){
        alert("File pdf required");
      }
      else{
        setReadingBook({
          ...readingBook,
          bookName: readingBook.bookName.trim,
          bookAuthor: readingBook.bookAuthor.trim,
          bookCategory: readingBook.bookCategory.trim,
          bookDescription: readingBook.bookDescription.trim,
          coverBook: readingBook.coverBook.trim
        })
        const formData = new FormData();
        formData.append('file', fileData);
        formData.append('bookEntityStr', JSON.stringify(readingBook));

        axios.post("http://localhost:9191/api/e-book-service/management/add-reading-book", formData)
        .then((response)=>{
          if(response.data.data.id != null){
            alert("Add new succcess")
          }else{
            alert("Add new False")
          }
        })
        .catch(()=>{})
      }
    }

    return (
        <div className={cx('wrapper')}>
          <DefaultLayout>
            <div className={cx("header-tab")}>
              <Link className={cx("reading-book-management")} to="/readingbookmanagement">
                Reading book management
              </Link>
              <Link className={cx("add-reading-book")} to="/addnewreadingbook" >
                Add new reading book
              </Link>
            </div>
            <div className={cx("content-box")}>
              <div className={cx("header-add-box")}>
                <div className={cx("cover-box")}>
                  <p style={{color: 'black', fontSize: '16px', textAlign: 'start'}}>Cover book</p>
                  <div className={cx("cover-image")} >
                    <img src={readingBook.coverBook}  style={{ width: '100%', height: 'auto' }}></img>
                  </div>
                  <button className={cx("cover-upload-button")}>Upload</button>
                </div>
                <div className={cx("des-box")}>
                  <p style={{color: 'black', fontSize: '16px', textAlign: 'start'}}>Description:</p>
                  <textarea name='bookDescription' className={cx("description")} onChange={(event)=>{handleInputReadingBook(event)}}></textarea>
                </div>
              </div>
              <div className={cx('url-box')}>
                <p className={cx('url-title')}>Url cover:</p>
                <input name="coverBook" className={cx('url-input')} onChange={(event)=>{handleInputReadingBook(event)}} ></input>
              </div>
              <div className={cx("grid-container")}>
                <div className={cx("grid-item")}>Book_name:</div>
                <div className={cx("grid-item")}>
                  <input name="bookName" onChange={(event)=>{handleInputReadingBook(event)}}></input>
                </div>
                <div className={cx("grid-item")}></div>
                <div className={cx("grid-item")}>Book_category:</div>
                <div className={cx("grid-item")}>
                  <input name="bookCategory" onChange={(event)=>{handleInputReadingBook(event)}}></input>
                </div>
                <div className={cx("grid-item")}>Book_author:</div>
                <div className={cx("grid-item")}>
                  <input name="bookAuthor" onChange={(event)=>{handleInputReadingBook(event)}}></input>
                </div>
                <div className={cx("grid-item")}></div>
                <div className={cx("grid-item")}>Book_price:</div>
                <div className={cx("grid-item")}>
                  <input name="bookPrice" onChange={(event)=>{handleInputReadingBook(event)}}></input>
                </div>
                <div className={cx("grid-item")}>Pdf file:</div>
                <div className={cx("grid-item")}>
                  <input type='file' name="preview" onChange={(event)=>{handleInputFilePdf(event)}} readOnly style={{height: '27px', fontSize: '14px'}} ></input>
                </div>
                <div className={cx("grid-item")}></div>
                <div className={cx("grid-item")}>
                  <Link to="#">view file</Link>
                </div>
                <div className={cx("grid-item")}>
                </div>
              </div>
            </div>
            <div className={cx("button-box")}>
              <button className={cx("button-add-new")} onClick={handleClickAddNew}>Add new</button>
              <button className={cx("button-clear")}>Clear</button>
            </div>
          </DefaultLayout>
        </div>
        );
}

export default AddNewReadingBook;
