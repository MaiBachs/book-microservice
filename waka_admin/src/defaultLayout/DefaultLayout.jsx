import React from 'react';
import Header from '../component/header/Header';
import Sidebar from '../component/sidebar/Sidebar';
import styles from './DefaultLayout.module.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

const DefaultLayout = ({children}) => {
    return (
        <div className={cx("wrapper")}>
            <div className={cx('header')}>
                <Header/>
            </div>
            <div className={cx('body')}>
                <div className={cx('sidebar')}><Sidebar/></div>
                <div className={cx('content')}>{children}</div>
            </div>
        </div>
    );
}

export default DefaultLayout;
