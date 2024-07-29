import React from 'react';
import styles from './CardAudioBook.module.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';

const cx = classNames.bind(styles);
const CardAudioBook = (props) => {
    return (
        <div className={cx('card-box')}>
            <div className={cx('card-body')}>
                <img src={props.audio.coverAudioBook}></img>
                <div className={cx('coating')}>
                    <span>
                        <Link to="" className={cx('hover-category')}>
                            {props.audio.audioBookName}
                        </Link>
                    </span>
                    <span>
                        <Link to="" className={cx('hover-branch')}>
                            {props.audio.audioBookAuthor}
                        </Link>
                    </span>
                    <button>
                        <Link className={cx('linkdetail-book')} to="/detailaudiobook" state={props.audio}>
                            Chi tiết
                        </Link>
                    </button>
                </div>
            </div>
            <div className={cx('card-footer')}>
                <span>Gói Hội viên</span>
            </div>
        </div>
    );
};

export default CardAudioBook;
