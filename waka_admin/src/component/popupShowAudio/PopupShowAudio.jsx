import React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import AudioPlayer from 'react-h5-audio-player';
import 'react-h5-audio-player/lib/styles.css';

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 900,
    height: 140,
    borderRadius: 0,
    bgcolor: 'background.paper',
    boxShadow: 24,
    // p: 4,
};

const PopupShowAudio = (props) => {
    return (
        <Modal
                open={props.open}
                onClose={props.handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2" style={{backgroundColor: '#04AA6D', color: 'white'}}>
                        <div style={{marginLeft: '30px'}}>Show audio file</div>
                    </Typography>
                    <Typography >
                        <AudioPlayer src={props.preview} autoPlay controls />
                    </Typography>
                </Box>
            </Modal>
    );
}

export default PopupShowAudio;
