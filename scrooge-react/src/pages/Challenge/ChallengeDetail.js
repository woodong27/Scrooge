import { Link } from "react-router-dom";

import backImg from "../../assets/back.png";
import styles from "./ChallengeDetail.module.css";
import ProgressBar from "./ProgressBar";

const ChallengeDetail = () => {
  return (
    <div>
      <div className={styles.img}>
        <Link className={styles.back} to="/challenge">
          <img src={backImg} alt="뒤로가기"></img>
        </Link>
        <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="더미" />
        <div>
          <p className={styles.day}>7.31(월) - 8.13(일)</p>
          배달 일주일에 한 번만 시키기
          <p>#식비 #일주일 #경험치 50+</p>
        </div>
      </div>
      <div className={styles.fight_container}>
        <div style={{ marginBottom: "10px" }}>대전 현황</div>
        <ProgressBar ally={60} enemy={40}></ProgressBar>
      </div>
      <hr></hr>
    </div>
  );
};

export default ChallengeDetail;
