import React, { useEffect, useState } from 'react';
import DefaultLayout from '../../defaultLayout/DefaultLayout';
import styles from './Home.module.scss';
import classNames from 'classnames/bind';
import axios from 'axios';

const cx = classNames.bind(styles);

const Home = () => {
    const [totalData, setTotalData] = useState({
        totalReadingBook: 0,
        totalAudioBook: 0,
        totalPaymentInMonth: 0,
        totalMoneyPaymentInMonth: 0,
        totalMember: 0,
        totalMemberRegisInMonth: 0,
    });

    useEffect(()=>{
        axios
            .get("http://localhost:9191/api/book-service/book/total-data-home")
            .then((response) => {
                setTotalData(response.data);
            })
            .catch();
    },[])

    return (
        <div className={cx("wrapper")}>
            <DefaultLayout>
                <div className={cx("data-box")}>
                    <div className={cx("grid-container")}>
                        <div className={cx("grid-item")}>
                            <p className={cx("title")}>Total Reading Book</p>
                            <p className={cx("value")}>{totalData.totalReadingBook}</p>
                        </div>
                        <div className={cx("grid-item")}>
                            <p className={cx("title")}>Total Audio Book</p>
                            <p className={cx("value")}>{totalData.totalAudioBook}</p>
                        </div>
                        <div className={cx("grid-item")}>
                            <p className={cx("title")}>Total Payment In Month</p>
                            <p className={cx("value")}>{totalData.totalPaymentInMonth}</p>
                        </div>
                        <div className={cx("grid-item")}>
                            <p className={cx("title")}>Total Money Payment</p>
                            <p className={cx("value")}>{totalData.totalMoneyPaymentInMonth} vnd</p>
                        </div>
                        <div className={cx("grid-item")}>
                            <p className={cx("title")}>Total Member</p>
                            <p className={cx("value")}>{totalData.totalMember}</p>
                        </div>
                        <div className={cx("grid-item")}>
                            <p className={cx("title")}>Member Regis In Month</p>
                            <p className={cx("value")}>{totalData.totalMemberRegisInMonth}</p>
                        </div>
                        <div className={cx("grid-item")}></div>
                        <div className={cx("grid-item")}></div>
                    </div>
                </div>
            </DefaultLayout>
        </div>
    );
}

export default Home;
