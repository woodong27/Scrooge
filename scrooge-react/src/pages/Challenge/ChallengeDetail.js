import { Link } from "react-router-dom";
import axios from "axios";
import { useState, useEffect } from "react";
import { useSelector } from "react-redux";

import backImg from "../../assets/back.png";
import styles from "./ChallengeDetail.module.css";
import ProgressBar from "./ProgressBar";
import Chips from "../../components/UI/Chips";
import AuthProgress from "./AuthProgress";

const ChallengeDetail = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const headers = { Authorization: globalToken };
  const [data, setData] = useState([]);
  const [selectChip, setSelectChip] = useState("나의 인증 현황");

  useEffect(() => {
    axios
      .get(
        `https://day6scrooge.duckdns.org/api/challenge/6/start-my-challenge`,
        { headers }
      )
      .then((response) => {
        setData(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

  return (
    <div className={styles.layout}>
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
      <div className={styles.auth_condition}>
        <Chips selected={selectChip} setSelect={setSelectChip}>
          나의 인증 현황
        </Chips>
        <Chips selected={selectChip} setSelect={setSelectChip}>
          우리팀 인증 현황
        </Chips>
      </div>

      {selectChip === "나의 인증 현황" ? (
        <div>
          <AuthProgress per={60}></AuthProgress>
          <div className={styles.my_auth_container}>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>1</div>
              <div>8/3</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>2</div>
              <div>8/4</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>3</div>
              <div>8/5</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>4</div>
              <div>8/6</div>
              <div className={styles.fail}>실패</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
          </div>
        </div>
      ) : (
        <div>
          <AuthProgress per={60}></AuthProgress>
          <div className={styles.img_container}>
            <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
          </div>
        </div>
      )}

      <div className={styles.foot}>
        <div className={styles.primary}>
          인증하기
          <div className={styles.shadow}></div>
        </div>
      </div>
    </div>
  );
};

export default ChallengeDetail;
