import axios from 'axios';
import React, { useEffect, useState } from 'react'
import styles from './AudioBook.module.scss';
import classNames from 'classnames/bind';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import CardAudioBook from '../../component/CardBook/CardAudioBook/CardAudioBook';

const cx = classNames.bind(styles);
const AudioBook = () => {
    const [listAudioBook, setListAudioBook] = useState([]);
    useEffect(()=>{
        axios.get('http://localhost:9191/api/podcast-service/audio-book/get-all-audio-book').then((response)=>{
            setListAudioBook(response.data.data);
        }).catch((error)=>{
            console.log(error);
        });
    }, [])
    return (
        <div className={cx('wrapper')}>
            <DefaultLayout check={false}>
                <div className={cx('slide')}>
                    <img src="https://ebook.waka.vn/themes/desktop/reactjs//images/bannerButton.jpg" />
                </div>
                <div className={cx('content')}>
                    {listAudioBook.map((audio) => {
                        return (
                            <div className={cx('audio')}>
                                <CardAudioBook audio={audio} />
                            </div>
                        );
                    })}
                </div>
            </DefaultLayout>
        </div>
    );
}

export default AudioBook;
