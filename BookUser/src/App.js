import './App.css';
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import Home from './Page/Home/Home.jsx';
import DetailBook from './Page/DetailBook/DetailBook.jsx';
import ListBookByCategory from './Page/ListBookByCategory/ListBookByCategory';
import RegisterCard from './Page/RegisterCard/RegisterCard';
import ListBookSearch from './Page/ListBookSearch/ListBookSearch';
import TopBook from './Page/TopBook/TopBook';
import Chat from './Page/Chat/Chat.jsx';
import PdfFileView from './Page/PdfFileView/PdfFileView.jsx';
import AudioBook from './Page/AudioBook/AudioBook.jsx';
import DetailAudioBook from './Page/DetailAudioBook/DetailAudioBook.jsx';
import EBookCase from './Page/BookCase/EBookCase.jsx';
import AudioBookCase from './Page/BookCase/AudioBookCase.jsx';
import HistoryPaymentBook from './Page/HistoryPayment/HistoryPaymentBook.jsx';
import HistoryPaymentAudioBook from './Page/HistoryPayment/HistoryPaymentAudioBook.jsx';
import HistoryPaymentRegiscard from './Page/HistoryPayment/HistoryPaymentRegiscard.jsx';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <BrowserRouter>
                    <Routes>
                        <Route path="/home" element={<Home />}></Route>
                        <Route path="/detailbook" element={<DetailBook />}></Route>
                        <Route path="/bookbycategory" element={<ListBookByCategory />}></Route>
                        <Route path="/registercard" element={<RegisterCard />}></Route>
                        <Route path="/listbooksearch" element={<ListBookSearch />}></Route>
                        <Route path="/topbook" element={<TopBook />}></Route>
                        <Route path="/chat" element={<Chat />}></Route>
                        <Route path="/pdffileview" element={<PdfFileView />}></Route>
                        <Route path="/audiobook" element={<AudioBook />}></Route>
                        <Route path="/detailaudiobook" element={<DetailAudioBook />}></Route>
                        <Route path="/ebookcase" element={<EBookCase />}></Route>
                        <Route path="/audiobookcase" element={<AudioBookCase />}></Route>
                        <Route path="/historypaymentbook" element={<HistoryPaymentBook />}></Route>
                        <Route path="/historypaymentaudiobook" element={<HistoryPaymentAudioBook />}></Route>
                        <Route path="/historypaymentregiscard" element={<HistoryPaymentRegiscard />}></Route>

                        <Route path="*" element={<Navigate to="/home" />}></Route>
                    </Routes>
                </BrowserRouter>
            </header>
        </div>
    );
}

export default App;
