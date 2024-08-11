import React from 'react';
import styles from './HistoryPaymentAudioBook.module.scss';
import classNames from 'classnames/bind';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const cx = classNames.bind(styles);

const HistoryPaymentAudioBook = () => {
    const [historyList, setHistoryList] = useState([]);
    console.log(historyList);
    const navigate = useNavigate();

    useEffect(() => {
        axios
            .get('http://localhost:9191/api/book-service/book/get-history-payment-audio-book-by-user', {
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

    function handleDetailAudioBook(audioBookId) {
        axios
            .get(`http://localhost:9191/api/podcast-service/audio-book/detail-audio-book?audioBookId=${audioBookId}`)
            .then((response) => {
                navigate('/detailaudiobook', { state: response.data.data });
            })
            .catch((error) => {
                console.log(error);
            });
    }

    return (
        <DefaultLayout>
            <div className={cx('wrapper')}>
                <div className={cx('side-bar')}>
                    <div className={cx('pay-ebook-menu')}>
                        <Link to="/historypaymentbook" className={cx('pay-book-tab')}>
                            Thanh toán Ebook
                        </Link>
                    </div>
                    <div className={cx('pay-audio-book-menu')}>
                        <Link to="/historypaymentaudiobook" className={cx('pay-book-tab')}>
                            Thanh toán AudioBook
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
                                <th>Sách thanh toán</th>
                                <th>Nội dung thanh toán</th>
                                <th>Số tiền</th>
                                <th>Ngày thanh toán</th>
                            </tr>
                            {historyList.map((history) => {
                                return (
                                    <tr>
                                        <td>{history.codePayment}</td>
                                        <td>
                                            <Link
                                                onClick={() => {
                                                    handleDetailAudioBook(history.audioBook);
                                                }}
                                            >
                                                {history.bookName}
                                            </Link>
                                        </td>
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

export default HistoryPaymentAudioBook;
