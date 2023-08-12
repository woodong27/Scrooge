import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
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
import Community from "./pages/Community/Community";
import Detail from "./pages/Community/Detail";
import NewArticle from "./pages/Community/NewArticle";

function App() {
  const [isLogin, setIsLogin] = useState(false);
  const dispatch = useDispatch();

  console.log("처음에는 : " + isLogin);

  useEffect(() => {
    const cookies = document.cookie.split(';');
    let refreshToken = null;

    for(let i=0; i<cookies.length; i++) {
      const cookie  = cookies[i].trim();
      if(cookie.startsWith('refreshToken=')) {
        refreshToken = cookie.substring('refreshToken='.length, cookie.length);
        break;
      }
    }

    console.log("refreshToken:" + refreshToken);

    // refreshToken이 있다면 access Token 받기
    if(refreshToken) {
      const refreshTokenUrl = "http://localhost:8081/api/token/reissue";

      const obj = {
        refreshToken
    }

    const postData = {
        method: "POST",
        headers: {
            "Content-Type" : "application/json",
        },
        body: JSON.stringify(obj),
    }


    fetch(refreshTokenUrl, postData)
        .then((res) => {
            return res.json();
        })
        .then((data) => {
            console.log(data.accessToken);
            dispatch({ type: "SET_TOKEN_STRING", payload: "Bearer " + data.accessToken });
            setIsLogin(!!refreshToken);
            console.log(data.refreshToken);
            setCookie('refreshToken', data.refreshToken, 7);
        })
        .catch((error) => {
            console.log(error);
        });
    }

    

    console.log("refreshToken 확인 " + isLogin);
  })

  function calculateExpiration(days) {
    const now = new Date();
    now.setTime(now.getTime() + days * 24 * 60 * 60 * 1000);
    return now.toUTCString();
  }

  // 쿠키 설정 함수
  function setCookie(name, value, days) {
    const expires = calculateExpiration(days);
    document.cookie = `${name}=${value}; expires=${expires}; path=/; SameSite=None; Secure`;
  }

  const loginHandler = () => {
    setIsLogin(true);
    console.log("로그인 하고 나서,," + isLogin);
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
            <Route path="/community" element={<Community />} />
            <Route path="/community/:id" element={<Detail />} />
            <Route path="/community/detail" element={<Detail />} />
            <Route path="/community/create" element={<NewArticle />} />
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
            <Route path="/quest" element={<Quest />} />
            <Route path="/community" element={<Community />} />
            <Route path="/community/:id" element={<Detail />} />
            <Route path="/community/create" element={<NewArticle />} />
          </>
        )}
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
