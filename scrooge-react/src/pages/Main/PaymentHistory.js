import PaymentItem from "./PaymentItem";
import PaymentAdd from "./PaymentAdd";
import styles from "./PaymentHistory.module.css";
import { useEffect, useState } from "react";

const PaymentHistory = () => {
  const [data, setData] = useState([]);
  const [date, setDate] = useState([]);

  useEffect(() => {
    getCurrentDate();
    fetch("http://localhost:8080/payment-history/1/today")
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
      })
      .catch((error) => console.log(error));
  }, []);

  const getCurrentDate = () => {
    const today = new Date();
    const month = today.toLocaleString("default", { month: "long" });
    const day = today.getDate();
    setDate(`${month} ${day}일 소비`);
  };

  const postExp = () => {};

  return (
    <div>
      <div className={styles.empty} />
      <div className={styles.card}>
        <div className={styles.date}>{date}</div>
        <div className={styles.scrollitem}>
          <div className={styles.item}>
            {data.map((it) => (
              <PaymentItem key={it.id} {...it} />
            ))}
            <PaymentAdd />
            <div className={styles.total}>총합: {}원</div>
            <button className={styles.btn} onClick={postExp()}>
              정산하기
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PaymentHistory;
