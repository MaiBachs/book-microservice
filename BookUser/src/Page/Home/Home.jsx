import React from 'react';
import styles from './Home.module.scss';
import classNames from 'classnames/bind';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import Slide from '../../component/Slide/Slide';
import CardBookSmall from '../../component/CardBook/CardBookSmall/CardBookSmall.jsx';
import GrillEBook from '../../component/GrillBook/GrillEBook.jsx';
import { BsNewspaper } from 'react-icons/bs';
import MediaDate from '../../component/MediaDate/MediaDate';
import { useState, useEffect } from 'react';
import axios from 'axios';
import AliceCarousel from 'react-alice-carousel';
import 'react-alice-carousel/lib/alice-carousel.css';
import { Link } from 'react-router-dom';
import CardAudioBook from '../../component/CardBook/CardAudioBook/CardAudioBook.jsx';

const cx = classNames.bind(styles);

function Home() {
    let size = 9;
    const [listBookCT1, setListBookCT1] = useState({
        page: 1,
        totalPage: 0,
        bookEntityList: [],
    });
    const [listBookRecomend, setListBookRecomend] = useState([]);
    const [listAudioBook, setListAudioBook] = useState([]);
    const [listBookCT2, setListBookCT2] = useState([]);
    console.log(listBookRecomend);
    console.log(listAudioBook);
    console.log(listBookCT2);

    useEffect(() => {
        setTimeout(() => {
            document.getElementById('tab-all').click();
        }, 250);
        axios
            .get('http://localhost:9191/api/podcast-service/audio-book/get-all-audio-book')
            .then((response) => {
                setListAudioBook(response.data.data);
            })
            .catch((error) => {
                console.log(error);
            });

        axios
            .get(
                `http://localhost:9191/api/e-book-service/recomend-for-user?userId=${
                    localStorage.getItem('userId') ? localStorage.getItem('userId') : ''
                }`,
            )
            .then((response) => {
                setListBookRecomend(response.data.data);
            })
            .catch((error) => {
                console.log(error);
            });

        axios
            .get('http://localhost:9191/api/e-book-service/get-all-book')
            .then((response) => {
                setListBookCT2(response.data.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, [listBookCT1.page]);

    function clickTabAll() {
        if (document.getElementById('tab-all').style.color == '#1ba085') {
            document.getElementById('option-ebook-all').style.backgroundColor = 'white';
            document.getElementById('tab-all').style.color = '#1ba085';
        } else {
            document.getElementById('option-ebook-all').style.backgroundColor = '#1ba085';
            document.getElementById('tab-all').style.color = 'white';

            // change color other button
            document.getElementById('option-ebook-free').style.backgroundColor = 'white';
            document.getElementById('tab-free').style.color = '#1ba085';
            document.getElementById('option-ebook-member').style.backgroundColor = 'white';
            document.getElementById('tab-member').style.color = '#1ba085';
            document.getElementById('option-ebook-fee').style.backgroundColor = 'white';
            document.getElementById('tab-fee').style.color = '#1ba085';
        }

        axios
            .post('http://localhost:9191/api/e-book-service/get-book-by-page', {
                page: listBookCT1.page,
                size: size,
                bookType: -1,
            })
            .then((response) => {
                setListBookCT1(response.data.data);
            })
            .catch((error) => console.log(error));
    }

    function clickTabFree() {
        if (document.getElementById('tab-free').style.color == '#1ba085') {
            document.getElementById('option-ebook-free').style.backgroundColor = 'white';
            document.getElementById('tab-free').style.color = '#1ba085';
        } else {
            document.getElementById('option-ebook-free').style.backgroundColor = '#1ba085';
            document.getElementById('tab-free').style.color = 'white';

            document.getElementById('option-ebook-all').style.backgroundColor = 'white';
            document.getElementById('tab-all').style.color = '#1ba085';
            document.getElementById('option-ebook-member').style.backgroundColor = 'white';
            document.getElementById('tab-member').style.color = '#1ba085';
            document.getElementById('option-ebook-fee').style.backgroundColor = 'white';
            document.getElementById('tab-fee').style.color = '#1ba085';
        }

        axios
            .post('http://localhost:9191/api/e-book-service/get-book-by-page', {
                page: listBookCT1.page,
                size: size,
                bookType: 1,
            })
            .then((response) => {
                setListBookCT1(response.data.data);
            })
            .catch((error) => console.log(error));
    }

    function clickTabMember() {
        if (document.getElementById('tab-member').style.color == '#1ba085') {
            document.getElementById('option-ebook-member').style.backgroundColor = 'white';
            document.getElementById('tab-member').style.color = '#1ba085';
        } else {
            document.getElementById('option-ebook-member').style.backgroundColor = '#1ba085';
            document.getElementById('tab-member').style.color = 'white';

            document.getElementById('option-ebook-all').style.backgroundColor = 'white';
            document.getElementById('tab-all').style.color = '#1ba085';
            document.getElementById('option-ebook-free').style.backgroundColor = 'white';
            document.getElementById('tab-free').style.color = '#1ba085';
            document.getElementById('option-ebook-fee').style.backgroundColor = 'white';
            document.getElementById('tab-fee').style.color = '#1ba085';
        }

        axios
            .post('http://localhost:9191/api/e-book-service/get-book-by-page', {
                page: listBookCT1.page,
                size: size,
                bookType: 2,
            })
            .then((response) => {
                setListBookCT1(response.data.data);
            })
            .catch((error) => console.log(error));
    }

    function clickTabFee() {
        if (document.getElementById('tab-fee').style.color == '#1ba085') {
            document.getElementById('option-ebook-fee').style.backgroundColor = 'white';
            document.getElementById('tab-fee').style.color = '#1ba085';
        } else {
            document.getElementById('option-ebook-fee').style.backgroundColor = '#1ba085';
            document.getElementById('tab-fee').style.color = 'white';

            document.getElementById('option-ebook-all').style.backgroundColor = 'white';
            document.getElementById('tab-all').style.color = '#1ba085';
            document.getElementById('option-ebook-free').style.backgroundColor = 'white';
            document.getElementById('tab-free').style.color = '#1ba085';
            document.getElementById('option-ebook-member').style.backgroundColor = 'white';
            document.getElementById('tab-member').style.color = '#1ba085';
        }

        axios
            .post('http://localhost:9191/api/e-book-service/get-book-by-page', {
                page: listBookCT1.page,
                size: size,
                bookType: 3,
            })
            .then((response) => {
                setListBookCT1(response.data.data);
            })
            .catch((error) => console.log(error));
    }

    return (
        <div className={cx('wrapper')}>
            <DefaultLayout listBookCT2={listBookCT2} setListBookCT2={setListBookCT2} check={true}>
                <div>
                    <div className={cx('slide')}>
                        <Slide />
                    </div>
                    <div className={cx('content1')}>
                        <div className={cx('news')}>
                            <div className={cx('title-news')}>
                                <p>TIN TỨC</p>
                                <BsNewspaper className={cx('news-icon')} />
                            </div>
                            <ul>
                                <li>
                                    <MediaDate />
                                </li>
                            </ul>
                        </div>
                        <div className={cx('ebook-grill-box')}>
                            <div className={cx('title-ebook-grill')}>
                                <div id="option-ebook-all" className={cx('option-ebook-all')}>
                                    <a onClick={clickTabAll} id="tab-all" href="#">
                                        Tất cả sách
                                    </a>
                                </div>
                                <div id="option-ebook-free" className={cx('option-ebook-free')}>
                                    <a onClick={clickTabFree} id="tab-free" href="#">
                                        Miễn phí
                                    </a>
                                </div>
                                <div id="option-ebook-member" className={cx('option-ebook-member')}>
                                    <a onClick={clickTabMember} id="tab-member" href="#">
                                        Dành cho hội viên
                                    </a>
                                </div>
                                <div id="option-ebook-fee" className={cx('option-ebook-fee')}>
                                    <a onClick={clickTabFee} id="tab-fee" href="#">
                                        Sách trả phí
                                    </a>
                                </div>
                            </div>
                            <GrillEBook listBookCT1={listBookCT1} setListBookCT1={setListBookCT1} size={size} />
                        </div>
                    </div>
                    <div className={cx('content2')}>
                        <div className={cx('audio-book')}>
                            <div className={cx('title-audio')}>CÓ THỂ PHÙ HỢP VỚI BẠN</div>
                        </div>
                        <div className={cx('best-rank')}>
                            <AliceCarousel
                                mouseTracking
                                disableDotsControls
                                keyboardNavigation
                                items={listBookRecomend.map((bookCT2) => {
                                    return <CardBookSmall bookCT2={bookCT2} />;
                                })}
                                responsive={{
                                    1324: { items: 6 },
                                }}
                                renderPrevButton={() => (
                                    <button
                                        style={{
                                            position: 'absolute',
                                            marginTop: '-170px',
                                            marginLeft: '-550px',
                                            backgroundColor: 'transparent',
                                            border: 'none',
                                            fontSize: '35px',
                                            color: '#1ba085',
                                        }}
                                    >
                                        {'<'}
                                    </button>
                                )}
                                renderNextButton={() => (
                                    <button
                                        style={{
                                            position: 'absolute',
                                            marginTop: '-170px',
                                            marginLeft: '256px',
                                            backgroundColor: 'transparent',
                                            border: 'none',
                                            fontSize: '35px',
                                            color: '#1ba085',
                                        }}
                                    >
                                        {'>'}
                                    </button>
                                )}
                            />
                        </div>
                    </div>
                    <div className={cx('content3')}>
                        <div className={cx('audio-book')}>
                            <div className={cx('title-audio')}>SÁCH NÓI</div>
                            <div>
                                <Link className={cx('link-viewall')} to="/audiobook">
                                    Xem tất cả
                                </Link>
                            </div>
                        </div>
                        <div className={cx('best-rank')}>
                            <AliceCarousel
                                mouseTracking
                                disableDotsControls
                                keyboardNavigation
                                items={listAudioBook.map((audio) => {
                                    return <CardAudioBook audio={audio} />;
                                })}
                                responsive={{
                                    1324: { items: 6 },
                                }}
                                renderPrevButton={() => (
                                    <button
                                        style={{
                                            position: 'absolute',
                                            marginTop: '-170px',
                                            marginLeft: '-550px',
                                            backgroundColor: 'transparent',
                                            border: 'none',
                                            fontSize: '35px',
                                            color: '#1ba085',
                                        }}
                                    >
                                        {'<'}
                                    </button>
                                )}
                                renderNextButton={() => (
                                    <button
                                        style={{
                                            position: 'absolute',
                                            marginTop: '-170px',
                                            marginLeft: '256px',
                                            backgroundColor: 'transparent',
                                            border: 'none',
                                            fontSize: '35px',
                                            color: '#1ba085',
                                        }}
                                    >
                                        {'>'}
                                    </button>
                                )}
                            />
                        </div>
                    </div>
                </div>
            </DefaultLayout>
        </div>
    );
}

export default Home;
