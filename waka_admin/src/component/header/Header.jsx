import React from 'react';
// import { useState } from 'react';
import styles from './Header.module.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);
const Header = () => {
    return (
        <div className={cx("wrapper")}>
            <img className={cx('logo-waka')} src='https://waka.vn/images/logo.png'></img>
        </div>
    );
}

export default Header;
