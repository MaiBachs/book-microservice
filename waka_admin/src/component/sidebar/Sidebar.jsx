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
            <TabSidebar dataTab={{url: "", title: "Home"}}></TabSidebar>
            <TabSidebar dataTab={{url: "/readingbookmanagement", title: "Reading Book"}}></TabSidebar>
            <TabSidebar dataTab={{url: "", title: "Audio Book"}}></TabSidebar>
            <TabSidebar dataTab={{url: "", title: "Member management"}}></TabSidebar>
        </div>
    );
}

export default Sidebar;
