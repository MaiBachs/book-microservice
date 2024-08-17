import React, { useState, useRef } from 'react';
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import classNames from 'classnames/bind';
import styles from './Chat.module.scss';
import Cookies from 'js-cookie';
import { FaImages } from 'react-icons/fa';

const cx = classNames.bind(styles);

const Chat = () => {
    const [mess, setMess] = useState([]);
    const [message, setMessage] = useState('');
    const messagesEnd = useRef();
    const socket = window._SOCKET_DATA;

    socket.on('chat', function (data) {
        if (
            mess.length == 0 ||
            data.userName !== Cookies.get('email') ||
            data.message !== mess[mess.length - 1].message
        ) {
            setMess([...mess, data]);
        }
    });

    function encodeImageFileAsURL(element) {
        return new Promise((resolve, reject) => {
            var file = element.files[0];
            var reader = new FileReader();
            reader.onloadend = function () {
                resolve(reader.result);
            };
            reader.onerror = function (error) {
                reject(error);
            };
            reader.readAsDataURL(file);
        });
    }

    const compressImage = async (file, { quality = 1, type = file.type }) => {
        // Get as image data
        const imageBitmap = await createImageBitmap(file);

        // Draw to canvas
        const canvas = document.createElement('canvas');
        canvas.width = imageBitmap.width;
        canvas.height = imageBitmap.height;
        const ctx = canvas.getContext('2d');
        ctx.drawImage(imageBitmap, 0, 0);

        // Turn into Blob
        const blob = await new Promise((resolve) => canvas.toBlob(resolve, type, quality));

        // Turn Blob into File
        return new File([blob], file.name, {
            type: blob.type,
        });
    };

    const sendMessage = async () => {
        let base64Image = null;
        console.log(document.getElementById('input-image'));

        let inputElement = document.getElementById('input-image');
        if (inputElement.files && inputElement.files[0]) {
            const compressedFile = await compressImage(inputElement.files[0], {
                quality: 0.9,
                type: 'image/jpeg',
            });
            const dataTransfer = new DataTransfer();
            dataTransfer.items.add(compressedFile);
            inputElement.files = dataTransfer.files;

            base64Image = await encodeImageFileAsURL(inputElement);
            base64Image = base64Image.toString();
        }
        console.log(base64Image);

        if (message !== null) {
            const msg = {
                userName: localStorage.getItem('email'),
                message: message,
                image: base64Image,
                actionTime: new Date(),
            };
            socket.emit('chat', msg);
            setMessage('');
            document.getElementById('input-image').value = null;
        }
    };

    const renderMess = mess.map((m, index) => {
        console.log(m);

        if (m.userName === localStorage.getItem('email')) {
            return (
                <div
                    style={{
                        display: 'block',
                    }}
                    key={index}
                    className={cx('your-message chat-item')}
                >
                    <p
                        style={{
                            display: 'block',
                            backgroundColor: '#1ba085',
                            marginLeft: '1em',
                            marginRight: '1em',
                            marginTop: '0.1em',
                            marginBottom: '0.2em',
                            borderRadius: '0.5em',
                            padding: '0.4em',
                            color: 'white',
                            float: 'right',
                            textAlign: 'right',
                            fontSize: '16px',
                        }}
                    >
                        {m.image && (
                            <img
                                src={m.image} // Thay đổi MIME type nếu cần
                                alt="Message Image"
                                style={{
                                    maxWidth: '1000px', // Điều chỉnh kích thước theo ý muốn
                                    maxHeight: '1000px',
                                    borderRadius: '0.3em',
                                    display: 'block',
                                    marginBottom: '0.5em',
                                }}
                            />
                        )}
                        {m.userName + ': ' + m.message}
                    </p>
                </div>
            );
        } else {
            return (
                <div
                    style={{
                        display: 'block',
                        width: 'fit-content',
                        backgroundColor: '#868686',
                        marginLeft: '1em',
                        marginRight: '1em',
                        marginTop: '0.1em',
                        marginBottom: '0.2em',
                        borderRadius: '0.5em',
                        padding: '0.4em',
                        color: 'white',
                        float: 'left',
                        textAlign: 'left',
                        fontSize: '16px',
                    }}
                    key={index}
                    className={cx('other-people chat-item')}
                >
                    {m.image && (
                        <img
                            src={m.image} // Thay đổi MIME type nếu cần
                            alt="Message Image"
                            style={{
                                maxWidth: '1000px', // Điều chỉnh kích thước theo ý muốn
                                maxHeight: '1000px',
                                borderRadius: '0.3em',
                                display: 'block',
                                marginBottom: '0.5em',
                            }}
                        />
                    )}
                    {m.userName + ': ' + m.message}
                </div>
            );
        }
    });

    const handleChange = (e) => {
        setMessage(e.target.value);
    };

    const onEnterPress = (e) => {
        if (e.keyCode == 13 && e.shiftKey == false) {
            sendMessage();
        }
    };

    return (
        <div>
            <DefaultLayout>
                <div className={cx('slide')}>
                    <img src="https://ebook.waka.vn/themes/desktop/reactjs//images/bannerButton.jpg" />
                </div>
                <div className={cx('box-chat')}>
                    <div className={cx('header-box-chat')}></div>
                    <div className={cx('box-chat_message')}>
                        {renderMess}
                        <div style={{ float: 'left', clear: 'both' }} ref={messagesEnd}></div>
                    </div>

                    <div className={cx('send-box')}>
                        <textarea
                            value={message}
                            onKeyDown={onEnterPress}
                            onChange={handleChange}
                            placeholder="Nhập tin nhắn ..."
                        />
                        <FaImages className={cx('image-button')} />
                        <input
                            type="file"
                            accept="image/*"
                            id="input-image"
                            style={{
                                position: 'absolute',
                                // bottom: '20px',
                                right: '345px',
                                width: '50px',
                                height: '30px',
                                opacity: 0,
                                cursor: 'pointer',
                            }}
                            // onChange={handleFileChange}
                        />
                        <button className={cx('button-send')} onClick={sendMessage}>
                            Send
                        </button>
                    </div>
                </div>
            </DefaultLayout>
        </div>
    );
};

export default Chat;
