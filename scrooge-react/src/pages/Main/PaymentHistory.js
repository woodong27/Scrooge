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
  setSettlement,
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

  const goNext = () => {
    if (currentIndex < data.length - 1) {
      setCurrentIndex(currentIndex + 1);
    } else {
      console.log("끝");
      setSettlement(true); //정산되었음을 저장
      postExp();
      getTotal();
      handleCloseModal();
    }
  };

  // 오늘 소비 내역 불러오기
  useEffect(() => {
    const postData = {
      method: "GET",
      headers: {
        Authorization: globalToken,
      },
    };
    getCurrentDate();
    fetch("http://day6scrooge.duckdns.org:8081/payment-history/today", postData)
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
      })
      .catch((error) => console.log(error));
  }, []);

  useEffect(() => {
    setOrigin(total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
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
  };

  const onEdit = (targetId, newContent, usedAt, cardName, category) => {
    const obj = {
      amount: newContent,
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
        it.id === targetId ? { ...it, amount: newContent } : it
      )
    );
  };

  // 오늘 날짜 가져오기
  const getCurrentDate = () => {
    const today = new Date();
    const month = today.toLocaleString("default", { month: "long" });
    const day = today.getDate();
    setDate(`${month} ${day}일 소비`);
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
            alt="커뮤니티"
          />
          <div className={styles.date}>{date}</div>
          <img
            src={`${process.env.PUBLIC_URL}/images/right.svg`}
            alt="커뮤니티"
          />
        </div>

        <div className={styles.scrollitem}>
          <div className={styles.item}>
            {data.map((it, index) => (
              <PaymentItem key={index} {...it} onEdit={onEdit} />
            ))}
            <PaymentAdd onCreate={onCreate} />
            {/* 아 origin이 truthy한 값이라 이상했네...  */}
            <div className={styles.total}>
              {origin || origin === 0 ? `총합: ${origin}원` : ""}
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
