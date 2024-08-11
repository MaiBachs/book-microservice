import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import Home from './page/home\'/Home';
import ReadingBookManagement from './page/ReadingBook/ReadingBookManagement';
import AddNewReadingBook from './page/ReadingBook/AddNewReadingBook';
import PdfFileView from './page/PdfFileView/PdfFileView';
import AudioBookManagement from './page/AudioBook/AudioBookManagement';
import AddNewAudioBook from './page/AudioBook/AddNewAudioBook';
import PaymentManagementReadingBook from './page/paymentManagement/PaymentManagementReadingBook';
import PaymentManagementAudioBook from './page/paymentManagement/PaymentManagementAudioBook';
import PaymentMemberMn from './page/paymentManagement/PaymentMemberMn';

function App() {
  return (
    <div className="App">
      <header className="App-header">
                <BrowserRouter>
                    <Routes>
                        <Route path="/home" element={<Home />}></Route>
                        <Route path="/readingbookmanagement" element={<ReadingBookManagement />}></Route>
                        <Route path="/addnewreadingbook" element={<AddNewReadingBook />}></Route>
                        <Route path="/pdffileview" element={<PdfFileView />}></Route>
                        <Route path="/audiobookmanagement" element={<AudioBookManagement />}></Route>
                        <Route path="/addnewaudiobook" element={<AddNewAudioBook />}></Route>
                        <Route path="/payment-management-reading-book" element={<PaymentManagementReadingBook />}></Route>
                        <Route path="/payment-management-audio-book" element={<PaymentManagementAudioBook />}></Route>
                        <Route path="/payment-member" element={<PaymentMemberMn />}></Route>

                        <Route path="*" element={<Navigate to="/home" />}></Route>
                    </Routes>
                </BrowserRouter>
            </header>
    </div>
  );
}

export default App;
