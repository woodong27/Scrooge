import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainPage from "./components/Main/MainPage";
import DailyCalcul from "./components/Main/DailyCalcul/DailyCalcul";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<MainPage />}></Route>
          <Route path="/300" element={<DailyCalcul />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
