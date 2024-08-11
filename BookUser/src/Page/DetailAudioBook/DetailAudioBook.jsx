import React from 'react';
import styles from './DetailAudioBook.module.scss';
import classNames from 'classnames/bind';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { AiFillHeart } from 'react-icons/ai';
import { Link } from 'react-router-dom';
import AudioPlayer from 'react-h5-audio-player';
import 'react-h5-audio-player/lib/styles.css';
import PopupPayment from '../../component/PopupPayment/PopupPayment';
import axios from 'axios';

const cx = classNames.bind(styles);

const DetailAudioBook = () => {
    const location = useLocation();
    const audio = location.state;
    console.log(audio.preview);

    function handleClickHeart() {
        console.log(document.getElementById('heart'));
        console.log(document.getElementById('heart').style.color);
        if (document.getElementById('heart').style.color == 'rgb(112, 112, 110)') {
            document.getElementById('heart').style.color = 'rgb(238, 171, 26)';
        } else {
            document.getElementById('heart').style.color = 'rgb(112, 112, 110))';
        }
    }

    const [showPlayer, setShowPlayer] = useState(false);
    const handleButtonClick = () => {
        setShowPlayer(true);
    };

    const [url, setUrl] = useState('');
    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    const [checkBought, setCheckBought] = useState(false);
    const [checkUserMember, setCheckUserMember] = useState(false);

    function checkUserBoughtBook() {
        axios
            .get('http://localhost:9191/api/book-service/book/check-paid', {
                params: {
                    userId: localStorage.getItem('userId'),
                    bookId: audio.id,
                },
            })
            .then((response) => {
                if (response.data.userId != undefined || response.data.userId != null) {
                    setCheckBought(true);
                }
            })
            .catch((error) => {
                console.log(error);
            });

        axios
            .get('http://localhost:9191/api/book-service/member/check-register', {
                params: {
                    userId: localStorage.getItem('userId'),
                },
            })
            .then((response) => {
                if (response.data.userId != undefined || response.data.userId != null) {
                    setCheckUserMember(true);
                }
            })
            .catch((error) => {
                console.log(error);
            });
    }
    checkUserBoughtBook();

    const handlePayBook = () => {
        var body = {
            amount: audio.audioBookPrice,
            orderInfo:
                `Payment for books-${localStorage.getItem('userId')}` + '-' + `${audio.id}` + '-' + `forBuyAudioBook`,
        };
        axios({
            method: 'post',
            url: 'http://localhost:9191/api/book-service/vnpay/submitOrder',
            data: body,
            headers: { 'Content-Type': 'multipart/form-data', 'Access-Control-Allow-Origin': '*' },
        })
            .then((response) => {
                const urlArray = response.data.split(' ');
                setUrl(urlArray[0] + urlArray[1]);
                handleOpen();
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <DefaultLayout>
            <PopupPayment
                cost={audio.audioBookPrice}
                url={url}
                open={open}
                handleOpen={handleOpen}
                handleClose={handleClose}
            />
            <div className={cx('wrapper')}>
                <div className={cx('category')}></div>
                <div className={cx('content')}>
                    <div className={cx('content-left')}>
                        <div className={cx('content-left-top')}>
                            <div className={cx('cover')}>
                                <img src={audio.coverAudioBook}></img>
                            </div>
                        </div>
                        <div className={cx('content-left-bottom')}>
                            <div>
                                <AiFillHeart
                                    id="heart"
                                    onClick={handleClickHeart}
                                    className={cx('heart-icon')}
                                    style={{ color: 'rgb(112, 112, 110)' }}
                                />
                            </div>
                            <span>Đánh giá sách</span>
                        </div>
                    </div>
                    <div className={cx('content-right')}>
                        <div className={cx('book-detail')}>
                            <h4>{audio.audioBookName}</h4>
                            <span>Tác giả: {audio.audioBookAuthor}</span>
                            <span>Thể loại: {audio.audioBookCategory}</span>
                            <span>Nhà xuất bản: updating</span>
                            <span>Ngày cập nhật: updating</span>
                            <span className={cx('require-box')}>
                                {audio.audioBookType == 1 && <span className={cx('require')}>Yêu cầu: Miễn phí</span>}
                                {audio.audioBookType == 2 && (
                                    <span className={cx('require')}>
                                        Yêu cầu: Hội viên waka
                                        {checkUserMember && (
                                            <span className={cx('check-condition')}> (Đã là hội viên)</span>
                                        )}
                                        {!checkUserMember && (
                                            <span className={cx('check-condition')}> (Chưa là hội viên)</span>
                                        )}
                                    </span>
                                )}
                                {audio.audioBookType == 3 && (
                                    <span className={cx('require')}>
                                        Giá tiền: {audio.audioBookPrice} vnd
                                        {checkBought && <span className={cx('check-condition')}> (Đã thanh toán)</span>}
                                        {!checkBought && (
                                            <span className={cx('check-condition')}> (Chưa thanh toán)</span>
                                        )}
                                    </span>
                                )}
                            </span>
                            <button>
                                {audio.audioBookType == 2 && checkUserMember == true && (
                                    <span className={cx('read-book')} onClick={handleButtonClick}>
                                        Nghe sách
                                    </span>
                                )}
                                {audio.audioBookType == 3 && checkBought == true && (
                                    <span className={cx('read-book')} onClick={handleButtonClick}>
                                        Nghe sách
                                    </span>
                                )}

                                {audio.audioBookType == 1 && (
                                    <span className={cx('read-book')} onClick={handleButtonClick}>
                                        Nghe sách
                                    </span>
                                )}
                                {audio.audioBookType == 2 && checkUserMember == false && (
                                    <Link className={cx('read-book')} to="/registercard">
                                        Đăng kí hội viên
                                    </Link>
                                )}
                                {audio.audioBookType == 3 && checkBought == false && (
                                    <span className={cx('read-book')} onClick={handlePayBook}>
                                        Thanh toán
                                    </span>
                                )}
                            </button>
                            <p>{audio.audioBookDescription}</p>
                        </div>
                    </div>
                </div>
                {showPlayer && (
                    <div className={cx('posdcast-audio-wrapper')}>
                        <AudioPlayer src={audio.preview} autoPlay controls />
                    </div>
                )}
            </div>
        </DefaultLayout>
    );
};

export default DetailAudioBook;
