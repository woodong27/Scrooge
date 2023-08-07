import { useState } from "react";
import { Link } from "react-router-dom";

import styles from "./CreateChallenge.module.css";
import Header from "../../components/Header";
import Chips from "../../components/UI/Chips";
import backImg from "../../assets/back.png";

const CreateChallenge = () => {
  const [title, setTitle] = useState(0);
  const category = ["식비", "교통비", "쇼핑", "기타"];

  const titleChangeHandler = (event) => {
    setTitle(event.target.value);
  };

  return (
    <div>
      <Header text="챌린지 만들기">
        <Link className={styles.back} to="/challenge">
          <img src={backImg} alt="뒤로가기"></img>
        </Link>
      </Header>
      <div className={styles.title}>
        챌린지 제목
        <input
          placeholder="예) 한 달 택시비 5만원 이하로 쓰기"
          value={title}
          onChange={titleChangeHandler}
        ></input>
        <div className={styles.length}>{title.length} / 30</div>
      </div>
      <div className="setup">
        <div>
          챌린지 기간
          {category.map((e) => (
            <Chips>e</Chips>
          ))}
        </div>
        <div>카테고리</div>
        <div>최소 인원</div>
      </div>
    </div>
  );
};

export default CreateChallenge;
