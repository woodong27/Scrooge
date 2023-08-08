import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import PaymentItem from "./PaymentItem";
import PaymentAdd from "./PaymentAdd";
import styles from "./PaymentHistory.module.css";
import Modal from "../../components/UI/Modal";

const PaymentHistory = ({
  total,
  getTotal,
  consumFalseHandler,
  settlement,
  settlementTrueHandler,
  settlementFalseHandler,
}) => {
  const globalToken = useSelector((state) => state.globalToken); //렌더링 여러번 되는 기분?

  const [data, setData] = useState([]);
  const [date, setDate] = useState([]);
  const [origin, setOrigin] = useState();

  const [currentIndex, setCurrentIndex] = useState(0);
  const currentItem = data[currentIndex];
  const [modal, setModal] = useState(false);

  const handleOpenModal = () => {
    // 소비가 0건인 경우 예외 처리
    if (data.length < 1) {
      getTotal();
      return;
    }
    setModal(true);
  };

  const handleCloseModal = () => {
    setModal(false);
  };

  const datebeforeHandler = () => {
    const [currentMonth, currentDay] = date;
    const previousDate = new Date();
    previousDate.setMonth(currentMonth - 1);
    previousDate.setDate(currentDay - 1);
    setDate([previousDate.getMonth() + 1, previousDate.getDate()]);
  };
  const dateafterHandler = () => {
    const [currentMonth, currentDay] = date;
    const nextDate = new Date();
    const today = new Date();
    nextDate.setMonth(currentMonth - 1);
    nextDate.setDate(currentDay + 1);
    if (nextDate <= today) {
      setDate([nextDate.getMonth() + 1, nextDate.getDate()]);
    }
  };

  // // 오늘 날짜 가져오기
  // const getCurrentDate = () => {
  // };

  useEffect(() => {
    const today = new Date();
    const month = today.getMonth() + 1;
    const day = today.getDate();
    setDate([month, day]);
  }, []);

  const goNext = () => {
    if (currentIndex < data.length - 1) {
      setCurrentIndex(currentIndex + 1);
    } else {
      console.log("끝");
      settlementTrueHandler(); //정산되었음을 저장
      postExp();
      getTotal();
      setCurrentIndex(currentIndex + 1);
      handleCloseModal();
    }
  };

  useEffect(() => {
    getPaymentHistory();
  }, [date]);

  // 오늘 소비 내역 불러오기
  const getPaymentHistory = () => {
    if (date.length === 2) {
      console.log(date[0], date[1]);
      const formattedDate = `2023-${date[0]
        .toString()
        .padStart(2, "0")}-${date[1].toString().padStart(2, "0")}`;

      const postData = {
        method: "GET",
        headers: {
          Authorization: globalToken,
        },
      };

      fetch(
        `http://day6scrooge.duckdns.org:8081/payment-history/date/${formattedDate}`,
        postData
      )
        .then((resp) => resp.json())
        .then((data) => {
          //데이터가 없는 경우도 처리해야겠지?
          setData(data);
        })
        .catch((error) => console.log(error));
    }
  };

  useEffect(() => {
    console.log(total);
    setOrigin(total);
  }, [total]);

  const onCreate = (targetId, usedAt, amount, paidAt, cardName) => {
    const newItem = {
      id: targetId,
      paidAt,
      amount,
      usedAt,
      cardName,
    };
    setData([...data, newItem]);
    if (settlement) {
      getTotal();
    }
    settlementFalseHandler();
  };

  const onEdit = (targetId, amount, usedAt, cardName, category) => {
    const obj = {
      amount,
      usedAt,
      cardName,
      category,
    };
    const postData = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
      body: JSON.stringify(obj),
    };
    fetch(
      `http://day6scrooge.duckdns.org:8081/payment-history/${targetId}`,
      postData
    )
      .then((res) => res.text())
      .then(console.log);
    setData(
      data.map((it) =>
        it.id === targetId ? { ...it, cardName, amount, usedAt } : it
      )
    );
  };

  // 일일정산 경험치 추가
  const postExp = () => {
    const postData = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    fetch(
      "http://day6scrooge.duckdns.org:8081/payment-history/settlement-exp",
      postData
    )
      .then((res) => res.text())
      .then(console.log);
  };

  return (
    <div>
      <div className={styles.empty}>
        <button onClick={consumFalseHandler}>홈 으로</button>
      </div>
      <div className={styles.card}>
        <div className={styles.title}>
          <img
            src={`${process.env.PUBLIC_URL}/images/left.svg`}
            onClick={datebeforeHandler}
            alt="왼쪽"
          />
          <div className={styles.date}>
            {date[0]}월 {date[1]}일 소비
          </div>
          <img
            src={`${process.env.PUBLIC_URL}/images/right.svg`}
            onClick={dateafterHandler}
            alt="오른쪽"
          />
        </div>

        <div className={styles.scrollitem}>
          <div className={styles.item}>
            {data.length > 0 ? (
              data.map((it, index) => (
                <PaymentItem key={index} {...it} onEdit={onEdit} />
              ))
            ) : (
              <p>!!소비 내역이 없습니다!!</p>
            )}
            <PaymentAdd onCreate={onCreate} date={date} />
            <div className={styles.total}>
              {origin || origin === 0
                ? `총합: ${origin
                    .toString()
                    .replace(/\B(?=(\d{3})+(?!\d))/g, ",")}원`
                : ""}
            </div>
            <button
              onClick={handleOpenModal}
              className={settlement ? styles.finishBtn : styles.btn}
            >
              {settlement ? "정산 완료" : "정산하기"}
            </button>
          </div>
        </div>
      </div>
      {modal && (
        <Modal
          item={currentItem}
          index={currentIndex}
          goNext={goNext}
          onEdit={onEdit}
          onCloseModal={handleCloseModal}
        />
      )}
    </div>
  );
};

export default PaymentHistory;
