import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Main from "./components/Main/Main";
import DailyCalcul from "./components/Main/DailyCalcul/DailyCalcul";
import NavBar from "./components/Nav/NavBar";
import Loading from "./components/pages/Loading";

function App() {
  return (
    <React.Fragment>
      {/* <Loading />  */}
      {/* <BrowserRouter>
        <Routes>
          <Route path="/" element={<Main />}></Route>
          <Route path="/300" element={<DailyCalcul />}></Route>
        </Routes>
        <NavBar />
      </BrowserRouter> */}
    </React.Fragment>
  );
}

export default App;
