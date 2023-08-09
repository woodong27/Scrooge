import { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { useSelector } from "react-redux";

import styles from "./CreateChallenge.module.css";
import Header from "../../components/Header";
import Chips from "../../components/UI/Chips";
import backImg from "../../assets/back.png";
import Toast from "../../components/UI/Toast";

const CreateChallenge = () => {
  const globalToken = useSelector((state) => state.globalToken);

  const period = ["1주", "2주", "3주", "한달"];
  const category = ["식비", "교통", "쇼핑", "기타"];
  const peoples = ["4명", "6명", "8명", "10명"];

  const [selectPeriod, setSelectPeriod] = useState("1주");
  const [selectCategory, setSelectCategory] = useState("식비");
  const [selectPeoples, setSelectPeoples] = useState("4명");

  const [title, setTitle] = useState("");
  const [authMethod, setAuthMethod] = useState("");
  const [introduce, setIntroduce] = useState("");

  const [toast, setToast] = useState(false);

  const titleChangeHandler = (event) => {
    setTitle(event.target.value);
  };
  const authMethodChangeHandler = (event) => {
    setAuthMethod(event.target.value);
  };
  const introduceChangeHandler = (event) => {
    setIntroduce(event.target.value);
  };

  const CreateChallengeHandler = () => {
    if (
      title.length === 0 ||
      authMethod.length === 0 ||
      introduce.length === 0
    ) {
      setToast(true);
      return;
    }

    const postData = {
      title: title,
      category: selectCategory,
      minParticipants: parseInt(selectPeoples),
      authMethod: authMethod,
      period: selectPeriod,
      description: introduce,
    };

    const headers = {
      "Content-Type": "application/json",
      Authorization: globalToken,
    };

    axios
      .post(
        "https://day6scrooge.duckdns.org/api/challenge",
        JSON.stringify(postData),
        {
          headers,
        }
      )
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  };

  return (
    <div className={styles.layout}>
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
          <div id="period">
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
          <span>
            인증 성공 예시를 추가해주세요
            <img
              src={`${process.env.PUBLIC_URL}/images/image.png`}
              alt="더미"
            />
          </span>
          <span>
            인증 성공 예시를 추가해주세요
            <img
              src={`${process.env.PUBLIC_URL}/images/image.png`}
              alt="더미"
            />
          </span>
          <span>
            인증 성공 예시를 추가해주세요
            <img
              src={`${process.env.PUBLIC_URL}/images/image.png`}
              alt="더미"
            />
          </span>
          <span>
            인증 성공 예시를 추가해주세요
            <img
              src={`${process.env.PUBLIC_URL}/images/image.png`}
              alt="더미"
            />
          </span>
          <span>
            인증 성공 예시를 추가해주세요
            <img
              src={`${process.env.PUBLIC_URL}/images/image.png`}
              alt="더미"
            />
          </span>
        </div>
      </div>

      <div className={styles.auth_method}>
        <div>챌린지 소개 🙌</div>
        <p>바른 소비 습관을 만들 수 있는 챌린지를 소개해보세요.</p>

        <textarea
          placeholder="예) 매일 출퇴근 인증해서 건강도 챙기고 지갑도 챙겨봐요! :)"
          value={introduce}
          onChange={introduceChangeHandler}
        ></textarea>
        <div className={styles.length}>{introduce.length}/100</div>
      </div>

      <div className={styles.primary} onClick={CreateChallengeHandler}>
        챌린지 만들기
        <div className={styles.shadow}></div>
      </div>

      {toast && <Toast setToast={setToast} text="빠진 부분이 있어요!" />}
    </div>
  );
};

export default CreateChallenge;
