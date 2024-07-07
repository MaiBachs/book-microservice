import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import Home from './page/home\'/Home';
import ReadingBookManagement from './page/ReadingBook/ReadingBookManagement';
import AddNewReadingBook from './page/ReadingBook/AddNewReadingBook';
import PdfFileView from './page/PdfFileView/PdfFileView';

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

                        <Route path="*" element={<Navigate to="/home" />}></Route>
                    </Routes>
                </BrowserRouter>
            </header>
    </div>
  );
}

export default App;
