import { Link, useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import { useSelector } from "react-redux";

import styles from "./ChallengeJoin.module.css";
import backImg from "../../assets/back.png";

const ChallengeJoin = () => {
  const [data, setData] = useState([]);
  const params = useParams();

  const joinChallengeHandler = () => {};

  useEffect(() => {
    axios
      .get(`https://day6scrooge.duckdns.org/api/challenge/${params.id}`)
      .then((response) => {
        setData(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

  return (
    <div className={styles.layout}>
      <Link className={styles.back} to="/challenge">
        <img src={backImg} alt="뒤로가기"></img>
      </Link>
      <img
        className={styles.img}
        src={`${process.env.PUBLIC_URL}/images/dummy.png`}
        alt="더미"
      />
      <div className={styles.container}>
        <h1>{data.title}</h1>
        <p className={styles.tag}>
          #{data.category} #{data.period}
        </p>
        <div className={styles.content}>
          <span>
            현재인원: {data.currentParticipants} / {data.minParticipants}명
          </span>
          <span>경험치 500+</span>
        </div>
      </div>
      <div className={styles.mid_container}>
        <h2>이렇게 인증해주세요</h2>
        <div>- 매일 전날의 소비 내역을 불러와주세요.</div>
        <div>- 사진이 잘 보이도록 촬영해주세요.</div>
        <div className={styles.auth_img}>
          <img
            className={styles.img}
            src={`${process.env.PUBLIC_URL}/images/dummy.png`}
            alt="더미"
          />
          <img
            className={styles.img}
            src={`${process.env.PUBLIC_URL}/images/dummy.png`}
            alt="더미"
          />
          <img
            className={styles.img}
            src={`${process.env.PUBLIC_URL}/images/dummy.png`}
            alt="더미"
          />
          <img
            className={styles.img}
            src={`${process.env.PUBLIC_URL}/images/dummy.png`}
            alt="더미"
          />
          <img
            className={styles.img}
            src={`${process.env.PUBLIC_URL}/images/dummy.png`}
            alt="더미"
          />
          <img
            className={styles.img}
            src={`${process.env.PUBLIC_URL}/images/dummy.png`}
            alt="더미"
          />
        </div>
      </div>
      <div className={styles.bottom_container}>
        <h2>이런 분들께 추천해요</h2>
        <div>{data.description}</div>
      </div>

      <div className={styles.primary} onClick={joinChallengeHandler}>
        챌린지에 도전할래요!
        <div className={styles.shadow}></div>
      </div>
    </div>
  );
};

export default ChallengeJoin;
