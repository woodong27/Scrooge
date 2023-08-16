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
import NicknameChange from "./pages/Settings/NicknameChange";
import WebSocketComponent from "./utils/WebSocketComponent";
import Cookies from "js-cookie";

function App() {

  const dispatch = useDispatch();

  const [isLogin, setIsLogin] = useState(false);

   useEffect(() => {
    // 페이지 로드 시 쿠키에서 accessToken 로드
    const savedAccessToken = Cookies.get('accessToken');
    if(savedAccessToken) {
      setIsLogin(true); // 로그인 true
      dispatch({ type: "SET_TOKEN_STRING", payload: "Bearer " + savedAccessToken });
      if(window.AndroidBridge) {
        window.AndroidBridge.sendJwtTokenToAndroid(savedAccessToken);
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
      const payload = token.split(".")[1];
      // base64 디코딩 후 JSON 파싱
      const decodedPayload = JSON.parse(atob(payload));

      // 'exp' 필드로부터 만료 시간 확인
      const expirationTime = decodedPayload.exp * 1000; // 밀리초 단위
      return expirationTime;
    } catch (error) {
      console.error("Failed to decode access token:", error);
      return null;
    }
  }

  return (
    <div>
      {isLogin ? (
        <>
          <Routes>
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
            <Route path="/challenge/my/:id/" element={<ChallengeDetail />} />
            <Route path="/challenge/chat" element={<Chatting />} />
            <Route
              path="/challenge/chat/:id/:team"
              element={<WebSocketComponent />}
            />
            <Route path="/mypage" element={<MyPage />} />
            <Route
              path="/mypage/settings"
              element={<Settings onLogout={logoutHandler} />}
            />
            <Route path="/passwordChange" element={<PasswordChange />} />
            <Route path="/nicknameChange" element={<NicknameChange />} />
            <Route path="/notification" element={<Notification />} />
            <Route path="/profile/:id" element={<Profile />} />
          </Routes>
          <Footer />
        </>
      ) : (
        <Routes>
          <Route path="/" element={<Loading loginHandler={loginHandler} />} />
          <Route
            path="/login"
            element={<Login loginHandler={loginHandler} />}
          />
          <Route path="/signup" element={<Signup />} />
        </Routes>
      )}
    </div>
  );
}

export default App;
