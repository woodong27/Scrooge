import React, { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Main from "./components/Main/Main";
import DailyCalcul from "./components/Main/DailyCalcul/DailyCalcul";
import NavBar from "./components/Nav/NavBar";
import Loading from "./components/pages/Loading";
import Challenge from "./components/pages/Challenge/Challenge";

function App() {
  const [isLogin, setIsLogin] = useState(false);

  const loginHandler = () => {
    setIsLogin(true);
  };

  const logoutHandler = () => {
    setIsLogin(false);
  };

  // bg-[#a9d9f4] bg-[url('../public/images/bg-images.png')] bg-cover
  return (
    <div className="h-screen w-screen ">
      {!isLogin && <Loading onLogin={loginHandler} />}
      {isLogin && (
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Main onLogout={logoutHandler} />}></Route>
            <Route path="/300" element={<DailyCalcul />}></Route>
            <Route path="/challenge" element={<Challenge />}></Route>
          </Routes>
          <NavBar />
        </BrowserRouter>
      )}
    </div>
  );
}

export default App;
