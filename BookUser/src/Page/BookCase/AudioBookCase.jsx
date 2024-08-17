import React, { useEffect, useState } from 'react';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import styles from './AudioBookCase.module.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { FaMoneyCheckAlt } from 'react-icons/fa';
import Pagination from '../../component/Pagination/Pagination';

const cx = classNames.bind(styles);

const AudioBookCase = () => {
    const [listAudioBook, setListAudioBook] = useState({
        page: 1,
        totalPage: 0,
        audioBooks: [],
    });
    let sizePage = 10;

    useEffect(() => {
        axios
            .post('http://localhost:9191/api/podcast-service/audio-book/get-audio-book-paid', {
                page: 1,
                size: sizePage,
                userId: localStorage.getItem('userId'),
            })
            .then((response) => {
                setListAudioBook({
                    ...listAudioBook,
                    page: response.data.data.page,
                    totalPage: response.data.data.totalPage,
                    audioBooks: response.data.data.audioBooks,
                });
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    return (
        <DefaultLayout>
            <div className={cx('wrapper')}>
                <div className={cx('side-bar')}>
                    <div className={cx('ebook-menu')}>
                        <Link to="/ebookcase" className={cx('book-tab')}>
                            Sách đọc
                        </Link>
                    </div>
                    <div className={cx('audio-book-menu')}>
                        <Link to="/audiobookcase" className={cx('audio-book-tab')}>
                            Sách nói
                        </Link>
                    </div>
                </div>
                <div className={cx('content')}>
                    {listAudioBook.audioBooks.map((audioBook, i) => {
                        return (
                            <div className={cx('row')}>
                                <Link className={cx('link-detail')} to="/detailbook" state={audioBook}>
                                    <div className={cx('content1')}>
                                        <img className={cx('cover')} src={audioBook.coverAudioBook} />
                                        <div>
                                            <p className={cx('bookName')}>{audioBook.audioBookName}</p>
                                            <p className={cx('bookAuthor')}>{audioBook.audioBookAuthor}</p>
                                            <p className={cx('bookPrice')}>{audioBook.audioBookPrice} vnd</p>
                                        </div>
                                    </div>
                                </Link>
                                <div className={cx('purchases')}>
                                    <FaMoneyCheckAlt className={cx('cart-icon')} />
                                    <span>Đã thanh toán</span>
                                </div>
                            </div>
                        );
                    })}
                    <Pagination listBook={listAudioBook} />
                </div>
            </div>
        </DefaultLayout>
    );
};

export default AudioBookCase;
