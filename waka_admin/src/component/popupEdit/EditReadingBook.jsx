import React, { useEffect } from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import styles from './EditReadingBook.module.scss';
import classNames from 'classnames/bind';
import { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

const cx = classNames.bind(styles);

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 900,
    height: 600,
    borderRadius: 0,
    bgcolor: 'background.paper',
    boxShadow: 24,
    // p: 4,
};

const EditReadingBook = (props) => {
    const [fileData, setFileData] = useState();
    const navigate = useNavigate();

    const [readingBook, setReadingBook] = useState({
        bookAuthor: '',
        bookCategory: '',
        bookDescription: '',
        bookName: '',
        bookPrice: '',
        bookType: '',
        coverBook: '',
        favorite: '',
        id: '',
        lastUpdate: '',
        lastUser: '',
        preview: '',
        purchases: '',
        view: ''
    });

    useEffect(() => {
        if (props.dataEdit) {
            setReadingBook({
                bookAuthor: props.dataEdit.bookAuthor || '',
                bookCategory: props.dataEdit.bookCategory || '',
                bookDescription: props.dataEdit.bookDescription || '',
                bookName: props.dataEdit.bookName || '',
                bookPrice: props.dataEdit.bookPrice || '',
                bookType: props.dataEdit.bookType || '',
                coverBook: props.dataEdit.coverBook || '',
                favorite: props.dataEdit.favorite || 0,
                id: props.dataEdit.id || '',
                lastUpdate: props.dataEdit.lastUpdate || '',
                lastUser: props.dataEdit.lastUser || '',
                preview: props.dataEdit.preview || '',
                purchases: props.dataEdit.purchases || 0,
                view: props.dataEdit.view || 0
            });
        }
    }, [props.dataEdit]);

    function handleInputBook(event) {
        setReadingBook({
            ...readingBook,
            [event.target.name]: event.target.value
        });
    }

    function handleInputFilePdf(event) {
        setFileData(event.target.files[0]);
    }

    function handleEdit() {
        console.log(readingBook);
        if (readingBook.bookName.length === 0) {
            alert("Book_name required");
        } else if (readingBook.bookAuthor.length === 0) {
            alert("Book_author required");
        } else if (readingBook.bookCategory.length === 0) {
            alert("Book_category required");
        } else if (readingBook.bookDescription.length === 0) {
            alert("Book_description required");
        } else if (readingBook.coverBook.length === 0) {
            alert("Book_cover required");
        } else {
            setReadingBook({
                ...readingBook,
                bookName: readingBook.bookName.trim(),
                bookAuthor: readingBook.bookAuthor.trim(),
                bookCategory: readingBook.bookCategory.trim(),
                bookDescription: readingBook.bookDescription.trim(),
                coverBook: readingBook.coverBook.trim()
            });
            const formData = new FormData();
            formData.append('file', fileData);
            formData.append('bookEntityStr', JSON.stringify(readingBook));

            axios.post("http://localhost:9191/api/e-book-service/management/edit", formData)
                .then((response) => {
                    if (response.data.data.id != null) {
                        alert("Edit success");
                        navigate('/readingbookmanagement');
                        props.handleCloseEdit(true);
                    } else {
                        alert("Edit Failed");
                    }
                })
                .catch(() => {});
        }
    }

    return (
        <div>
            <Modal
                open={props.open}
                onClose={props.handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2" style={{ backgroundColor: '#04AA6D', color: 'white' }}>
                        <div style={{ marginLeft: '30px' }}>Edit reading book</div>
                    </Typography>
                    <Typography sx={{ mt: 2 }}>
                        <div className={cx("content-box")}>
                            <div className={cx("header-add-box")}>
                                <div className={cx("cover-box")}>
                                    <p style={{ color: 'black', fontSize: '16px', textAlign: 'start', marginLeft: '10px' }}>Cover book</p>
                                    <div className={cx("cover-image")} >
                                        <img src={readingBook.coverBook} style={{ width: '100%', height: 'auto' }}></img>
                                    </div>
                                    <button className={cx("cover-upload-button")}>Upload</button>
                                </div>
                                <div className={cx("des-box")}>
                                    <p style={{ color: 'black', fontSize: '16px', textAlign: 'start' }}>Description:</p>
                                    <textarea value={readingBook.bookDescription} name='bookDescription' className={cx("description")} onChange={(event) => { handleInputBook(event) }}></textarea>
                                </div>
                            </div>
                            <div className={cx('url-box')}>
                                <p className={cx('url-title')}>Url cover:</p>
                                <input value={readingBook.coverBook} name="coverBook" className={cx('url-input')} onChange={(event) => { handleInputBook(event) }} ></input>
                            </div>
                            <div className={cx("grid-container")}>
                                <div className={cx("grid-item")}>Book_name:</div>
                                <div className={cx("grid-item")}>
                                    <input value={readingBook.bookName} name="bookName" onChange={(event) => { handleInputBook(event) }}></input>
                                </div>
                                <div className={cx("grid-item")}></div>
                                <div className={cx("grid-item")}>Book_category:</div>
                                <div className={cx("grid-item")}>
                                    <input value={readingBook.bookCategory} name="bookCategory" onChange={(event) => { handleInputBook(event) }}></input>
                                </div>
                                <div className={cx("grid-item")}>Book_author:</div>
                                <div className={cx("grid-item")}>
                                    <input value={readingBook.bookAuthor} name="bookAuthor" onChange={(event) => { handleInputBook(event) }}></input>
                                </div>
                                <div className={cx("grid-item")}></div>
                                <div className={cx("grid-item")}>Book_price:</div>
                                <div className={cx("grid-item")}>
                                    <input value={readingBook.bookPrice} name="bookPrice" onChange={(event) => { handleInputBook(event) }}></input>
                                </div>
                                <div className={cx("grid-item")}>Bile:</div>
                                <div className={cx("grid-item")}>
                                    <input type='file' name="preview" onChange={(event) => { handleInputFilePdf(event) }} readOnly style={{ height: '27px', fontSize: '14px' }} ></input>
                                </div>
                                <div className={cx("grid-item")}></div>
                                <div className={cx("grid-item")}>
                                    Book_option
                                </div>
                                <div className={cx("grid-item")}>
                                    <select value={readingBook.bookType} name="bookType" onChange={(event) => { handleInputBook(event) }}>
                                        <option value="1" >Free reader waka</option>
                                        <option value="2" >For member waka</option>
                                        <option value="3" >Pay fee</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </Typography>
                    <Typography style={{ textAlign: 'right', padding: '30px', bottom: '0', right: '0', position: 'fixed' }}>
                        <button style={{ marginRight: '10px', marginLeft: 'auto' }} onClick={handleEdit}>Save</button>
                        <button style={{ marginRight: '15px', marginLeft: 'auto' }} onClick={props.handleClose}>Close</button>
                    </Typography>
                </Box>
            </Modal>
        </div>
    );
}

export default EditReadingBook;
