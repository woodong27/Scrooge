import PaymentItem from "./PaymentItem";
import styles from "./PaymentHistory.module.css";
import { useEffect, useState } from "react";

const DailyCalcul = () => {
  const [data, setData] = useState([]);
  const [date, setDate] = useState([]);

  useEffect(() => {
    getCurrentDate();
    fetch("http://localhost:8080/payment-history/1/today")
      .then((resp) => resp.json())
      .then((data) => setData(data))
      .catch((error) => console.log(error));
  }, []);

  const getCurrentDate = () => {
    const today = new Date();
    const month = today.toLocaleString("default", { month: "long" });
    const day = today.getDate();
    setDate(`${month} ${day}일 소비`);
  };

  return (
    <div>
      <div className={styles.empty} />
      <div className={styles.card}>
        <div className={styles.date}>{date}</div>
        <div>{data.length}</div>
        {data.map((it) => (
          <PaymentItem key={it.id} {...it} />
        ))}
      </div>
    </div>
  );
};

export default DailyCalcul;
