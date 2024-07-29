import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import styles from './PopupPayment.module.scss';
import classNames from 'classnames/bind';
import { useState } from 'react';
import toastr from 'toastr';

const cx = classNames.bind(styles);

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    height: 250,
    borderRadius: 3,
    bgcolor: 'background.paper',
    boxShadow: 24,
    p: 4,
};

function PopupPayment(props) {
    toastr.options = {
        positionClass: 'toast-top-center', // vị trí giữa bên trên màn hình
        toastClass: 'toastr-custom-style', // tùy chỉnh style cho toastr
    };
    console.log(props);

    return (
        <div>
            <Modal
                open={props.open}
                onClose={props.handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        <img src="https://ebook.waka.vn/themes/desktop/images/logo-waka.png" alt="logo" />
                    </Typography>
                    <Typography sx={{ mt: 2 }}>
                        <div className={cx('your-account')}>
                            <p style={{ color: 'black', textAlign: 'center' }}>
                                Tổng số tiền cần thanh toán là: {props.cost} vnd
                            </p>
                        </div>
                    </Typography>
                    <br />
                    <Typography>
                        <button className={cx('button-login')}>
                            <a className={cx('confirm-payment')} href={props.url.replace(/undefined$/, '')}>
                                Xác nhận thanh tóan
                            </a>
                        </button>
                    </Typography>
                </Box>
            </Modal>
        </div>
    );
}

export default PopupPayment;
