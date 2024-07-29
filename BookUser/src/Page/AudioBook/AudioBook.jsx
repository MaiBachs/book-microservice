import axios from 'axios';
import React, { useEffect, useState } from 'react';
import styles from './AudioBook.module.scss';
import classNames from 'classnames/bind';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import GrillAudioBook from '../../component/GrillBook/GrillAudioBook';

const cx = classNames.bind(styles);
const AudioBook = () => {
    const [listAudioBook, setListAudioBook] = useState({
        page: 1,
        totalPage: 0,
        audioBooks: [],
        audioBookType: null,
        categories: [],
    });
    let sizePage = 12;
    useEffect(() => {
        document.getElementById('all-value').click();
    }, []);
    useEffect(() => {
        let audioBookTypeSelected = checkTypeSelect();
        let categorySelected = checkCategory();
        axios
            .post('http://localhost:9191/api/podcast-service/audio-book/get-audio-book-by-category', {
                page: listAudioBook.page,
                size: sizePage,
                categories: categorySelected,
                audioBookType: audioBookTypeSelected,
            })
            .then((response) => {
                setListAudioBook({
                    ...listAudioBook,
                    page: response.data.data.page,
                    totalPage: response.data.data.totalPage,
                    audioBooks: response.data.data.audioBooks,
                });
            })
            .catch((error) => {
                console.log(error);
            });
    }, [listAudioBook.page]);
    const listCategory = [
        'Doanh nhân - Bài học kinh doanh',
        'Trinh thám - Kinh dị',
        'Viễn tưởng - Giả tưởng',
        'Khởi nghiệp - Làm giàu',
        'Marketing - Bán hàng',
        'Quản trị - Lãnh đạo',
        'Tài chính cá nhân',
        'Phát triển cá nhân',
    ];

    function handleChoiceOption() {
        let categorySelected = checkCategory();
        let audioBookTypeSelected = checkTypeSelect();
        axios
            .post('http://localhost:9191/api/podcast-service/audio-book/get-audio-book-by-category', {
                page: listAudioBook.page,
                size: sizePage,
                categories: categorySelected,
                audioBookType: audioBookTypeSelected,
            })
            .then((response) => {
                setListAudioBook({
                    ...listAudioBook,
                    page: response.data.data.page,
                    totalPage: response.data.data.totalPage,
                    audioBooks: response.data.data.audioBooks,
                });
            })
            .catch((error) => {
                console.log(error);
            });
    }

    function checkCategory() {
        let categorySelected = [];
        for (let i = 0; i < listCategory.length; i++) {
            let radio = document.getElementById(listCategory[i]);
            if (radio && radio.checked) {
                categorySelected.push(radio.value);
            }
        }
        return categorySelected;
    }

    function checkTypeSelect() {
        let audioBookTypeSelected = null;
        let radioAll = document.getElementById('all-value');
        let radioFree = document.getElementById('free-value');
        let radioMember = document.getElementById('member-value');
        let radioFee = document.getElementById('fee-value');
        if (radioAll && radioAll.checked) {
            audioBookTypeSelected = radioAll.value;
        }
        if (radioFree && radioFree.checked) {
            audioBookTypeSelected = radioFree.value;
        }
        if (radioMember && radioMember.checked) {
            audioBookTypeSelected = radioMember.value;
        }
        if (radioFee && radioFee.checked) {
            audioBookTypeSelected = radioFee.value;
        }
        return audioBookTypeSelected;
    }

    return (
        <div className={cx('wrapper')}>
            <DefaultLayout check={false}>
                <div className={cx('slide')}>
                    <img src="https://i.imgur.com/L3IoQwS.png" />
                </div>
                <div className={cx('content')}>
                    <div className={cx('sidebar-box')}>
                        <div className={cx('type-book-title')}>Chọn sách</div>
                        <div className={cx('sidebar-book-type')}>
                            <div className={cx('grid-container-value')}>
                                <div className={cx('grid-item')}>
                                    <input
                                        onClick={() => {
                                            handleChoiceOption();
                                        }}
                                        value={null}
                                        id="all-value"
                                        name="group-radio-type"
                                        type="radio"
                                    />
                                </div>
                                <div className={cx('grid-item')}>
                                    <input
                                        onClick={() => {
                                            handleChoiceOption();
                                        }}
                                        value={1}
                                        id="free-value"
                                        name="group-radio-type"
                                        type="radio"
                                    />
                                </div>
                                <div className={cx('grid-item')}>
                                    <input
                                        onClick={() => {
                                            handleChoiceOption();
                                        }}
                                        value={2}
                                        id="member-value"
                                        name="group-radio-type"
                                        type="radio"
                                    />
                                </div>
                                <div className={cx('grid-item')}>
                                    <input
                                        onClick={() => {
                                            handleChoiceOption();
                                        }}
                                        value={3}
                                        id="fee-value"
                                        name="group-radio-type"
                                        type="radio"
                                    />
                                </div>
                            </div>
                            <div className={cx('grid-container-lable')}>
                                <div className={cx('grid-item')}>Tất cả</div>
                                <div className={cx('grid-item')}>Miễn phí</div>
                                <div className={cx('grid-item')}>Dành cho member waka</div>
                                <div className={cx('grid-item')}>Sách trả phí</div>
                            </div>
                        </div>

                        <div className={cx('category-title')}>Thể loại sách</div>
                        <div className={cx('sidebar-category')}>
                            <div className={cx('grid-container-value')}>
                                {listCategory.map((item, i) => {
                                    return (
                                        <div className={cx('grid-item')}>
                                            <input
                                                id={item}
                                                onClick={() => {
                                                    handleChoiceOption(null, item);
                                                }}
                                                value={item}
                                                type="checkbox"
                                            />
                                        </div>
                                    );
                                })}
                            </div>
                            <div className={cx('grid-container-lable')}>
                                {listCategory.map((item) => {
                                    return <div className={cx('grid-item')}>{item}</div>;
                                })}
                            </div>
                        </div>
                    </div>
                    <GrillAudioBook listAudioBook={listAudioBook} setListAudioBook={setListAudioBook} size={sizePage} />
                </div>
            </DefaultLayout>
        </div>
    );
};

export default AudioBook;
