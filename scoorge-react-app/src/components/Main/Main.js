import { Fragment, useEffect, useState } from "react";
// import { Link } from "react-router-dom";
import ProgressBar from "./ProgressBar";
import styles from "./Main.module.css";
import Card from "../UI/Card";
import ButtonWhite from "../UI/ButtonWhite";
import DailyCalcul from "./DailyCalcul/DailyCalcul";

const Main = (props) => {
  const [isConsum, setIsConsum] = useState(false);

  const consumTrueHandler = () => {
    setIsConsum(true);
  };
  const consumFalseHandler = () => {
    setIsConsum(false);
  };

  return (
    <Fragment>
      <Card>
        {!isConsum && (
          <div>
            <div className={styles.header34}>
              <p>Lv. 3</p>
              <p>돈그만써</p>
            </div>
            <div className={styles.box}>
              <img
                className={styles.img}
                src={`${process.env.PUBLIC_URL}/Character/${
                  ~~(Math.random() * 88) + 1
                }.png`}
                alt="캐릭터"
              />
              <ProgressBar />
            </div>
            <button className={styles.btn} onClick={consumTrueHandler}>
              일일정산
            </button>
            <button onClick={props.onLogout}>로그아웃</button>
          </div>
        )}
        {isConsum && (
          <div>
            <DailyCalcul />
            <button onClick={consumFalseHandler}>돌아가기</button>
          </div>
        )}
      </Card>

      <div className={styles.foot}>
        <ButtonWhite text="자전거로 출퇴근하기"></ButtonWhite>
        <ButtonWhite text="집에서 저녁 만들어 먹기"></ButtonWhite>
        <ButtonWhite text="파 키우기"></ButtonWhite>
      </div>
    </Fragment>
  );
};

export default Main;
