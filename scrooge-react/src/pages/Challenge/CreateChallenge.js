import { useState } from "react";

import styles from "./CreateChallenge.module.css";
import Header from "../../components/Header";
import Chips from "../../components/UI/Chips";

const CreateChallenge = () => {
  const [title, setTitle] = useState(0);

  const titleChangeHandler = (event) => {
    setTitle(event.target.value);
  };

  return (
    <div>
      <Header text="챌린지 만들기"></Header>
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
          <Chips chips={["식비", "교통비", "쇼핑", "기타"]} />
        </div>
        <div>카테고리</div>
        <div>최소 인원</div>
      </div>
    </div>
  );
};

export default CreateChallenge;
