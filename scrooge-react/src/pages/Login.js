import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

import BackGround from "../components/BackGround";
import ButtonWhite from "../components/Button/ButtonWhite";
import CharacterCard from "../components/UI/CharacterCard";
import styles from "./Login.module.css";

const Login = ({ loginHandler }) => {
  //이메일 정규식
  const emailRegEx =
    /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*.[A-Za-z]{2,3}$/;

  const dispatch = useDispatch();

  const [state, setState] = useState({
    email: "",
    password: "",
  });

  const handleChangeState = (e) => {
    setState({
      ...state,
      [e.target.name]: e.target.value,
    });
  };

  const emailInput = useRef();
  const passwordInput = useRef();

  const navigate = useNavigate();
  const handleLogin = () => {
    if (!emailRegEx.test(state.email)) {
      emailInput.current.focus();
      return;
    }
    if (state.password.length < 8) {
      passwordInput.current.focus();
      return;
    }

    const obj = {
      email: state.email,
      password: state.password,
    };
    const postData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(obj),
      credentials: "include",
    };
    fetch("http://localhost:8081/api/member/login", postData)
      .then((res) => {
        if (!res.ok) {
          throw new Error("Login 실패");
        }
        return res.json();
      })
      .then((data) => {
        console.log(data);
        const jwtToken = data.token;
        const refreshToken = data.refreshToken;

        localStorage.setItem('refreshToken', refreshToken);

        setCookie('refreshToken', refreshToken, 7);

        sendJwtTokenToAndroid(jwtToken);

        console.log(jwtToken);
        console.log(refreshToken);

        dispatch({ type: "SET_TOKEN_STRING", payload: "Bearer " + data.token });
        dispatch({ type: "SET_ID_STRING", payload: data.memberId });
        dispatch({ type: 'SET_LOG_IN', payload: true });

        loginHandler();
        navigate("/");
      })
      .catch((error) => {
        const errorDiv = document.getElementById("error");
        errorDiv.style.display = "block";
      });
  };

  function sendJwtTokenToAndroid(jwtToken) {
    if (window.AndroidBridge) {
      window.AndroidBridge.sendJwtTokenToAndroid(jwtToken);
    }
  }

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

  return (
    <BackGround>
      <div className={styles.empty} />
      <CharacterCard>
        <div className={styles.title}>로그인</div>

        <input
          className={styles.input}
          ref={emailInput}
          name="email"
          value={state.email}
          placeholder="이메일을 입력해주세요"
          onChange={handleChangeState}
        />
        <input
          className={styles.input}
          ref={passwordInput}
          name="password"
          type="password"
          value={state.password}
          placeholder="비밀번호를 입력해주세요"
          onChange={handleChangeState}
        />
        <div id="error" className={styles.error}>
          이메일과 비밀번호를 확인해주세요.
        </div>
        <button className={styles.missPassword}> 비밀번호를 잊으셨나요?</button>
      </CharacterCard>

      <ButtonWhite text="로그인" onClick={handleLogin}></ButtonWhite>
    </BackGround>
  );
};

export default Login;
