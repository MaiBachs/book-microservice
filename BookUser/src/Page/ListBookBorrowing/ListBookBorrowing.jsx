import React from 'react';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import { useEffect, useState } from 'react';
import axios from 'axios';
import styles from './ListBookBorrowing.module.scss';
import classNames from 'classnames/bind';
import CardBookSmall from '../../component/CardBook/CardBookSmall/CardBookSmall';

const cx = classNames.bind(styles);

function ListBookBorrowing() {
    const [listBookBorrowing, setListBookBorrowing] = useState([]);
    const [listBorrowing, setListBorrowing] = useState([]);
    useEffect(() => {
        axios
            .post('http://localhost:8083/check-account', { userName: localStorage.getItem('userName') })
            .then((response) => {
                axios
                    .post('http://localhost:8083/findbr-by-customer', { customerPhone: response.data.phoneNumber })
                    .then((response1) => {
                        setListBorrowing(response1.data);
                        listBorrowing.map((bookBorrowing) => {
                            setListBookBorrowing(bookBorrowing.bookEntityList);
                        });
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            });
    });

    return (
        <div className={cx('wrapper')}>
            <DefaultLayout check={false}>
                <div className={cx('content')}>
                    <div className={cx('table-borrow')}>
                        <table className={cx('table')}>
                            <tr>
                                <th>BorrowDate</th>
                                <th>DueDate</th>
                                <th>Returned</th>
                                <th>BookList</th>
                            </tr>
                            {listBorrowing.map((borrowing) => {
                                return (
                                    <tr>
                                        <td>{borrowing.borrowDate}</td>
                                        <td>{borrowing.dueDate}</td>
                                        <td>{borrowing.returned.toString()}</td>
                                        <td>
                                            {borrowing.bookEntityList.map((book) => {
                                                return <p>{book.bookName}</p>;
                                            })}
                                        </td>
                                    </tr>
                                );
                            })}
                        </table>
                    </div>
                    {listBookBorrowing.map((book) => {
                        return (
                            <div className={cx('book')}>
                                <CardBookSmall bookCT2={book} />
                            </div>
                        );
                    })}
                </div>
            </DefaultLayout>
        </div>
    );
}

export default ListBookBorrowing;
