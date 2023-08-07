import { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Main from "./pages/Main/Main";
import Footer from "./components/Footer";
import Loading from "./pages/Loading";
import Login from "./pages/Login";
import Quest from "./pages/Quest/Quest";
import Challenge from "./pages/Challenge/Challenge";
import ChallengeJoin from "./pages/Challenge/ChallengeJoin";
import ChallengeDetail from "./pages/Challenge/ChallengeDetail";
import MyPage from "./pages/MyPage/MyPage";
import Settings from "./pages/MyPage/Settings";

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
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/mypage/settings" element={<Settings onLogout={logoutHandler}/>} /> 
          </Routes>
        ) : (
          <Routes>
            <Route
              path="/"
              element={<Loading loginHandler={loginHandler} />}
            ></Route>
            <Route
              path="/login"
              element={<Login loginHandler={loginHandler} />}
            ></Route>
          </Routes>
        )}

        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
