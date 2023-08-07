import { useState } from "react";
import { Routes, Route } from "react-router-dom";

import Main from "./pages/Main/Main";
import Footer from "./components/Footer";
import Loading from "./pages/Loading";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Quest from "./pages/Quest/Quest";
import Challenge from "./pages/Challenge/Challenge";
import ChallengeJoin from "./pages/Challenge/ChallengeJoin";
import ChallengeDetail from "./pages/Challenge/ChallengeDetail";
import MyPage from "./pages/MyPage/MyPage";
import Settings from "./pages/MyPage/Settings";
import CreateChallenge from "./pages/Challenge/CreateChallenge";

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
      <Routes>
        {isLogin ? (
          <>
            <Route path="/" element={<Main onLogout={logoutHandler} />} />
            <Route path="/quest" element={<Quest />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/challenge" element={<Challenge />} />
            <Route path="/challenge/:id" element={<ChallengeJoin />} />
            <Route path="/challenge/create" element={<CreateChallenge />} />
            <Route path="/challenge/my" element={<ChallengeDetail />} />
            <Route path="/mypage" element={<MyPage />} />
            <Route
              path="/mypage/settings"
              element={<Settings onLogout={logoutHandler} />}
            />
          </>
        ) : (
          <>
            <Route path="/" element={<Loading loginHandler={loginHandler} />} />
            <Route
              path="/login"
              element={<Login loginHandler={loginHandler} />}
            />
            <Route path="/signup" element={<Signup />} />

            {/* 로그인 안되서 테스트용 */}
            <Route path="/challenge" element={<Challenge />} />
            <Route path="/challenge/:id" element={<ChallengeJoin />} />
            <Route path="/challenge/create" element={<CreateChallenge />} />
            <Route path="/challenge/my" element={<ChallengeDetail />} />
          </>
        )}
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
