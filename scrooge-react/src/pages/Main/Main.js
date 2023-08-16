import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import ProgressBar from "./ProgressBar";
import styles from "./Main.module.css";
import CharacterCard from "../../components/UI/CharacterCard";
import Card from "../../components/UI/Card";
import BackGround from "../../components/BackGround";
import PaymentHistory from "../../pages/Main/PaymentHistory";

const Main = (props) => {
  const globalToken = useSelector((state) => state.globalToken);

  const [data, setData] = useState([]);
  const [total, setTotal] = useState(0);
  const [date, setDate] = useState([]);

  const [settlement, setSettlement] = useState(false);
  const [isConsum, setIsConsum] = useState(false);
  const [isEdit, setIsEdit] = useState(false);

  const [message, setMessage] = useState();
  const [weeklyGoal, setWeeklyGoal] = useState();
  const [weeklyConsum, setWeeklyConsum] = useState();

  const [images, setImages] = useState([
    "https://storage.googleapis.com/scroogestorage/avatars/2-1.png",
    "https://storage.googleapis.com/scroogestorage/avatars/2-2.png",
  ]);
  const [imageIndex, setImageIndex] = useState(0);

  // BGM 관련
  // const [isSoundOn, setIsSoundOn] = useState(true);

  const handleOpen = () => {
    setIsEdit(true);
  };
  const handleClose = () => {
    setMessage(data.message);
    setIsEdit(false);
  };
  const handleSetTrue = () => {
    setSettlement(true);
  };

  const handleSetFalse = () => {
    setSettlement(false);
  };

  const handleSend = () => {
    const obj = {
      message: message,
    };
    const postData = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
      body: JSON.stringify(obj),
    };
    fetch("https://day6scrooge.duckdns.org/api/member/message", postData)
      .then((res) => {
        if (!res.ok) {
          throw new Error("상태메시지 저장 실패");
        }
        return res.json();
      })
      .then((data) => {
        setData(data);
        setMessage(data.message);
        setIsEdit(false);
      });
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

  // BGM 관련 코드
  // useEffect(() => {
  //   const storedSoundStatus = localStorage.getItem("isSoundOn");
  //   if (storedSoundStatus !== null) {
  //     setIsSoundOn(storedSoundStatus === "true");
  //   }
  //   else {
  //     setIsSoundOn(true);
  //     localStorage.setItem("isSoundOn", "true");
  //   }
  // }, []);

  // if(window.AndroidSound) {
  //   window.AndroidSound.sendSoundToggleToAndroid(isSoundOn);
  // }

  return (
    <BackGround>
      {!isConsum && data && data.levelId && data.mainAvatar.id && (
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
                {isEdit ? (
                  <>
                    <textarea
                      className={styles.content}
                      value={message}
                      onChange={(e) => setMessage(e.target.value)}
                      maxLength="50"
                      rows="3"
                    />
                    <div className={styles.line}>
                      <button
                        className={styles.cancleBtn}
                        onClick={handleClose}
                      >
                        취소
                      </button>
                      <button
                        className={styles.completeBtn}
                        onClick={handleSend}
                      >
                        완료
                      </button>
                    </div>
                  </>
                ) : (
                  <>
                    <textarea
                      className={styles.content}
                      value={message}
                      readOnly
                    />

                    <div className={styles.line}>
                      <img
                        className={styles.editBtn}
                        src={`${process.env.PUBLIC_URL}/images/write.svg`}
                        alt="수정"
                        onClick={handleOpen}
                      />
                    </div>
                  </>
                )}
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
          <Card height={236}>
            <div className={styles.todayCard}>
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
          </Card>
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
