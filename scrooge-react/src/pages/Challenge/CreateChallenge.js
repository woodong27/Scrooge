import { useState, Fragment } from "react";
import { Link } from "react-router-dom";

import styles from "./CreateChallenge.module.css";
import Header from "../../components/Header";
import Chips from "../../components/UI/Chips";
import backImg from "../../assets/back.png";

const CreateChallenge = () => {
  const period = ["1주", "2주", "3주", "한달"];
  const category = ["식비", "교통비", "쇼핑", "기타"];
  const peoples = ["4명", "6명", "8명", "10명"];
  const [selectPeriod, setSelectPeriod] = useState();
  const [selectCategory, setSelectCategory] = useState();
  const [selectPeoples, setSelectPeoples] = useState();

  const [title, setTitle] = useState("");
  const [authMethod, setAuthMethod] = useState("");

  const titleChangeHandler = (event) => {
    setTitle(event.target.value);
  };
  const authMethodChangeHandler = (event) => {
    setAuthMethod(event.target.value);
  };

  return (
    <Fragment>
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
        <div className={styles.length}>{title.length}/30</div>
      </div>
      <div className={styles.setup}>
        <div className={styles.item}>
          챌린지 기간
          <div>
            {period.map((e, i) => (
              <Chips
                key={i}
                s
                selected={selectPeriod}
                setSelect={setSelectPeriod}
              >
                {e}
              </Chips>
            ))}
          </div>
        </div>
        <div className={styles.item}>
          카테고리
          <div>
            {category.map((e, i) => (
              <Chips
                key={i}
                selected={selectCategory}
                setSelect={setSelectCategory}
              >
                {e}
              </Chips>
            ))}
          </div>
        </div>
        <div className={styles.item}>
          최소 인원
          <div>
            {peoples.map((e, i) => (
              <Chips
                key={i}
                selected={selectPeoples}
                setSelect={setSelectPeoples}
              >
                {e}
              </Chips>
            ))}
          </div>
          <p>❗최소 인원이 달성되어야 챌린지 시작이 가능해요.</p>
        </div>
      </div>

      <div className={styles.auth_method}>
        <div>인증 방법 👀</div>
        <textarea
          placeholder="예) 오늘 소비 내역 불러오기 (소비 내역 리캡은 스크루지가 알아서 도와드릴게요!) & 택시 안에서 촬영한 사진 또는 대중교통 타는 사진 업로드"
          value={authMethod}
          onChange={authMethodChangeHandler}
        ></textarea>
        <div className={styles.length}>{authMethod.length}/100</div>
      </div>

      <div className={styles.auth_method}>
        <div>인증샷 예시등록(5장이 필요해요)</div>
        <div className={styles.auth_img}>
          <div>
            인증 성공 예시를 추가해주세요
            <img
              src={`${process.env.PUBLIC_URL}/images/image.png`}
              alt="더미"
            />
          </div>
          <div>
            인증 성공 예시를 추가해주세요
            <img
              src={`${process.env.PUBLIC_URL}/images/image.png`}
              alt="더미"
            />
          </div>
          <div>
            인증 성공 예시를 추가해주세요
            <img
              src={`${process.env.PUBLIC_URL}/images/image.png`}
              alt="더미"
            />
          </div>
          <div>
            인증 성공 예시를 추가해주세요
            <img
              src={`${process.env.PUBLIC_URL}/images/image.png`}
              alt="더미"
            />
          </div>
          <div>
            인증 성공 예시를 추가해주세요
            <img
              src={`${process.env.PUBLIC_URL}/images/image.png`}
              alt="더미"
            />
          </div>
        </div>
      </div>
    </Fragment>
  );
};

export default CreateChallenge;
