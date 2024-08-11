import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap.min.css';

const Pagination = (props) => {
    let page = props.listBook.page;
    let indexPage = [];
    for (let i = 1; i <= props.listBook.totalPage; i++) {
        indexPage.push(i);
    }
    const handleChangePage = (index) => {
        if (index === 'previos') {
            props.setListBookCT1({
                ...props.listBook,
                page: page - 1,
            });
        } else if (index === 'next') {
            props.setListBookCT1({
                ...props.listBook,
                page: page + 1,
            });
        } else {
            props.setListBookCT1({
                ...props.listBook,
                page: index,
            });
        }
    };

    return (
        <nav aria-label="..." className="pagin mt-3">
            <ul class="pagination pagination-sm justify-content-end mr-5">
                {props.listBook.page <= 1 ? (
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
                            {index === props.listBook.page ? (
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
                {props.listBook.page >= props.listBook.totalPage ? (
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
    );
};

export default Pagination;
