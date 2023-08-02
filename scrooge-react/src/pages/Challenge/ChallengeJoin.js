import { Link } from "react-router-dom";

import ButtonBlue from "../../components/Button/ButtonBlue";
import styles from "./ChallengeJoin.module.css";
import backImg from "../../assets/back.png";

const ChallengeJoin = (props) => {
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
        <h1>배달 일주일에 한 번만 시키기</h1>
        <p className={styles.tag}>#식비 #일주일 </p>
        <div className={styles.content}>
          <span>현재인원: 6 / 10명</span>
          <span>경험치 50+</span>
        </div>
      </div>
      <div className={styles.container}>
        <h2>이렇게 인증해주세요</h2>
        <div>- 매일 전날의 소비 내역을 불러와주세요.</div>
        <div>- 사진이 잘 보이도록 촬영해주세요.</div>
        <div>{/* 인증 예시 이미지 */}</div>
      </div>
      <div className={styles.container}>
        <h2>이런 분들께 추천해요</h2>
        <div>
          - 배달비를 생각하지 않고 먹고 싶은 음식을 마구마구 시키는 분들
        </div>
        <div>- 포장으로 가능한 거리인데 귀찮아서 배달 시키는 분들</div>
      </div>
      <div className={styles.day}>7.31(월) - 8.13(일), 일주일 동안</div>
      <ButtonBlue>챌린지에 도전할래요!</ButtonBlue>
    </div>
  );
};

export default ChallengeJoin;
