import React, { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Main from "./components/Main/Main";
import DailyCalcul from "./components/Main/DailyCalcul/DailyCalcul";
import NavBar from "./components/Nav/NavBar";
import Loading from "./components/pages/Loading";

function App() {
  const [isLogin, setIsLogin] = useState(false);

  const loginHandler = () => {
    setIsLogin(true);
  };

  const logoutHandler = () => {
    setIsLogin(false);
  };

  return (
    <React.Fragment>
      {!isLogin && <Loading onLogin={loginHandler} />}
      {isLogin && (
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Main onLogout={logoutHandler} />}></Route>
            <Route path="/300" element={<DailyCalcul />}></Route>
          </Routes>
          <NavBar />
        </BrowserRouter>
      )}
    </React.Fragment>
  );
}

export default App;
