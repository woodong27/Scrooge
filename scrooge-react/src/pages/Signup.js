import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";

import BackGround from "../components/BackGround";
import Card from "../components/UI/Card";
import styles from "./Signup.module.css";

const Signup = () => {
  //이메일 정규식
  const emailRegEx =
    /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*.[A-Za-z]{2,3}$/;
  //비밀번호 정규식
  const passwordRegEx = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;

  const [state, setState] = useState({
    nickname: "",
    email: "",
    password: "",
    passwordcheck: "",
  });

  const handleChangeState = (e) => {
    setState({
      ...state,
      [e.target.name]: e.target.value,
    });
  };

  const nicknameInput = useRef();
  const emailInput = useRef();
  const passwordInput = useRef();
  const passwordcheckInput = useRef();

  const navigate = useNavigate();
  const handleSignup = () => {
    if (state.nickname.length < 4) {
      nicknameInput.current.focus();
      const errorDiv = document.getElementById("nickname");
      errorDiv.style.display = "block";
      return;
    } else {
      const errorDiv = document.getElementById("nickname");
      errorDiv.style.display = "none";
    }
    if (!emailRegEx.test(state.email)) {
      emailInput.current.focus();
      const errorDiv = document.getElementById("email");
      errorDiv.style.display = "block";
      return;
    } else {
      const errorDiv = document.getElementById("email");
      errorDiv.style.display = "none";
    }
    if (!passwordRegEx.test(state.password)) {
      passwordInput.current.focus();
      const errorDiv = document.getElementById("password");
      errorDiv.style.display = "block";
      return;
    } else {
      const errorDiv = document.getElementById("password");
      errorDiv.style.display = "none";
    }
    if (state.password !== state.passwordcheck) {
      passwordcheckInput.current.focus();
      const errorDiv = document.getElementById("passwordcheck");
      errorDiv.style.display = "block";
      return;
    } else {
      const errorDiv = document.getElementById("passwordcheck");
      errorDiv.style.display = "none";
    }

    const obj = {
      nickname: state.nickname,
      email: state.email,
      password1: state.password,
      password2: state.passwordcheck,
    };

    const postData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(obj),
    };
    fetch("https://day6scrooge.duckdns.org/api/member/signup", postData)
      .then((res) => {
        if (!res.ok) {
          console.log(res);
          throw new Error("회원가입 실패");
        }
        return res.text();
      })
      .then(() => {
        navigate("/");
      })
      .catch((error) => {
        const errorDiv = document.getElementById("error");
        errorDiv.style.display = "block";
      });
  };

  return (
    <BackGround>
      <div className={styles.empty} />
      <div className={styles.body}>
        <Card height={440}>
          <div className={styles.title}>회원가입</div>

          <div className={styles.frame}>
            <input
              className={styles.input}
              ref={nicknameInput}
              name="nickname"
              value={state.nickname}
              placeholder="닉네임을 입력해주세요"
              onChange={handleChangeState}
            />
            <div id="nickname" className={styles.error}>
              닉네임을 4글자 이상 입력해주세요.
            </div>

            <input
              className={styles.input}
              ref={emailInput}
              name="email"
              value={state.email}
              placeholder="이메일을 입력해주세요"
              onChange={handleChangeState}
            />
            <div id="email" className={styles.error}>
              올바른 이메일을 입력해주세요.
            </div>

            <input
              className={styles.input}
              ref={passwordInput}
              name="password"
              type="password"
              value={state.password}
              placeholder="비밀번호를 입력해주세요"
              onChange={handleChangeState}
            />
            <div id="password" className={styles.error}>
              알파벳, 숫자, 특수문자를 포함한 <br />
              8글자 이상 비밀번호를 입력해주세요.
            </div>

            <input
              className={styles.input}
              ref={passwordcheckInput}
              name="passwordcheck"
              type="password"
              value={state.passwordcheck}
              placeholder="비밀번호를 확인해주세요"
              onChange={handleChangeState}
            />
            <div id="passwordcheck" className={styles.error}>
              비밀번호를 다시 확인해주세요.
            </div>
            <div id="error" className={styles.error}>
              회원가입에 실패했습니다.
            </div>
          </div>
          <button
            className={styles.yesAccount}
            onClick={() => {
              navigate("/login");
            }}
          >
            계정이 있으신가요?
          </button>
        </Card>
        <br />

        <div onClick={handleSignup}>
          <Card height={60}>
            <div className={styles.buttonText}>회원가입</div>
          </Card>
        </div>
      </div>
    </BackGround>
  );
};

export default Signup;
