import axios from 'axios';
import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import styles from './PdfFileView.module.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);
const PdfFileView = (props) => {
    const location = useLocation();
    const bookCT2 = location.state;
    
    return (
        <div className={cx("wrapper")}>
            {/* <ReactPDF
            file={{
                data: bookContent
            }}
            /> */}
            <object data={`http://localhost:9191/api/e-book-service/download?pdfFileName=${bookCT2.preview}`} type="application/pdf" width="100%" height="100%">
                <p>Alternative text</p>
            </object>
        </div>
    );
}

export default PdfFileView;
