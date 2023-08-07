import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

import BackGround from "../components/BackGround";
import ButtonWhite from "../components/UI/ButtonWhite";
import CharacterCard from "../components/UI/CharacterCard";
import styles from "./Signup.module.css";

const Signup = ({ loginHandler }) => {
  const dispatch = useDispatch();

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
      return;
    }
    if (state.email.length < 10) {
      emailInput.current.focus();
      return;
    }
    if (state.password.length < 8) {
      passwordInput.current.focus();
      return;
    }
    if (state.password !== state.passwordcheck) {
      passwordcheckInput.current.focus();
      return;
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
    fetch("http://day6scrooge.duckdns.org:8081/member/signup", postData)
      .then((res) => res.text())
      .then((data) => {
        navigate("/");
      });
  };

  return (
    <BackGround>
      <div className={styles.empty} />
      <CharacterCard>
        <div className={styles.title}>회원가입</div>
        <input
          className={styles.input}
          ref={nicknameInput}
          name="nickname"
          value={state.nickname}
          placeholder="닉네임을 입력해주세요"
          onChange={handleChangeState}
        />
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
        <input
          className={styles.input}
          ref={passwordcheckInput}
          name="passwordcheck"
          type="password"
          value={state.passwordcheck}
          placeholder="비밀번호를 확인해주세요"
          onChange={handleChangeState}
        />
      </CharacterCard>

      <ButtonWhite text="회원가입" onClick={handleSignup}></ButtonWhite>
    </BackGround>
  );
};

export default Signup;
