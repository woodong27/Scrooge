import React, { useRef, useState } from "react";

import BackGround from "../components/BackGround";
import ButtonWhite from "../components/UI/ButtonWhite";
import CharacterCard from "../components/UI/CharacterCard";

import styles from "./Login.module.css";

const Login = ({ login }) => {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();

  const emailInput = useRef();
  const passwordInput = useRef();
  return (
    <BackGround>
      <div className={styles.empty} />
      <CharacterCard>
        <div className={styles.title}>로그인</div>

        <input
          className={styles.input}
          ref={emailInput}
          value={email}
          placeholder="이메일을 입력해주세요"
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          className={styles.input}
          ref={passwordInput}
          value={password}
          placeholder="비밀번호를 입력해주세요"
          onChange={(e) => setPassword(e.target.value)}
        />
        <button className={styles.missPassword}> 비밀번호를 잊으셨나요?</button>
      </CharacterCard>

      <ButtonWhite text="로그인"></ButtonWhite>
    </BackGround>
  );
};

export default Login;
