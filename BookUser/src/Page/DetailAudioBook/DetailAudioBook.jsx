import React from 'react';
import styles from './DetailAudioBook.module.scss';
import classNames from 'classnames/bind';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { AiFillHeart } from 'react-icons/ai';
import { Link } from 'react-router-dom';
import ReactAudioPlayer from 'react-audio-player';

const cx = classNames.bind(styles);

const DetailAudioBook = () => {
    const location = useLocation();
    const audio = location.state;

    const [showPlayer, setShowPlayer] = useState(false);
    const handleButtonClick = () => {
        setShowPlayer(true);
      };

    return (
        <DefaultLayout>
            <div className={cx('wrapper')}>
                <div className={cx('category')}></div>
                <div className={cx('content')}>
                    <div className={cx('content-left')}>
                        <div className={cx('content-left-top')}>
                            <div className={cx('cover')}>
                                <img src={audio.coverAudioBook}></img>
                            </div>
                        </div>
                        <div className={cx('content-left-bottom')}>
                            <div>
                                <AiFillHeart className={cx('heart-icon')} />
                            </div>
                            <span>Đánh giá sách</span>
                        </div>
                    </div>
                    <div className={cx('content-right')}>
                        <div className={cx('book-detail')}>
                            <h4>{audio.audioBookName}</h4>
                            <span>Tác giả: {audio.audioBookAuthor}</span>
                            <span>Thể loại: {audio.audioBookCategory}</span>
                            <span>Nhà xuất bản: updating</span>
                            <span>Ngày cập nhật: updating</span>
                            <span>Gói cước áp dụn: Hội viên</span>
                            <button onClick={handleButtonClick}>
                                    Nghe sách
                            </button>
                            <p>{audio.audioBookDescription}</p>
                        </div>
                    </div>
                </div>
                {showPlayer && (
                    <div className={cx('posdcast-audio-wrapper')}>
                    <ReactAudioPlayer
                    style={{width:'800px', height:'68px'}}
                    src={audio.preview}
                    autoPlay
                    controls
                    />
                </div>
                )}
            </div>
        </DefaultLayout>
    );
}

export default DetailAudioBook;
