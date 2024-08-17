import React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import styles from './DetailAudioBook.module.scss';
import classNames from 'classnames/bind';
import { Link} from 'react-router-dom';
import { useState } from 'react';
import PopupShowAudio from '../popupShowAudio/PopupShowAudio';

const cx = classNames.bind(styles);

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 1000,
    height: 600,
    borderRadius: 0,
    bgcolor: 'background.paper',
    boxShadow: 24
};

const DetailAudioBook = (props) => {
    const [openShowAudio, setOpenShowAudio] = useState(false);
    const handleOpenShowAudio = () => setOpenShowAudio(true);
    const handleCloseShowAudio = () => setOpenShowAudio(false);
    

    return (
        <div>
            <Modal
                open={props.open}
                onClose={props.handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2" style={{backgroundColor: '#04AA6D', color: 'white'}}>
                        <PopupShowAudio open={openShowAudio} handleOpen={handleOpenShowAudio} handleClose={handleCloseShowAudio} preview = {props.dataDetail.preview} />
                        <div style={{marginLeft: '30px'}}>Detail</div>
                    </Typography>
                    <Typography sx={{ mt: 2 }}>
                        <div className={cx('content-box')} style={{display: 'flex'}}>
                            <div style={{width: '160px', paddingTop: '15px', paddingLeft: '10px'}}>
                                <img src={props.dataDetail.coverAudioBook}  style={{ width: '100%', height: 'auto' }}></img>
                            </div>
                            <div className={cx('grid-container')}>
                                <div className={cx('grid-item')}>Audio_Book_name:</div>
                                <div className={cx('grid-item')}>
                                    <input value={props.dataDetail.audioBookName} readOnly></input>
                                </div>
                                <div className={cx('grid-item')}></div>
                                <div className={cx('grid-item')}>Audio_Book_category:</div>
                                <div className={cx('grid-item')}>
                                    <input value={props.dataDetail.audioBookCategory} readOnly></input>
                                </div>
                                <div className={cx('grid-item')}>Audio_Book_price:</div>
                                <div className={cx('grid-item')}>
                                    <input value={props.dataDetail.audioBookPrice} readOnly></input>
                                </div>
                                <div className={cx('grid-item')}></div>
                                <div className={cx('grid-item')}>Audio_Book_author:</div>
                                <div className={cx('grid-item')}>
                                    <input value={props.dataDetail.audioBookAuthor} readOnly></input>
                                </div>
                                <div className={cx('grid-item')}>View:</div>
                                <div className={cx('grid-item')}>
                                    <input value={props.dataDetail.view} readOnly></input>
                                </div>
                                <div className={cx('grid-item')}></div>
                                <div className={cx('grid-item')}>Pdf file:</div>
                                <div className={cx('grid-item')} style={{maxHeight: '24px'}}>
                                    <Link href='#' onClick={handleOpenShowAudio} >{props.dataDetail.preview}</Link>
                                </div>
                                <div className={cx('grid-item')}>Favorite:</div>
                                <div className={cx('grid-item')}>
                                    <input value={props.dataDetail.favorite} readOnly></input>
                                </div>
                                <div className={cx('grid-item')}></div>
                                <div className={cx('grid-item')}></div>
                                <div className={cx('grid-item')}></div>
                            </div>
                        </div>
                    </Typography>
                    <Typography style={{paddingLeft: '3%', paddingRight: '3%'}}>
                        <div className={cx('grid-item')}>Description:</div>
                        <div className={cx('grid-item')}>
                            <textarea style={{ width: '100%', height: '200px', outline: 'none' }} value={props.dataDetail.audioBookDescription}></textarea>
                        </div>
                    </Typography>
                    <Typography style={{textAlign: 'right', padding: '30px', bottom: '0', right: '0',  position: 'fixed'}}>
                        <button style={{marginRight: '0px', marginLeft: 'auto'}} onClick={props.handleClose}>Close</button>
                    </Typography>
                </Box>
            </Modal>
        </div>
    );
}

export default DetailAudioBook;
