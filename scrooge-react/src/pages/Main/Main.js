import { useEffect, Fragment, useState } from "react";

import ProgressBar from "./ProgressBar";
import styles from "./Main.module.css";
import CharacterCard from "../../components/UI/CharacterCard";
import TodayCard from "../../components/UI/TodayCard";
import BackGround from "../../components/BackGround";
import DailyCalcul from "../../pages/Main/DailyCalcul";

const Main = (props) => {
  // const [data, setData] = useState([]);

  // useEffect(() => {
  //   fetch("http://localhost:8080/member")
  //  //토큰 인증.. 넣어서 하기..!! 근데 그러려면 로그인/회원가입이 먼저 되야할 것 같은디
  //     .then((resp) => resp.json())
  //     .then((data) => setData(data))
  //     .catch((error) => console.log(error));
  // }, []);
  const [isConsum, setIsConsum] = useState(false);

  const consumTrueHandler = () => {
    setIsConsum(true);
  };
  const consumFalseHandler = () => {
    setIsConsum(false);
  };

  return (
    <BackGround>
      {!isConsum /* && data && data.level && data.mainAvatar && */ && (
        <div>
          <div className={styles.empty} />
          <CharacterCard>
            <div>
              <div className={styles.infoheader}>
                <img
                  className={styles.badge}
                  src={`${process.env.PUBLIC_URL}/images/sample-badge.svg`}
                  alt="뱃지"
                />
                {/* <p>Lv. {data.level.level}</p> */}
                {/* <p>{data.name}</p> */}
                <span>
                  <p>Lv. 3 </p>
                  <p> 돈그만써</p>
                </span>
              </div>
              <div className={styles.border} />

              <span className={styles.charactercoin}>
                <img
                  className={styles.character}
                  src={`${process.env.PUBLIC_URL}/Character/1.png`}
                  alt="캐릭터"
                />

                <div className={styles.coin} onClick={consumTrueHandler}>
                  <img
                    src={`${process.env.PUBLIC_URL}/images/coin.png`}
                    alt="코인"
                  />
                  <div className={styles.payBtn}>정산하기</div>
                </div>
              </span>
              <div className={styles.statemessage}>
                <div>
                  상태메세지 <br />
                  에엥
                  <br />
                  괜찮나?
                </div>
                <img
                  className={styles.editBtn}
                  src={`${process.env.PUBLIC_URL}/images/write.svg`}
                  alt="코인"
                />
              </div>
            </div>
          </CharacterCard>
          <div className={styles.rings}>
            <img
              className={styles.ring}
              src={`${process.env.PUBLIC_URL}/images/main-ring.png`}
              alt="고리"
            />
            <img
              className={styles.ring}
              src={`${process.env.PUBLIC_URL}/images/main-ring.png`}
              alt="고리"
            />
            <img
              className={styles.ring}
              src={`${process.env.PUBLIC_URL}/images/main-ring.png`}
              alt="고리"
            />
          </div>
          <TodayCard>
            <div className={styles.todayCard}>
              <div className={styles.title}>8월 2일, 오늘의 소비💸</div>
              <div className={styles.amount}>정산이 필요해요!</div>
            </div>
            <ProgressBar></ProgressBar>
          </TodayCard>
        </div>
      )}
      {isConsum && (
        <div>
          <DailyCalcul />
        </div>
      )}
    </BackGround>
  );
};

export default Main;
