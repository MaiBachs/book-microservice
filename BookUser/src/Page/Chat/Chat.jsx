import React, { useState, useEffect, useRef } from "react";
import socketIOClient, { io } from "socket.io-client";
import DefaultLayout from '../../DefaultLayout/DefaultLayout';
import classNames from 'classnames/bind';
import styles from './Chat.module.scss';
import Cookies from 'js-cookie'

const cx = classNames.bind(styles);
const host = "http://localhost:9004/chat?token=abc123";

const Chat = () => {
    const [mess, setMess] = useState([]);
    const [message, setMessage] = useState('');
    const messagesEnd = useRef();
    const socket = window._SOCKET_DATA;
    
    socket.on('chat', function (data) {
        console.log(Cookies.get("email"));
        if(mess.length == 0 ||data.userName !==Cookies.get("email") || data.message !== mess[mess.length - 1].message){
            setMess([...mess, data])
        }
    });

    const sendMessage = () => {
        if(message !== null) {
        const msg = {
            userName: Cookies.get('email'),
            message: message,
            actionTime: new Date(),
        }
        socket.emit('chat', msg);
        setMessage('')
        }
    }

    const scrollToBottom = () => {
        messagesEnd.current.scrollIntoView({ behavior: "smooth" });
    }
    

    const renderMess =  mess.map((m, index) => 
        <div style={{ display: 'block' }} key={index} className={`${m.userName === Cookies.get("email") ? cx('your-message') : cx('other-people')} chat-item`}>
        {m.userName + ": " + m.message}
        </div>
    )

    const handleChange = (e) => {
        setMessage(e.target.value)
    }

    const onEnterPress = (e) => {
        if(e.keyCode == 13 && e.shiftKey == false) {
        sendMessage()
        }
    }

    return (
        <div>
            <DefaultLayout>
                <div className={cx('box-chat')}>
                    <div className={cx('box-chat_message')}>
                        {renderMess}
                        <div style={{ float:"left", clear: "both" }}
                                ref={messagesEnd}>
                            </div>
                    </div>

                    <div className={cx('send-box')}>
                        <textarea
                            value={message}  
                            onKeyDown={onEnterPress}
                            onChange={handleChange} 
                            placeholder="Nhập tin nhắn ..." 
                        />
                        <button 
                        className={cx('button-send')}
                        onClick={sendMessage}>
                            Send
                        </button>
                    </div>
                </div>
            </DefaultLayout>
        </div>
    );
}

export default Chat;
