import { Link } from "react-router-dom";
import axios from "axios";
import { useState, useEffect } from "react";

import backImg from "../../assets/back.png";
import styles from "./ChallengeDetail.module.css";
import ProgressBar from "./ProgressBar";

const ChallengeDetail = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get("http://day6scrooge.duckdns.org:8081/challenge/1")
      .then((response) => {
        setData(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

  console.log(data);

  return (
    <div>
      <div className={styles.img}>
        <Link className={styles.back} to="/challenge">
          <img src={backImg} alt="뒤로가기"></img>
        </Link>
        <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="더미" />
        <div className={styles.title}>
          <p className={styles.day}>7.31(월) - 8.13(일)</p>
          {data.title}
          <p>
            #{data.category} #{data.period} #경험치 500+
          </p>
        </div>
      </div>
      <div className={styles.fight_container}>
        <div>대전 현황</div>
        <ProgressBar ally={60} enemy={40}></ProgressBar>
      </div>
      <hr></hr>
    </div>
  );
};

export default ChallengeDetail;
