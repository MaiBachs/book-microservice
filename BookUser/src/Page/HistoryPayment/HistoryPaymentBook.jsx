import React from 'react';
import styles from './HistoryPaymentBook.module.scss';
import classNames from 'classnames/bind';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const cx = classNames.bind(styles);

const HistoryPaymentBook = () => {
    return (
        <DefaultLayout>
            <div className={cx('wrapper')}>
                <div className={cx('side-bar')}>
                    <div className={cx('pay-book-menu')}>
                        <Link to="#" className={cx('pay-book-tab')}>
                            Thanh toán sách
                        </Link>
                    </div>
                    <div className={cx('pay-member-menu')}>
                        <Link to="#" className={cx('pay-member-tab')}>
                            Thanh toán hội viên
                        </Link>
                    </div>
                </div>
                <div className={cx('content')}>
                    <table className={cx('books')}>
                        <tbody>
                            <tr>
                                <th>Mã thanh toán</th>
                                <th>Sách thanh toán</th>
                                <th>Nội dung thanh toán</th>
                                <th>Số tiền</th>
                                <th>Ngày thanh toán</th>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </DefaultLayout>
    );
};

export default HistoryPaymentBook;
