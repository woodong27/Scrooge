import { useState, useEffect } from "react";
import { Routes, Route } from "react-router-dom";
import { useDispatch } from "react-redux";

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
import Settings from "./pages/Settings/Settings";
import CreateChallenge from "./pages/Challenge/CreateChallenge";
import Community from "./pages/Community/Community";
import Detail from "./pages/Community/Detail";
import NewArticle from "./pages/Community/NewArticle";
import Chatting from "./pages/Challenge/Chatting";
import Profile from "./pages/Profile";
// import Settings from "./pages/Settings/Settings";
import Notification from "./pages/Notification";
import PasswordChange from "./pages/Settings/PasswordChange";

function App() {

  const [isLogin, setIsLogin] = useState(false);
  const dispatch = useDispatch();

  useEffect(() => {
    const token = localStorage.getItem("token");

    
    if (token !== null) {
      const expirationTime = decodeAccessToken(token);

      if(expirationTime) {
        const currentTime = Date.now();
        if(expirationTime <= currentTime) {
          localStorage.removeItem("token");
        }
      }

      if(localStorage.getItem("token") !== null) {
        dispatch({ type: "SET_TOKEN_STRING", payload: "Bearer " + token });
        setIsLogin(true);
      }
    }
  }, []);

  const loginHandler = () => {
    setIsLogin(true);
  };
  const logoutHandler = () => {
    setIsLogin(false);
  };

  function decodeAccessToken(token) {
    try {
      // 토큰의 payload 부분 추출 (두 번째 부분)
      const payload = token.split('.')[1];
      // base64 디코딩 후 JSON 파싱
      const decodedPayload = JSON.parse(atob(payload));
      
      // 'exp' 필드로부터 만료 시간 확인
      const expirationTime = decodedPayload.exp * 1000; // 밀리초 단위
      return expirationTime;
    } catch (error) {
      console.error('Failed to decode access token:', error);
      return null;
    }
  }
  

  return (
    <div>
      <Routes>
        {isLogin ? (
          <>
            <Route path="/" element={<Main onLogout={logoutHandler} />} />
            <Route path="/quest" element={<Quest />} />
            <Route path="/community" element={<Community />} />
            <Route path="/community/:id" element={<Detail />} />
            <Route path="/community/detail" element={<Detail />} />
            <Route path="/community/create" element={<NewArticle />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/challenge" element={<Challenge />} />
            <Route path="/challenge/:id" element={<ChallengeJoin />} />
            <Route path="/challenge/create" element={<CreateChallenge />} />
            <Route path="/challenge/my/:id" element={<ChallengeDetail />} />
            <Route path="/challenge/chat" element={<Chatting />} />
            <Route path="/mypage" element={<MyPage />} />
            <Route
              path="/mypage/settings"
              element={<Settings onLogout={logoutHandler} />}
            />
            <Route path="/passwordChange" element={<PasswordChange />} />
            <Route path="/notification" element={<Notification />} />
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
            <Route path="/challenge/my/:id" element={<ChallengeDetail />} />
            <Route path="/challenge/chat" element={<Chatting />} />
            <Route path="/quest" element={<Quest />} />
            <Route path="/community" element={<Community />} />
            <Route path="/community/:id" element={<Detail />} />
            <Route path="/community/create" element={<NewArticle />} />
            <Route path="/notification" element={<Notification />} />
            <Route path="/profile" element={<Profile />} />
          </>
        )}
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
