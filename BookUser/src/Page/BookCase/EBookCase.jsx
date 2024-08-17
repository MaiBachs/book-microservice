import React, { useEffect, useState } from 'react';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import styles from './EBookCase.module.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { FaMoneyCheckAlt } from 'react-icons/fa';
import Pagination from '../../component/Pagination/Pagination';

const cx = classNames.bind(styles);

const EBookCase = () => {
    const [listBook, setListBook] = useState({
        page: 1,
        totalPage: 0,
        bookEntityList: [],
    });
    let sizePage = 10;

    useEffect(() => {
        axios
            .post('http://localhost:9191/api/e-book-service/get-book-paid', {
                page: 1,
                size: sizePage,
                userId: localStorage.getItem('userId'),
            })
            .then((response) => {
                setListBook({
                    ...listBook,
                    page: response.data.data.page,
                    totalPage: response.data.data.totalPage,
                    bookEntityList: response.data.data.bookEntityList,
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
                    {listBook.bookEntityList.map((book, i) => {
                        return (
                            <div className={cx('row')}>
                                <Link className={cx('link-detail')} to="/detailbook" state={book}>
                                    <div className={cx('content1')}>
                                        <img className={cx('cover')} src={book.coverBook} />
                                        <div>
                                            <p className={cx('bookName')}>{book.bookName}</p>
                                            <p className={cx('bookAuthor')}>{book.bookAuthor}</p>
                                            <p className={cx('bookPrice')}>{book.bookPrice} vnd</p>
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
                    <Pagination listBook={listBook} />
                </div>
            </div>
        </DefaultLayout>
    );
};

export default EBookCase;
