import React, { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Main from "./pages/Main/Main";
import DailyCalcul from "./pages/Main/DailyCalcul";
import Footer from "./components/Footer";
import Loading from "./pages/Loading";
import Challenge from "./pages/Challenge/Challenge";

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
          <Footer />
        </BrowserRouter>
      )}
    </div>
  );
}

export default App;
