import React, { useEffect, useState } from 'react';
import DefaultLayout from '../../defaultLayout/DefaultLayout';
import styles from './PaymentMemberMn.module.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import axios from 'axios';

const cx = classNames.bind(styles);

const PaymentMemberMn = () => {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0'); // Tháng bắt đầu từ 0
    const previousMonth = String(today.getMonth()).padStart(2, '0'); // Tháng bắt đầu từ 0
    const day = String(today.getDate()).padStart(2, '0');

    const formattedFromDate = `${year}-${previousMonth}-${day}`;
    const formattedToDate = `${year}-${month}-${day}`;

    const [fromDate, setFromDate] = useState(formattedFromDate);
    const [toDate, setToDate] = useState(formattedToDate);

    const handleInputFromDate = (event) => {
        setFromDate(event.target.value);
    };
    const handleInputToDate = (event) => {
        setToDate(event.target.value);
    };

    const convertDateToString = (dateString) => {
        const [year, month, day] = dateString.split('-');
        return `${day}/${month}/${year}`;
    };
    
    const [listDataResultSearch, setListDataResultSearch] = useState([]); 

    const [dataSearch, setDataSearch] = useState({
        term: null,
        userName: "",
        fromDate: convertDateToString(formattedFromDate),
        toDate: convertDateToString(formattedToDate),
        page: 1,
        size: 10
    })

    function handleInputSearch(event) {
        setDataSearch({
          ...dataSearch,
          [event.target.name]: event.target.value
        })
    }

    useEffect(()=>{
        axios
        .post("http://localhost:9191/api/book-service/book/search-payment-member", dataSearch)
        .then((response) => {
            setListDataResultSearch(response.data);
        })
        .catch();
    }, [])

    function handleSearch() {
        axios
            .post("http://localhost:9191/api/book-service/book/search-payment-member", dataSearch)
            .then((response) => {
                setListDataResultSearch(response.data);
            })
            .catch();
    };

    async function handleExport() {
        const response = await axios.post(
        `http://localhost:9191/api/book-service/book/report-payment-member`,
        dataSearch,
        { responseType: "arraybuffer" }
        );
        const blob = new Blob([response.data], { type: 'application/vnd.ms-excel' });
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'Export report member.xls');
        document.body.appendChild(link);
        link.click();
        link.remove();
    };  

    return (
        <DefaultLayout>
            <div className={cx("header-tab")}>
                <Link className={cx("reading-book-management")} to="/payment-management-reading-book">
                    Payment Ebook
                </Link>
                <Link className={cx("add-reading-book")} to="/payment-management-audio-book" >
                    Payment AudioBook
                </Link>
                <Link className={cx("add-reading-book")} to="/payment-member" >
                    Payment Member
                </Link>
            </div>
            <div className={cx("grid-container")}>
                <div className={cx("grid-item")}>
                <label>User_name</label>
                </div>
                <div className={cx("grid-item")}>
                    <input name="userName" type='text' value={dataSearch.userName} onChange={(event) => { handleInputSearch(event) }}></input>
                </div>
                <div className={cx("grid-item")}></div>
                <div className={cx("grid-item")}></div>
                <div className={cx("grid-item")}>
                    <label>Term</label>
                </div>
                <div className={cx("grid-item")}>
                    <select name="term" onChange={(event) => { handleInputSearch(event) }}>
                        <option value="" >All</option>
                        <option value="3" >3 month</option>
                        <option value="6" >6 month</option>
                        <option value="12" >12 month</option>
                    </select>
                </div>
                <div className={cx("grid-item")}></div>
                <div className={cx("grid-item")}></div>
                <div className={cx("grid-item")}></div>
                <div className={cx("grid-item")}>
                    <label>From_date</label>
                </div>
                <div className={cx("grid-item")}>
                    <input style={{width: '189px'}} name="fromDate" type='date' value={fromDate} onChange={(event) => { handleInputFromDate(event) }}></input>
                </div>
                <div className={cx("grid-item")}>
                </div>
                c
                <div className={cx("grid-item")}>
                    <label>To_date</label>
                </div>
                <div className={cx("grid-item")}>
                    <input style={{width: '189px'}} name="toDate" type='date' value={toDate} onChange={(event) => { handleInputToDate(event) }}></input>
                </div>
                <div className={cx("grid-item")}></div>
                <div className={cx("grid-item")}>
                    
                </div>
                <div className={cx("grid-item")}>
                    
                </div>
            </div>
            <div className={cx("button-box")}>
                <button className={cx('button-search')} onClick={handleSearch}>Search</button>
                <button className={cx('button-export-report')} onClick={handleExport} >Export report</button>
            </div>
            <div className={cx("result-search")}>
                <table className={cx("books")}>
                    <tbody>
                    <tr>
                        <th>Stt</th>
                        <th>Code payment</th>
                        <th>User name</th>
                        <th>Term</th>
                        <th>Payments</th>
                        <th>Payment date</th>
                        <th>Content payment</th>
                        <th>Start date</th>
                        <th>End date</th>
                    </tr>
                    {listDataResultSearch.map((pm, i) => (
                        <>
                            <tr key={i}>
                            <td>{i + 1}</td>
                            <td>{pm.codePayment}</td>
                            <td>{pm.userName}</td>
                            <td>{pm.term}</td>
                            <td>{pm.moneyPayment}</td>
                            <td>{pm.createdDate.split("T")[0]}</td>
                            <td>{pm.contentPayment}</td>
                            <td>{pm.startDate.split("T")[0]}</td>
                            <td>{pm.endDate.split("T")[0]}</td>
                        </tr>
                        </>
                    ))}
                    </tbody>
                </table>
            </div>
        </DefaultLayout>
    );
}

export default PaymentMemberMn;
