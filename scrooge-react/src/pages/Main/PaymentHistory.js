import { useEffect, useState } from "react";
import PaymentItem from "./PaymentItem";
import PaymentAdd from "./PaymentAdd";
import styles from "./PaymentHistory.module.css";
import Modal from "../../components/UI/Modal";

const PaymentHistory = () => {
  const [data, setData] = useState([]);
  const [date, setDate] = useState([]);

  // 오늘 소비 내역 불러오기
  useEffect(() => {
    getCurrentDate();
    fetch("http://day6scrooge.duckdns.org:8081/payment-history/1/today")
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
      })
      .catch((error) => console.log(error));
  }, []);

  const onCreate = (targetId, usedAt, amount, paidAt) => {
    const newItem = {
      id: targetId,
      paidAt,
      amount,
      usedAt,
    };
    setData([...data, newItem]);
  };

  const onEdit = (targetId, newContent, usedAt, cardName) => {
    const obj = {
      amount: newContent,
      usedAt,
      cardName,
    };
    const postData = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImhhcHB5QGdtYWlsLmNvbSIsIm1lbWJlcklkIjoyLCJpYXQiOjE2OTEwNTUzOTIsImV4cCI6MTY5MTY2MDE5Mn0.GSDDPI26jaeE7zZzhHGIlImyCWcZi3GbE6K8rIZhi30",
      },
      body: JSON.stringify(obj),
    };
    fetch(
      `http://day6scrooge.duckdns.org:8081/payment-history/1/${targetId}`,
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

  // 일일정산 경험치 추가 (나중에 오늘 날짜인지 확인 및 하루 한 번만 가능하게 변경)
  const postExp = () => {
    const obj = { id: "1" };
    const postData = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImhhcHB5QGdtYWlsLmNvbSIsIm1lbWJlcklkIjoyLCJpYXQiOjE2OTEwNTUzOTIsImV4cCI6MTY5MTY2MDE5Mn0.GSDDPI26jaeE7zZzhHGIlImyCWcZi3GbE6K8rIZhi30",
      },
      body: JSON.stringify(obj),
    };
    fetch(
      "http://day6scrooge.duckdns.org:8081/payment-history/settlement-exp",
      postData
    )
      .then((res) => res.text())
      .then(console.log);
  };

  const [modal, setModal] = useState(false);
  const handleOpenModal = () => {
    setModal(true);
  };

  const handleCloseModal = () => {
    setModal(false);
  };

  return (
    <div>
      <div className={styles.empty} />
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
            <div className={styles.total}>총합: {}원</div>
            <button className={styles.btn} onClick={handleOpenModal}>
              정산하기
            </button>
          </div>
        </div>
      </div>
      {/* {modal && <Modal onEdit={onEdit} onCloseModal={handleCloseModal} />} */}
    </div>
  );
};

export default PaymentHistory;
