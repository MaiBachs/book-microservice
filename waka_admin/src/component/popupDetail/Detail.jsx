import React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import styles from './Detail.module.scss';
import classNames from 'classnames/bind';
import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie'

const cx = classNames.bind(styles);

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 1000,
    height: 600,
    borderRadius: 0,
    bgcolor: 'background.paper',
    boxShadow: 24,
    p: 4,
    border: 2,
};

const Detail = (props) => {
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
                    </Typography>
                    <Typography sx={{ mt: 2 }}>
                    </Typography>
                    <Typography sx={{ mt: 2 }}>
                    </Typography>
                    <Typography>
                    </Typography>
                    <Typography>
                    </Typography>
                </Box>
            </Modal>
        </div>
    );
}

export default Detail;
