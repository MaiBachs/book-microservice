import React from 'react';
import DefaultLayout from '../../defaultLayout/DefaultLayout';
import styles from './AddNewReadingBook.module.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';

const cx = classNames.bind(styles);

const AddNewReadingBook = () => {
    return (
        <div className={cx('wrapper')}>
          <DefaultLayout>
            <div className={cx("header-tab")}>
              <Link className={cx("reading-book-management")} to="/readingbookmanagement">
                Reading book management
              </Link>
              <Link className={cx("add-reading-book")} to="/addnewreadingbook" >
                Add new reading book
              </Link>
            </div>
          </DefaultLayout>
        </div>
        );
}

export default AddNewReadingBook;
