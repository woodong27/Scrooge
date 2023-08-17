import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

import BackGround from "../components/BackGround";
import Card from "../components/UI/Card";
import styles from "./Login.module.css";
import Cookies from "js-cookie";

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
    };
    fetch("https://day6scrooge.duckdns.org/api/member/login", postData)
      .then((res) => {
        if (!res.ok) {
          throw new Error("Login 실패");
        }
        return res.json();
      })
      .then((data) => {
        const jwtToken = data.token;
        Cookies.set("accessToken", jwtToken, { expires: 7 });
        Cookies.set("memberId", data.memberId, {expires: 7});
        sendJwtTokenToAndroid(jwtToken);

        dispatch({ type: "SET_TOKEN_STRING", payload: "Bearer " + data.token });
        dispatch({ type: "SET_ID_STRING", payload: data.memberId });

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

  return (
    <BackGround>
      <div className={styles.empty} />
      <div className={styles.body}>
        <Card height={44}>
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
          <button className={styles.missPassword}>
            {" "}
            비밀번호를 잊으셨나요?
          </button>
          <button
            className={styles.noAccount}
            onClick={() => {
              navigate("/signup");
            }}
          >
            계정이 없으신가요?
          </button>
        </Card>
        <br />
        <div onClick={handleLogin}>
          <Card height={8}>
            <div className={styles.buttonText}>로그인</div>
          </Card>
        </div>
      </div>
    </BackGround>
  );
};

export default Login;
