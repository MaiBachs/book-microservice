import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import CardAudioBook from '../CardBook/CardAudioBook/CardAudioBook';
import './GrillAudioBook.css';

const GrillAudioBook = (props) => {
    const list1 = [...props.listAudioBook.audioBooks];
    let list2 = [];
    let page = props.listAudioBook.page;
    let rowNum = props.size / 4;
    for (let i = 0; i < list1.length; i += 4) {
        if (rowNum === 3) {
            if (list1[i] && list1[i + 1] && list1[i + 2] && list1[i + 3]) {
                list2.push([...list1.slice(i, i + 4)]);
            } else if (list1[i] && list1[i + 1] && list1[i + 2]) {
                list2.push([...list1.slice(i, i + 3)]);
            } else if (list1[i] && list1[i + 1]) {
                list2.push([...list1.slice(i, i + 2)]);
            } else {
                list2.push([...list1.slice(i, i + 1)]);
            }
        }
    }
    let indexPage = [];
    for (let i = 1; i <= props.listAudioBook.totalPage; i++) {
        indexPage.push(i);
    }

    const handleChangePage = (index) => {
        if (index === 'previos') {
            props.setListAudioBook({
                ...props.listAudioBook,
                page: page - 1,
            });
        } else if (index === 'next') {
            props.setListAudioBook({
                ...props.listAudioBook,
                page: page + 1,
            });
        } else {
            props.setListAudioBook({
                ...props.listAudioBook,
                page: index,
            });
        }
    };
    return (
        <div class="wrapper">
            <div class="container ms-2 mt-2">
                <div class="row test">
                    {list2.map((bookList) => {
                        return (
                            <>
                                {bookList.map((book) => {
                                    return (
                                        <>
                                            <div class="col-3 p-2" style={{ right: '0px' }}>
                                                <CardAudioBook audio={book} />
                                            </div>
                                        </>
                                    );
                                })}
                            </>
                        );
                    })}
                </div>
            </div>
            <nav aria-label="..." className="pagin mt-3">
                <ul class="pagination pagination-sm justify-content-end mr-5">
                    {props.listAudioBook.page <= 1 ? (
                        <li class="page-item disabled" key={'previos'}>
                            <button class="page-link" tabindex="-1">
                                {'<'}
                            </button>
                        </li>
                    ) : (
                        <li
                            class="page-item"
                            key={'previos'}
                            onClick={() => {
                                handleChangePage('previos');
                            }}
                        >
                            <button class="page-link" tabindex="-1">
                                {'<'}
                            </button>
                        </li>
                    )}
                    {indexPage.map((index) => {
                        return (
                            <>
                                {index === props.listAudioBook.page ? (
                                    <li key={index} class="page-item active ms-2">
                                        <button class="page-link">{index}</button>
                                    </li>
                                ) : (
                                    <li
                                        key={index}
                                        class="page-item ms-2"
                                        onClick={() => {
                                            handleChangePage(index);
                                        }}
                                    >
                                        <button class="page-link">{index}</button>
                                    </li>
                                )}
                                ;
                            </>
                        );
                    })}
                    {props.listAudioBook.page >= props.listAudioBook.totalPage ? (
                        <li class="page-item disabled" key={'next'}>
                            <button class="page-link ms-2">{'>'}</button>
                        </li>
                    ) : (
                        <li
                            class="page-item"
                            key={'next'}
                            onClick={() => {
                                handleChangePage('next');
                            }}
                        >
                            <button class="page-link ms-2">{'>'}</button>
                        </li>
                    )}
                </ul>
            </nav>
        </div>
    );
};

export default GrillAudioBook;
