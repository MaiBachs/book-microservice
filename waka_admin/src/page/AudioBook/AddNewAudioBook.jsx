import React, { useState } from 'react';
import DefaultLayout from '../../defaultLayout/DefaultLayout';
import styles from './AddNewAudioBook.module.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const cx = classNames.bind(styles);

const AddNewAudioBook = () => {
    const [fileData, setFileData] = useState()
    const navigate = useNavigate();

    const [audioBook, setAudioBook] = useState(
      {
        audioBookName: "",
        audioBookAuthor: "",
        audioBookCategory: "",
        audioBookDescription: "",
        coverAudioBook: "",
        audioBookPrice: 0,
        lastUser: "admin",
        audioBookType: 1
      }
    )

    function handleInputAudioBook(event) {
      setAudioBook({
        ...audioBook,
        [event.target.name]: event.target.value
      })
    }

    function handleInputFilePdf(event) {
      setFileData(event.target.files[0])
    }

    function handleClickAddNew(){
      if(audioBook.audioBookName.length == 0){
        alert("Book_name required");
      }
      else if(audioBook.audioBookAuthor.length == 0){
        alert("Book_author required");
      }
      else if(audioBook.audioBookCategory.length == 0){
        alert("Book_category required");
      }
      else if(audioBook.audioBookDescription.length == 0){
        alert("Book_description required");
      }
      else if(audioBook.coverAudioBook.length == 0){
        alert("Book_cover required");
      }
      else if(fileData == null){
        alert("File audio required");
      }
      else{
        setAudioBook({
          ...audioBook,
          audioBookName: audioBook.audioBookName.trim,
          audioBookAuthor: audioBook.audioBookAuthor.trim,
          audioBookCategory: audioBook.audioBookCategory.trim,
          audioBookDescription: audioBook.audioBookDescription.trim,
          coverAudioBook: audioBook.coverAudioBook.trim
        })
        const formData = new FormData();
        formData.append('file', fileData);
        formData.append('bookEntityStr', JSON.stringify(audioBook));

        axios.post("http://localhost:9191/api/podcast-service/management/add-audio-book", formData)
        .then((response)=>{
          if(response.data.data.id != null){
            alert("Add new succcess")
            navigate('/audiobookmanagement');
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
              <Link className={cx("reading-book-management")} to="/audiobookmanagement">
                Audio book management
              </Link>
              <Link className={cx("add-reading-book")} to="/addnewaudiobook" >
                Add new audio book
              </Link>
            </div>
            <div className={cx("content-box")}>
              <div className={cx("header-add-box")}>
                <div className={cx("cover-box")}>
                  <p style={{color: 'black', fontSize: '16px', textAlign: 'start'}}>Cover book</p>
                  <div className={cx("cover-image")} >
                    <img src={audioBook.coverAudioBook}  style={{ width: '100%', height: 'auto' }}></img>
                  </div>
                  <button className={cx("cover-upload-button")}>Upload</button>
                </div>
                <div className={cx("des-box")}>
                  <p style={{color: 'black', fontSize: '16px', textAlign: 'start'}}>Description:</p>
                  <textarea name='audioBookDescription' className={cx("description")} onChange={(event)=>{handleInputAudioBook(event)}}></textarea>
                </div>
              </div>
              <div className={cx('url-box')}>
                <p className={cx('url-title')}>Url cover:</p>
                <input name="coverAudioBook" className={cx('url-input')} onChange={(event)=>{handleInputAudioBook(event)}} ></input>
              </div>
              <div className={cx("grid-container")}>
                <div className={cx("grid-item")}>Audio_book_name:</div>
                <div className={cx("grid-item")}>
                  <input name="audioBookName" onChange={(event)=>{handleInputAudioBook(event)}}></input>
                </div>
                <div className={cx("grid-item")}></div>
                <div className={cx("grid-item")}>Audio_book_category:</div>
                <div className={cx("grid-item")}>
                  <input name="audioBookCategory" onChange={(event)=>{handleInputAudioBook(event)}}></input>
                </div>
                <div className={cx("grid-item")}>Audio_book_author:</div>
                <div className={cx("grid-item")}>
                  <input name="audioBookAuthor" onChange={(event)=>{handleInputAudioBook(event)}}></input>
                </div>
                <div className={cx("grid-item")}></div>
                <div className={cx("grid-item")}>Audio_book_price:</div>
                <div className={cx("grid-item")}>
                  <input name="audioBookPrice" onChange={(event)=>{handleInputAudioBook(event)}}></input>
                </div>
                <div className={cx("grid-item")}>Audio_file:</div>
                <div className={cx("grid-item")}>
                  <input type='file' name="preview" onChange={(event)=>{handleInputFilePdf(event)}} readOnly style={{height: '27px', fontSize: '14px'}} ></input>
                </div>
                <div className={cx("grid-item")}></div>
                <div className={cx("grid-item")}>
                  Audio_book_option
                </div>
                <div className={cx("grid-item")}>
                  <select name="audioBookType" onChange={(event) => { handleInputAudioBook(event) }}>
                    <option value="1" >Free reader waka</option>
                    <option value="2" >For member waka</option>
                    <option value="3" >Pay fee</option>
                  </select>
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

export default AddNewAudioBook;
