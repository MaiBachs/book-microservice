import React from 'react';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import styles from './BookCase.module.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

const BookCase = () => {
    return (
        <div>
            <DefaultLayout>
                <div className={cx("wrapper")}>
                    <div className={cx("header-tab")}>
                        <div className={cx("reading-book")}>Reading book</div>
                        <div className={cx("audio-book")}>Audio book</div>
                    </div>
                    <div className={cx("content")}>
                        
                    </div>
                </div>
            </DefaultLayout>
        </div>
    );
}

export default BookCase;
