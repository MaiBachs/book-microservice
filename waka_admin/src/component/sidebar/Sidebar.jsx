import React from 'react';
import { useState } from 'react';
import styles from './Sidebar.module.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import TabSidebar from './TabSidebar';

const cx = classNames.bind(styles);

const Sidebar = () => {
    return (
        <div className={cx('wrapper')}>
            <div className={cx('menu')}>Menu</div>
            <TabSidebar dataTab={{url: "/home", title: "Home"}}></TabSidebar>
            <TabSidebar dataTab={{url: "/readingbookmanagement", title: "Reading Book"}}></TabSidebar>
            <TabSidebar dataTab={{url: "/audiobookmanagement", title: "Audio Book"}}></TabSidebar>
            <TabSidebar dataTab={{url: "/payment-management-reading-book", title: "Payment Management"}}></TabSidebar>
            <TabSidebar dataTab={{url: "", title: "Member Management"}}></TabSidebar>
        </div>
    );
}

export default Sidebar;
