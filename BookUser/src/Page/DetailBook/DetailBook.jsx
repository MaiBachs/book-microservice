import React from 'react';
import styles from './DetailBook.module.scss';
import classNames from 'classnames/bind';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { AiFillHeart } from 'react-icons/ai';
import { Link } from 'react-router-dom';
import { AiOutlineEye } from 'react-icons/ai';
import PopupPayment from '../../component/PopupPayment/PopupPayment';
import axios from 'axios';

const cx = classNames.bind(styles);

function DetailBook() {
    const location = useLocation();
    const bookCT2 = location.state;

    function handleClickHeart() {
        console.log(document.getElementById('heart'));
        console.log(document.getElementById('heart').style.color);
        if (document.getElementById('heart').style.color == 'rgb(112, 112, 110)') {
            document.getElementById('heart').style.color = 'rgb(238, 171, 26)';
        } else {
            document.getElementById('heart').style.color = 'rgb(112, 112, 110))';
        }
    }

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
                    bookId: bookCT2.id,
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
            amount: bookCT2.bookPrice,
            orderInfo:
                `Payment for books-${localStorage.getItem('userId')}` + '-' + `${bookCT2.id}` + '-' + `forBuyBook`,
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
                cost={bookCT2.bookPrice}
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
                                <img src={bookCT2.coverBook}></img>
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
                            <h4 style={{ color: '#097a63', fontFamily: 'Arial, Helvetica, sans-serif' }}>
                                {bookCT2.bookName}
                            </h4>
                            <div className={cx('views')}>
                                <span>Lượt xem: {bookCT2.view}</span>
                                <AiOutlineEye className={cx('views-icon')} />
                            </div>
                            <span>Tác giả: {bookCT2.bookAuthor}</span>
                            <span>Thể loại: {bookCT2.bookCategory}</span>
                            <span>Ngày cập nhật: {bookCT2.lastUpdate}</span>
                            <span className={cx('require-box')}>
                                {bookCT2.bookType == 1 && <span className={cx('require')}>Yêu cầu: Miễn phí</span>}
                                {bookCT2.bookType == 2 && (
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
                                {bookCT2.bookType == 3 && (
                                    <span className={cx('require')}>
                                        Giá tiền: {bookCT2.bookPrice} vnd
                                        {checkBought && <span className={cx('check-condition')}> (Đã thanh toán)</span>}
                                        {!checkBought && (
                                            <span className={cx('check-condition')}> (Chưa thanh toán)</span>
                                        )}
                                    </span>
                                )}
                            </span>
                            <button>
                                {bookCT2.bookType == 2 && checkUserMember == true && (
                                    <Link className={cx('read-book')} to="/pdffileview" state={bookCT2}>
                                        Đọc sách
                                    </Link>
                                )}
                                {bookCT2.bookType == 3 && checkBought == true && (
                                    <Link className={cx('read-book')} to="/pdffileview" state={bookCT2}>
                                        Đọc sách
                                    </Link>
                                )}

                                {bookCT2.bookType == 1 && (
                                    <Link className={cx('read-book')} to="/pdffileview" state={bookCT2}>
                                        Đọc sách
                                    </Link>
                                )}
                                {bookCT2.bookType == 2 && checkUserMember == false && (
                                    <Link className={cx('read-book')} to="/registercard" state={bookCT2}>
                                        Đăng kí hội viên
                                    </Link>
                                )}
                                {bookCT2.bookType == 3 && checkBought == false && (
                                    <Link className={cx('read-book')} onClick={handlePayBook} to="#" state={bookCT2}>
                                        Thanh toán
                                    </Link>
                                )}
                            </button>
                            <p>{bookCT2.bookDescription}</p>
                        </div>
                    </div>
                </div>
            </div>
        </DefaultLayout>
    );
}

export default DetailBook;
