import React, { useEffect, useState } from 'react';
import styles from './RegisterCard.module.scss';
import classNames from 'classnames/bind';
import { MdCardMembership } from 'react-icons/md';
import { Link } from 'react-router-dom';
import axios from 'axios';
import toastr from 'toastr';
import { useNavigate } from 'react-router-dom';
import PopupPayment from '../../component/PopupPayment/PopupPayment';

const cx = classNames.bind(styles);

function RegisterCard(props) {
    const currentDate = new Date();
    const [url, setUrl] = useState("");
    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    let json = {
        userId: localStorage.getItem('userId'),
        term: ""
    };

    toastr.options = {
        positionClass: 'toast-top-center', // vị trí giữa bên trên màn hình
        toastClass: 'toastr-custom-style', // tùy chỉnh style cho toastr
    };

    const [termList, setTermList] = useState([]);
    let termEntity = {};


    useEffect(()=>{
        axios
            .get('http://localhost:9191/api/book-service/term/search-all')
            .then((response)=>{
                setTermList(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    },[])

    const navigate = useNavigate();

    const handleRegisterCard = (term) => {
        json.term = term;
        axios
            .get('http://localhost:9191/api/book-service/member/check-register', {params: {
                userId: localStorage.getItem('userId'),
            }})
            .then((response) => {
                console.log(response.data);
                if (response.data.userId != undefined || response.data.userId != null) {
                    toastr.warning(`Tài khoản đã được đăng kí hội viên, từ ngày ${response.data.startDate} đến ngày ${response.data.endDate}`);
                    return;
                }else  {
                    for(let i =0; i < termList.length; i++){
                        if(termList[i].term == term){
                            termEntity = termList[i];
                            break;
                        }
                    }
                    var body = {
                        amount: termEntity.cost,
                        orderInfo: `package member 12 month-${localStorage.getItem('userId')}`+"-"+`${termEntity.term}`
                    }
                    axios({
                        method: 'post',
                        url: 'http://localhost:9191/api/book-service/vnpay/submitOrder',
                        data: body,
                        headers: { "Content-Type": "multipart/form-data","Access-Control-Allow-Origin": "*" },
                    })
                    .then((response)=>{
                        console.log(response.data);
                        const urlArray = response.data.split(" ");
                        setUrl(urlArray[0]+urlArray[1]);
                        console.log(url);
                        handleOpen();

                        // navigate(urlArray[0]+urlArray[1])s
                    })
                    .catch((error) => {
                        console.log(error);
                    });
                }
                // axios
                //     .post('http://localhost:9191/api/book-service/member/register', { ...json })
                //     .then((response) => {
                //         alert(`Bạn đã trở thành hội viên của waka từ ngày ${response.data.startDate} đến ngày ${response.data.endDate}`);
                //     })
                //     .catch((error) => {
                //         console.log(error);
                //     });
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className={cx('wrapper')}>
            <PopupPayment url = {url} termList = {termList} open={open} handleOpen={handleOpen} handleClose={handleClose}/>
            <div className={cx('header')}>
                <div className={cx('logo')}>
                    <img src="	https://ebook.waka.vn/themes/desktop/images/logo_waka_epay.png?v=1" />
                </div>
                <div className={cx('content')}>
                    <div className={cx('banner')}>
                        <img src="https://ebook.waka.vn/themes/desktop/images/epay_bg.png" />
                    </div>
                    <div className={cx('member')}>
                        <MdCardMembership className={cx('member-icon')} />
                        <h2>Đăng kí để trở thành hội viên của waka</h2>
                        <p>Khi đăng kí để trở thành hội viên bạn sẽ có những lợi ích sau</p>
                    </div>
                    <div className={cx('benefit-box')}>
                        <div className={cx('benefit')}>
                            <div className={cx('benefit-content')}>
                                <h3>Waka: Hội viên</h3>
                            </div>
                            <p className={cx('benefit-title')}>3 tháng làm hội viên waka</p>
                            <div className={cx('benefit-button')}>
                                <Link onClick={()=>{handleRegisterCard(3)}} className={cx('benefit-button-detail')}>
                                    Đăng kí
                                </Link>
                            </div>
                        </div>
                        <div className={cx('benefit')}>
                            <div className={cx('benefit-content')}>
                                <h3>Waka: Hội viên</h3>
                            </div>
                            <p className={cx('benefit-title')}>6 tháng làm hội viên waka</p>
                            <div className={cx('benefit-button')}>
                                <Link onClick={()=>{handleRegisterCard(6)}} className={cx('benefit-button-detail')}>
                                    Đăng kí
                                </Link>
                            </div>
                        </div>
                        <div className={cx('benefit')}>
                            <div className={cx('benefit-content')}>
                                <h3>Waka: Hội viên</h3>
                            </div>
                            <p className={cx('benefit-title')}>12 tháng làm hội viên waka</p>
                            <div className={cx('benefit-button')}>
                                <Link onClick={()=>{handleRegisterCard(12)}} className={cx('benefit-button-detail')}>
                                    Đăng kí
                                </Link>
                            </div>
                        </div>
                    </div>
                    <div className={cx('form-register')}>
                        <div className={cx('form-header')}>
                            <h3>Trở thành hội viên waka</h3>
                        </div>
                    </div>
                </div>
            </div>
            <div className={cx('body')}>
                <div className={cx('account')}>
                    <h4>
                        Trở thành hội viên của Waka bạn cần nắm rõ những điều sau
                    </h4>
                    <br />
                    <p>
                        1. Đăng ký hội viên: Trước hết, bạn cần tham gia chương trình thành viên của Waka và hoàn tất quá trình đăng ký.
                    </p>
                    <p>
                        2. Khám phá thư viện sách trực tuyến: Duyệt qua bộ sưu tập đa dạng của Waka và chọn sách mà bạn muốn đọc. Có thể bạn sẽ được tận hưởng sách điện tử, âm thanh, hoặc cả hai.
                    </p>
                    <p>
                        3. Thưởng thức đọc sách: Mọi sách đều sẵn có trực tuyến, không cần mang theo thẻ hội viên. Bạn có thể bắt đầu đọc ngay trên trang web của Waka và tận hưởng trải nghiệm đọc sách thuận tiện.
                    </p>
                    <p>
                        4. Tìm hiểu về các tính năng: Khám phá các tính năng hữu ích như đánh dấu trang, tìm kiếm nhanh, và gợi ý sách dựa trên sở thích cá nhân của bạn.
                    </p>
                </div>
            </div>
            <div className={cx('foodter')}>
                <div className={cx('foodter-left')}>
                    <h4>THÔNG TIN LIÊN HỆ</h4>
                    <div className={cx('foodter-left-content')}>
                        <p>
                            <strong>Công ty Cổ phần Sách điện tử Waka</strong> - Tầng 6, tháp văn phòng quốc tế Hòa
                            Bình, số 106, đường Hoàng Quốc Việt, phường Nghĩa Đô, Quận Cầu Giấy, thành phố Hà Nội, Việt
                            Nam.
                        </p>
                        <p>
                            ĐKKD số 0108796796 do SKHĐT TP Hà Nội cấp ngày 24/06/2019 | Chịu trách nhiệm nội dung:{' '}
                            <strong>Ông Đinh Quang Hoàng</strong>
                        </p>
                        <p>
                            Giấy xác nhận Đăng ký hoạt động phát hành xuất bản phẩm điện tử số 8132/XN-CXBIPH do Cục
                            Xuất bản, In và Phát hành cấp ngày 31/12/2019
                        </p>
                        <p>
                            {' '}
                            Giấy chứng nhận Đăng ký cung cấp dịch vụ nội dung thông tin trên mạng Viễn thông di động số
                            19/GCN-DĐ do Cục Phát thanh, truyền hình và thông tin điện tử ký ngày 11/03/2020
                        </p>
                        <p>
                            {' '}
                            Email: <strong>support@waka.vn</strong> | Tel: <strong>024.37918440</strong>
                        </p>
                    </div>
                </div>
                <div className={cx('foodter-right')}>
                    <h4>THÔNG TIN ỨNG DỤNG</h4>
                    <div className={cx('qr-box')}>
                        <ul>
                            <li>
                                <div className={cx('app-qr-code')}>
                                    <img
                                        src="https://ebook.waka.vn/themes/desktop/images/qrcode_install_app.png?v=1"
                                        className={cx('img-fluid')}
                                        alt="Đọc sách online trên Waka"
                                    />
                                </div>
                                <div className={cx('app-desc')}>
                                    <span>
                                        Hệ điều hành <strong>Android </strong>
                                    </span>
                                    <span>phiên bản ứng dụng:7.4</span>
                                    <br />
                                    <span>
                                        Hệ điều hành <strong>IOS</strong>
                                    </span>
                                    <span>phiên bản ứng dụng:7.4</span>
                                    <span>Ngày đăng: 06/12/2019</span>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default RegisterCard;
