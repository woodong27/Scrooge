import { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Main from "./pages/Main/Main";
import DailyCalcul from "./pages/Main/DailyCalcul";
import Footer from "./components/Footer";
import Loading from "./pages/Loading";
import Challenge from "./pages/Challenge/Challenge";
import ChallengeJoin from "./pages/Challenge/ChallengeJoin";

function App() {
  const [isLogin, setIsLogin] = useState(false);

  const loginHandler = () => {
    setIsLogin(true);
  };

  const logoutHandler = () => {
    setIsLogin(false);
  };

  return (
    <div className="h-screen w-screen ">
      {!isLogin && <Loading onLogin={loginHandler} />}
      {isLogin && (
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Main onLogout={logoutHandler} />}></Route>
            <Route path="/300" element={<DailyCalcul />}></Route>
            <Route path="/challenge" element={<Challenge />}></Route>
            <Route
              path="/challenge/join"
              element={<ChallengeJoin></ChallengeJoin>}
            ></Route>
          </Routes>
          <Footer />
        </BrowserRouter>
      )}
    </div>
  );
}

export default App;
