import React from 'react';
import styles from './Header.module.scss';
import classNames from 'classnames/bind';
import { AiOutlineSearch, AiOutlineUnorderedList, AiFillCreditCard, AiOutlineHome } from 'react-icons/ai';
import { Link } from 'react-router-dom';
import { Menu } from 'antd';
import Tippy from '@tippyjs/react';
import 'tippy.js/dist/tippy.css'; // optional
import { useState } from 'react';
import Login from './Login';
import { useNavigate } from 'react-router-dom';
import Register from './Register';
import { CiMenuBurger } from 'react-icons/ci';
import { MdMenuBook } from 'react-icons/md';
import { MdWorkHistory } from 'react-icons/md';
import { FaList } from 'react-icons/fa';
import { IoLogOutOutline } from 'react-icons/io5';

const { SubMenu } = Menu;

const cx = classNames.bind(styles);

function Header(props) {
    const navigate = useNavigate();
    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const [open1, setOpen1] = useState(false);
    const handleOpen1 = () => setOpen1(true);
    const handleClose1 = () => setOpen1(false);

    const [value, setValue] = useState('');

    let resultSearch = [];
    const handleChange = (event) => {
        setValue(event.target.value);
        handleMouseEnter(event);
    };
    const listCategory = [
        'Trinh thám - Kinh dị',
        'Quản trị - Lãnh đạo',
        'Khởi nghiệp - Làm giàu',
        'Marketing - Bán hàng',
        'Ngôn tình',
        'Tài chính cá nhân',
        'Phát triển cá nhân',
        'Doanh nhân - Bài học kinh doanh',
    ];

    const handleSearch = (event) => {
        if (event.keyCode === 13) {
            if (value === '') {
                return;
            }
            resultSearch = [
                ...props.listBookCT2.filter((book) => {
                    return book.bookName.toLowerCase().includes(value.toLowerCase());
                }),
            ];

            navigate('/listbooksearch', { state: [...resultSearch] });
        }
    };
    const handleSearch1 = (event) => {
        if (value === '') {
            return;
        }
        resultSearch = [
            ...props.listBookCT2.filter((book) => {
                return book.bookName.toLowerCase().includes(value.toLowerCase());
            }),
        ];

        navigate('/listbooksearch', { state: [...resultSearch] });
    };

    const handleLogout = () => {
        if (window.confirm('Bạn có chắc chắn muốn đăng xuất?')) {
            navigate('/home');
            localStorage.clear();
        } else {
            return;
        }
    };
    const handleClickSearch = () => {
        navigate('/home');
    };

    const handleMouseEnter = (event) => {
        if (props.check === true) {
            event.target.focus();
        }
    };

    function displayMenuAcount() {
        if (document.getElementById('dropdown').style.display == 'block') {
            document.getElementById('dropdown').style.display = 'none';
        } else {
            document.getElementById('dropdown').style.display = 'block';
        }
    }

    document.addEventListener('mouseup', function (event) {
        var pol = document.getElementById('dropdown');
        if (pol == null) {
            return;
        }
        if (event.target != pol && event.target.parentNode != pol) {
            pol.style.display = 'none';
        }
    });

    return (
        <div className={cx('wrapper')}>
            <div className={cx('header-top')}>
                <div className={cx('logo-search')}>
                    <div className={cx('logo')}>
                        <img src="https://ebook.waka.vn/themes/desktop/images/logo-waka.png?v=1" alt="logo" />
                    </div>
                    <div className={cx('search')}>
                        <input
                            autofocus={true}
                            placeholder="Nhập tên sách"
                            value={value}
                            onChange={(event) => {
                                handleChange(event);
                            }}
                            onKeyDown={handleSearch}
                            onClick={handleClickSearch}
                            onMouseEnter={handleMouseEnter}
                        ></input>
                        {value !== '' && (
                            <ul className={cx('result-search')}>
                                {typeof props.listBookCT2 !== 'undefined' &&
                                    props.listBookCT2
                                        .filter((book) => {
                                            if (value === '') {
                                                return 1 < 0;
                                            }
                                            return book.bookName.toLowerCase().includes(value.toLowerCase());
                                        })
                                        .map((book) => {
                                            return (
                                                <li className={cx('list-book-search-li')}>
                                                    <Link
                                                        className={cx('list-book-search')}
                                                        to="/detailbook"
                                                        state={book}
                                                    >
                                                        {book.bookName}
                                                    </Link>
                                                </li>
                                            );
                                        })}
                            </ul>
                        )}
                        <button onClick={handleSearch1}>
                            <AiOutlineSearch className={cx('search-icon')} />
                            Tìm kiếm
                        </button>
                    </div>
                </div>
                <div className={cx('menu')}>
                    <a>
                        <img src="https://ebook.waka.vn/themes/desktop/images/hieu-soi.png" alt="logo"></img>
                    </a>
                    {localStorage.getItem('token') && (
                        <Tippy content="Đăng kí thẻ hội viên" className={cx('tippy')} theme="tomato">
                            <Link to="/registercard">
                                <AiFillCreditCard
                                    className={cx('card-icon')}
                                    style={{ color: '#eeb815', fontSize: '46px', paddingTop: '10px' }}
                                />
                            </Link>
                        </Tippy>
                    )}
                    {localStorage.getItem('token') ? (
                        <>
                            {/* <button className={cx('menu-account')} onClick={handleLogout} >{localStorage.getItem('userName')}</button> */}
                            <button className={cx('menu-account')} onClick={displayMenuAcount}>
                                <CiMenuBurger className={cx('menu-icon')} /> {localStorage.getItem('userName')}
                            </button>
                            <div id="dropdown" className={cx('dropdown')}>
                                <p>
                                    <Link to="/ebookcase" style={{ height: '100%' }}>
                                        <MdMenuBook style={{ marginTop: '-10x' }} /> Tủ sách của bạn
                                    </Link>
                                </p>
                                <p>
                                    <Link to="/historypaymentbook">
                                        <MdWorkHistory style={{ marginTop: '-4px' }} /> Lịch sử giao dich
                                    </Link>
                                </p>
                                <p>
                                    <Link to="#">
                                        <FaList style={{ marginTop: '-4px' }} /> Danh sách phát
                                    </Link>
                                </p>
                                <p className={cx('logout')}>
                                    <Link to="#" style={{ color: 'white' }} onClick={handleLogout}>
                                        <IoLogOutOutline style={{ marginTop: '-4px' }} /> ĐĂNG XUẤT
                                    </Link>
                                </p>
                            </div>
                        </>
                    ) : (
                        <button className={cx('login')} onClick={handleOpen}>
                            ĐĂNG NHẬP
                        </button>
                    )}
                    <Login open={open} handleOpen={handleOpen} handleClose={handleClose} handleOpen1={handleOpen1} />
                    <Register open1={open1} handleOpen1={handleOpen1} handleClose1={handleClose1} />
                </div>
            </div>
            <div className={cx('header-bottom')}>
                <ul className={cx('nav')}>
                    <li>
                        <span>
                            <AiOutlineUnorderedList className={cx('list-icon')} />
                        </span>
                        <div>
                            <Menu mode="vertical" className={cx('menu')}>
                                <SubMenu className={cx('submenu')} title="Danh mục Ebook">
                                    <Menu.Item key="setting:0">
                                        <Link
                                            style={{ textDecoration: 'none' }}
                                            to="/bookbycategory"
                                            state={listCategory[0]}
                                        >
                                            {listCategory[0]}
                                        </Link>
                                    </Menu.Item>
                                    <Menu.Item key="setting:2">
                                        <Link
                                            style={{ textDecoration: 'none' }}
                                            to="/bookbycategory"
                                            state={listCategory[1]}
                                        >
                                            {listCategory[1]}
                                        </Link>
                                    </Menu.Item>
                                    <Menu.Item key="setting:3">
                                        <Link
                                            style={{ textDecoration: 'none' }}
                                            to="/bookbycategory"
                                            state={listCategory[2]}
                                        >
                                            {listCategory[2]}
                                        </Link>
                                    </Menu.Item>
                                    <Menu.Item key="setting:4">
                                        <Link
                                            style={{ textDecoration: 'none' }}
                                            to="/bookbycategory"
                                            state={listCategory[3]}
                                        >
                                            {listCategory[3]}
                                        </Link>
                                    </Menu.Item>
                                    <Menu.Item key="setting:5">
                                        <Link
                                            style={{ textDecoration: 'none' }}
                                            to="/bookbycategory"
                                            state={listCategory[4]}
                                        >
                                            {listCategory[4]}
                                        </Link>
                                    </Menu.Item>
                                    <Menu.Item key="setting:6">
                                        <Link
                                            style={{ textDecoration: 'none' }}
                                            to="/bookbycategory"
                                            state={listCategory[5]}
                                        >
                                            {listCategory[5]}
                                        </Link>
                                    </Menu.Item>
                                    <Menu.Item key="setting:7">
                                        <Link
                                            style={{ textDecoration: 'none' }}
                                            to="/bookbycategory"
                                            state={listCategory[6]}
                                        >
                                            {listCategory[6]}
                                        </Link>
                                    </Menu.Item>
                                    <Menu.Item key="setting:8">
                                        <Link
                                            style={{ textDecoration: 'none' }}
                                            to="/bookbycategory"
                                            state={listCategory[7]}
                                        >
                                            {listCategory[7]}
                                        </Link>
                                    </Menu.Item>
                                </SubMenu>
                            </Menu>
                        </div>
                    </li>
                    <li>
                        <Link to="/home" className={cx('link-menu')}>
                            <AiOutlineHome className={cx('home-icon')} />
                            Trang chủ
                        </Link>
                    </li>
                    <li>
                        <Link className={cx('link-menu')} to="/topbook">
                            Bảng xếp hạng
                        </Link>
                    </li>
                    <li>
                        <Link className={cx('link-menu')} to="/chat">
                            Thảo luận trực tuyến
                        </Link>
                    </li>
                    <li>
                        <div style={{ fontSize: '14px' }}>------</div>
                    </li>
                    <li>
                        <Link className={cx('link-menu')} to="/audiobook">
                            Sách nói
                        </Link>
                    </li>
                    <li>
                        <Link className={cx('link-menu-not')}>PodCast</Link>
                    </li>
                </ul>
            </div>
        </div>
    );
}

export default Header;
