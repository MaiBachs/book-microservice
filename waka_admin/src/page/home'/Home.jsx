import React from 'react';
import DefaultLayout from '../../defaultLayout/DefaultLayout';
import styles from './Home.module.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

const Home = () => {
    return (
        <div className={cx("wrapper")}>
            <DefaultLayout></DefaultLayout>
        </div>
    );
}

export default Home;
