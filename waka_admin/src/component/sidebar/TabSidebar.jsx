import React from 'react';
import { useState } from 'react';
import styles from './TabSidebar.module.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const cx = classNames.bind(styles);

const TabSidebar = (props) => {
    let dataTab = props.dataTab;
    const navigate = useNavigate();

    const handleSearch = (event) => {
        navigate(dataTab.url);
    };

    return (
        <div className={cx('tab-wrapper')}>
            <a className={cx('link')} onClick={handleSearch}>{dataTab.title}</a>
        </div>
    );
}

export default TabSidebar;
