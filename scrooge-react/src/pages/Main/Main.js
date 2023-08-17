import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import ProgressBar from "./ProgressBar";
import styles from "./Main.module.css";
import Card from "../../components/UI/Card";
import BackGround from "../../components/BackGround";
import PaymentHistory from "../../pages/Main/PaymentHistory";

const Main = () => {
  const globalToken = useSelector((state) => state.globalToken);

  const [data, setData] = useState([]);
  const [total, setTotal] = useState(0);
  const [date, setDate] = useState([]);

  const [settlement, setSettlement] = useState(false);
  const [isConsum, setIsConsum] = useState(false);

  const [message, setMessage] = useState();
  const [weeklyGoal, setWeeklyGoal] = useState();
  const [weeklyConsum, setWeeklyConsum] = useState();

  const [images, setImages] = useState([
    "https://storage.googleapis.com/scroogestorage/avatars/2-1.png",
    "https://storage.googleapis.com/scroogestorage/avatars/2-2.png",
  ]);
  const [imageIndex, setImageIndex] = useState(0);

  const handleSetTrue = () => {
    setSettlement(true);
  };

  const handleSetFalse = () => {
    setSettlement(false);
  };

  useEffect(() => {
    const today = new Date();
    const month = today.getMonth() + 1;
    const day = today.getDate();
    setDate([month, day]);
    const postData = {
      method: "GET",
      headers: {
        Authorization: globalToken,
      },
    };
    fetch("https://day6scrooge.duckdns.org/api/member/info", postData)
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
        setSettlement(data.isSettlementDone);
        setMessage(data.message);
        setWeeklyGoal(data.weeklyGoal);
        setWeeklyConsum(data.weeklyConsum);
        setImages([
          `https://storage.googleapis.com/scroogestorage/avatars/${data.mainAvatar.id}-1.png`,
          `https://storage.googleapis.com/scroogestorage/avatars/${data.mainAvatar.id}-2.png`,
        ]);
      })
      .catch((error) => console.log(error));

    const formattedDate = `2023-${month.toString().padStart(2, "0")}-${day
      .toString()
      .padStart(2, "0")}`;
    getTotal(formattedDate);
  }, [isConsum]);

  //이미지 움직이게

  const changeImage = () => {
    setImageIndex((prevIndex) => (prevIndex + 1) % images.length);
  };
  useEffect(() => {
    const imageInterval = setInterval(changeImage, 1000); // 1초마다 이미지 변경

    return () => {
      clearInterval(imageInterval);
    };
  }, []);

  //주간 목표 설정
  const setGoal = (goal) => {
    const obj = { weeklyGoal: goal };
    const postData = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
      body: JSON.stringify(obj),
    };
    fetch(`https://day6scrooge.duckdns.org/api/member/weekly-goal`, postData)
      .then((res) => res.json())
      .then((data) => {
        setWeeklyGoal(data.weeklyGoal);
      })
      .then(console.log);
  };

  const getTotal = (formattedDate) => {
    const postData = {
      method: "GET",
      headers: {
        Authorization: globalToken,
      },
    };
    fetch(
      `https://day6scrooge.duckdns.org/api/payment-history/date-total/${formattedDate}`,
      postData
    )
      .then((resp) => resp.json())
      .then((data) => {
        setTotal(data);
      })
      .catch((error) => console.log(error));
  };

  const consumTrueHandler = () => {
    setIsConsum(true);
  };

  const consumFalseHandler = () => {
    setIsConsum(false);
  };

  return (
    <BackGround>
      {!isConsum && data && data.levelId && data.mainAvatar.id && (
        <div>
          <div className={styles.body}>
            <Card height={44}>
              <div className={styles.oneCard}>
                <div className={styles.infoheader}>
                  <img
                    className={styles.badge}
                    src={`${process.env.PUBLIC_URL}/images/sample-badge.svg`}
                    alt="뱃지"
                  />
                  <span>
                    <p>Lv. {data.levelId}</p>
                    <p>{data.nickname}</p>
                  </span>
                </div>
                <div className={styles.border} />

                {settlement ? (
                  //정산 완료
                  <span className={styles.charactercoin}>
                    <img
                      className={styles.character}
                      src={`https://storage.googleapis.com/scroogestorage/avatars/${data.mainAvatar.id}-1.png`}
                      alt="캐릭터"
                    />

                    <div className={styles.coin} onClick={consumTrueHandler}>
                      <img
                        src={`${process.env.PUBLIC_URL}/images/coin.png`}
                        alt="코인"
                      />
                      <div className={styles.payBtn}>정산하기</div>{" "}
                      <div className={styles.streak}>{data.streak}일 째</div>
                    </div>
                  </span>
                ) : (
                  //정산 전
                  <span className={styles.charactercoin}>
                    <img
                      className={styles.character}
                      src={images[imageIndex]}
                      alt="캐릭터"
                    />

                    {imageIndex === 1 && (
                      <div className={styles.coin} onClick={consumTrueHandler}>
                        <img
                          src={`${process.env.PUBLIC_URL}/images/coin.png`}
                          alt="코인"
                        />
                        <div className={styles.payBtn}>정산하기</div>{" "}
                        <div className={styles.streak}>{data.streak}일 째</div>
                      </div>
                    )}
                  </span>
                )}

                <div className={styles.statemessage}>
                  <textarea
                    className={styles.content}
                    value={message}
                    readOnly
                  />
                </div>
              </div>
            </Card>
            <div className={styles.rings}></div>
            <Card height={28}>
              <div className={styles.todayCard}>
                <div>
                  <div className={styles.title}>
                    {date[0]}월 {date[1]}일, 오늘의 소비💸
                  </div>
                  <div className={styles.amount}>
                    {settlement
                      ? `${total
                          .toString()
                          .replace(/\B(?=(\d{3})+(?!\d))/g, ",")}원`
                      : "정산이 필요해요!"}
                  </div>
                </div>
                <ProgressBar
                  goal={weeklyGoal}
                  consum={weeklyConsum}
                  setGoal={setGoal}
                ></ProgressBar>
              </div>
            </Card>
          </div>
        </div>
      )}
      {isConsum && (
        <div>
          <PaymentHistory
            total={total}
            getTotal={getTotal}
            todaySettlement={settlement}
            consumFalseHandler={consumFalseHandler}
            todayProp={date}
            handleSetFalse={handleSetFalse}
            handleSetTrue={handleSetTrue}
          />
        </div>
      )}
    </BackGround>
  );
};

export default Main;
