import React, { useEffect } from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import styles from './EditAudioBook.module.scss';
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

const EditAudioBook = (props) => {
    const [fileData, setFileData] = useState();
    const navigate = useNavigate();

    const [audioBook, setAudioBook] = useState({
        audioBookAuthor: '',
        audioBookCategory: '',
        audioBookDescription: '',
        audioBookName: '',
        audioBookPrice: '',
        audioBookType: '',
        coverAudioBook: '',
        favorite: '',
        id: '',
        lastUpdate: '',
        preview: '',
        purchases: '',
        view: ''
    });

    useEffect(() => {
        if (props.dataEdit) {
            setAudioBook({
                audioBookAuthor: props.dataEdit.audioBookAuthor || '',
                audioBookCategory: props.dataEdit.audioBookCategory || '',
                audioBookDescription: props.dataEdit.audioBookDescription || '',
                audioBookName: props.dataEdit.audioBookName || '',
                audioBookPrice: props.dataEdit.audioBookPrice || 0,
                audioBookType: props.dataEdit.audioBookType || '',
                coverAudioBook: props.dataEdit.coverAudioBook || '',
                favorite: props.dataEdit.favorite || 0,
                id: props.dataEdit.id || '',
                lastUpdate: props.dataEdit.lastUpdate || '',
                preview: props.dataEdit.preview || '',
                purchases: props.dataEdit.purchases || 0,
                view: props.dataEdit.view || 0
            });
            console.log(props.dataEdit);
            console.log(audioBook);
        }
    }, [props.dataEdit]);


    function handleInputBook(event) {
        setAudioBook({
            ...audioBook,
            [event.target.name]: event.target.value
        });
    }

    function handleInputFilePdf(event) {
        setFileData(event.target.files[0]);
    }

    function handleEdit() {
        if (audioBook.audioBookName.length === 0) {
            alert("Book_name required");
        } else if (audioBook.audioBookAuthor.length === 0) {
            alert("Book_author required");
        } else if (audioBook.audioBookCategory.length === 0) {
            alert("Book_category required");
        } else if (audioBook.audioBookDescription.length === 0) {
            alert("Book_description required");
        } else if (audioBook.coverAudioBook.length === 0) {
            alert("Book_cover required");
        } else {
            setAudioBook({
                ...audioBook,
                audioBookName: audioBook.audioBookName.trim(),
                audioBookAuthor: audioBook.audioBookAuthor.trim(),
                audioBookCategory: audioBook.audioBookCategory.trim(),
                audioBookDescription: audioBook.audioBookDescription.trim(),
                coverAudioBook: audioBook.coverAudioBook.trim(),
            });
            const formData = new FormData();
            formData.append('file', fileData);
            formData.append('bookEntityStr', JSON.stringify(audioBook));

            axios.post("http://localhost:9191/api/podcast-service/management/edit", formData)
                .then((response) => {
                    if (response.data.data.id != null) {
                        alert("Edit success");
                        props.handleCloseEdit(true)
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
                        <div style={{ marginLeft: '30px' }}>Edit audio book</div>
                    </Typography>
                    <Typography sx={{ mt: 2 }}>
                        <div className={cx("content-box")}>
                            <div className={cx("header-add-box")}>
                                <div className={cx("cover-box")}>
                                    <p style={{ color: 'black', fontSize: '16px', textAlign: 'start', marginLeft: '10px' }}>Cover book</p>
                                    <div className={cx("cover-image")} >
                                        <img src={audioBook.coverAudioBook} style={{ width: '100%', height: 'auto' }}></img>
                                    </div>
                                    <button className={cx("cover-upload-button")}>Upload</button>
                                </div>
                                <div className={cx("des-box")}>
                                    <p style={{ color: 'black', fontSize: '16px', textAlign: 'start' }}>Description:</p>
                                    <textarea style={{width: '700px', height: '100%', fontSize: '16px'}} value={audioBook.audioBookDescription} name="audioBookDescription" onChange={(event) => { handleInputBook(event) }}></textarea>
                                </div>
                            </div>
                            <div className={cx('url-box')}>
                                <p className={cx('url-title')}>Url cover:</p>
                                <input value={audioBook.coverAudioBook} name="audioCoverBook" className={cx('url-input')} onChange={(event) => { handleInputBook(event) }} ></input>
                            </div>
                            <div className={cx("grid-container")}>
                                <div className={cx("grid-item")}>Audio_book_name:</div>
                                <div className={cx("grid-item")}>
                                    <input value={audioBook.audioBookName} name="audioBookName" onChange={(event) => { handleInputBook(event) }}></input>
                                </div>
                                <div className={cx("grid-item")}></div>
                                <div className={cx("grid-item")}>Audio_book_category:</div>
                                <div className={cx("grid-item")}>
                                    <input value={audioBook.audioBookCategory} name="audioBookCategory" onChange={(event) => { handleInputBook(event) }}></input>
                                </div>
                                <div className={cx("grid-item")}>Audio_book_author:</div>
                                <div className={cx("grid-item")}>
                                <input value={audioBook.audioBookAuthor} name="audioBookAuthor" onChange={(event) => { handleInputBook(event) }}></input>
                                </div>
                                <div className={cx("grid-item")}></div>
                                <div className={cx("grid-item")}>Audio_book_price:</div>
                                <div className={cx("grid-item")}>
                                <input value={audioBook.audioBookPrice} name="audioBookPrice" onChange={(event) => { handleInputBook(event) }}></input>
                                </div>
                                <div className={cx("grid-item")}>Audio_file:</div>
                                <div className={cx("grid-item")}>
                                    <input type='file' name="preview" onChange={(event) => { handleInputFilePdf(event) }} readOnly style={{ height: '27px', fontSize: '14px' }} ></input>
                                </div>
                                <div className={cx("grid-item")}></div>
                                <div className={cx("grid-item")}>
                                    Audio_book_option
                                </div>
                                <div className={cx("grid-item")}>
                                    <select value={audioBook.audioBookType} name="bookType" onChange={(event) => { handleInputBook(event) }}>
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

export default EditAudioBook;
