import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

import BackGround from "../components/BackGround";
import ButtonWhite from "../components/UI/ButtonWhite";
import CharacterCard from "../components/UI/CharacterCard";
import styles from "./Login.module.css";

const Login = ({ loginHandler }) => {
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
    if (state.email.length < 5) {
      emailInput.current.focus();
      return;
    }
    if (state.password.length < 2) {
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
    };
    fetch("http://day6scrooge.duckdns.org:8081/member/login", postData)
      .then((res) => res.text())
      .then((data) => {
        const jwtToken = data;
        sendJwtTokenToAndroid(jwtToken);

        dispatch({ type: "SET_TOKEN_STRING", payload: "Bearer " + data });

        loginHandler();
        navigate("/");
      });
  };

  function sendJwtTokenToAndroid(jwtToken) {
    if (window.AndroidBridge) {
      window.AndroidBridge.sendJwtTokenToAndroid(jwtToken);
    }
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
        <button className={styles.missPassword}> 비밀번호를 잊으셨나요?</button>
      </CharacterCard>

      <ButtonWhite text="로그인" onClick={handleLogin}></ButtonWhite>
    </BackGround>
  );
};

export default Login;
