import React from 'react';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import styles from './HistoryPaymentRegiscard.module.scss';
import classNames from 'classnames/bind';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

const cx = classNames.bind(styles);

const HistoryPaymentRegiscard = () => {
    const [historyList, setHistoryList] = useState([]);

    useEffect(() => {
        axios
            .get('http://localhost:9191/api/book-service/book/get-history-payment-regiscard-by-user', {
                params: {
                    userId: localStorage.getItem('userId'),
                },
            })
            .then((response) => {
                setHistoryList(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    return (
        <DefaultLayout>
            <div className={cx('wrapper')}>
                <div className={cx('side-bar')}>
                    <div className={cx('pay-ebook-menu')}>
                        <Link to="/historypaymentbook" className={cx('pay-book-tab')}>
                            Thanh toán sách đọc
                        </Link>
                    </div>
                    <div className={cx('pay-audio-book-menu')}>
                        <Link to="/historypaymentaudiobook" className={cx('pay-book-tab')}>
                            Thanh toán sách nghe
                        </Link>
                    </div>
                    <div className={cx('pay-member-menu')}>
                        <Link to="/historypaymentregiscard" className={cx('pay-member-tab')}>
                            Thanh toán hội viên
                        </Link>
                    </div>
                </div>
                <div className={cx('content')}>
                    <table className={cx('books')}>
                        <tbody>
                            <tr>
                                <th>Mã thanh toán</th>
                                <th>Nội dung thanh toán</th>
                                <th>Số tiền</th>
                                <th>Ngày thanh toán</th>
                            </tr>
                            {historyList.map((history) => {
                                return (
                                    <tr>
                                        <td>{history.codePayment}</td>
                                        <td>{history.contentPayment}</td>
                                        <td>{history.moneyPayment}</td>
                                        <td>{history.createdDate.split('T1')[0]}</td>
                                    </tr>
                                );
                            })}
                        </tbody>
                    </table>
                </div>
            </div>
        </DefaultLayout>
    );
};

export default HistoryPaymentRegiscard;
