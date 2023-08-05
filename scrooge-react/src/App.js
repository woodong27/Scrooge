import { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Main from "./pages/Main/Main";
import Footer from "./components/Footer";
import Loading from "./pages/Loading";
import Quest from "./pages/Quest/Quest";
import Challenge from "./pages/Challenge/Challenge";
import ChallengeJoin from "./pages/Challenge/ChallengeJoin";
import ChallengeDetail from "./pages/Challenge/ChallengeDetail";
import MyPage from "./pages/MyPage/MyPage";
import Login from "./pages/Login";

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
      <BrowserRouter>
        {isLogin ? (
          <Routes>
            <Route path="/" element={<Main onLogout={logoutHandler} />}></Route>
            <Route path="/quest" element={<Quest />}></Route>

            <Route path="/challenge" element={<Challenge />}></Route>
            <Route
              path="/challenge/join"
              element={<ChallengeJoin></ChallengeJoin>}
            ></Route>
            <Route
              path="/challenge/my"
              element={<ChallengeDetail></ChallengeDetail>}
            ></Route>
            <Route path="/mypage" element={<MyPage />}></Route>
            <Route path="/login" element={<Login />}></Route>
          </Routes>
        ) : (
          <Loading isLogin={isLogin} onLogin={loginHandler} />
        )}

        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
