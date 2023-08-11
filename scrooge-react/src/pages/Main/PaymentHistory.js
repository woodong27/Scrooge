import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import PaymentItem from "./PaymentItem";
import PaymentAdd from "./PaymentAdd";
import styles from "./PaymentHistory.module.css";
import Modal from "../../components/UI/Modal";

const PaymentHistory = ({ total, getTotal, consumFalseHandler }) => {
  const globalToken = useSelector((state) => state.globalToken); //렌더링 여러번 되는 기분?

  const [data, setData] = useState([]);
  const [date, setDate] = useState([]);
  const [origin, setOrigin] = useState();

  const [currentIndex, setCurrentIndex] = useState(0);
  const currentItem = data[currentIndex];
  const [modal, setModal] = useState(false);
  const [settlement, setSettlement] = useState(false);

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

  //전날로 이동
  const datebeforeHandler = () => {
    const [currentMonth, currentDay] = date;
    const previousDate = new Date();
    previousDate.setMonth(currentMonth - 1);
    previousDate.setDate(currentDay - 1);
    setDate([previousDate.getMonth() + 1, previousDate.getDate()]);
  };
  //다음날로 이동
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
    const today = new Date();
    const month = today.getMonth() + 1;
    const day = today.getDate();
    if (month === date[0] && day === date[1]) {
      getTotal();
    }

    if (date.length === 2) {
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
        `https://day6scrooge.duckdns.org/api/payment-history/date/${formattedDate}`,
        postData
      )
        .then((resp) => resp.json())
        .then((data) => {
          setData(data);
          //첫 false를 index로 지정
          setCurrentIndex(data.findIndex((item) => !item.isSeetled));
        })
        .catch((error) => console.log(error));
    }
  };

  useEffect(() => {
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
      `https://day6scrooge.duckdns.org/api/payment-history/${targetId}`,
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
    // fetch(
    //   "https://day6scrooge.duckdns.org/api/payment-history/settlement-exp",
    //   postData
    // )
    //   .then((res) => res.json())
    // .then(console.log);
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

            {settlement ? (
              <button className={styles.finishBtn}>정산완료</button>
            ) : (
              <button onClick={handleOpenModal} className={styles.btn}>
                정산하기
              </button>
            )}
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
