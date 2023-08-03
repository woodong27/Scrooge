import PaymentItem from "./PaymentItem";
import PaymentAdd from "./PaymentAdd";
import styles from "./PaymentHistory.module.css";
import { useEffect, useState } from "react";

const PaymentHistory = () => {
  const [data, setData] = useState([]);
  const [date, setDate] = useState([]);

  // 오늘 소비 내역 불러오기
  useEffect(() => {
    getCurrentDate();
    fetch("http://localhost:8080/payment-history/1/today")
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
      })
      .catch((error) => console.log(error));
  }, []);

  const onCreate = (usedAt, amount, paidAt) => {
    console.log(`여기 ${paidAt}`);
    const newItem = {
      paidAt,
      amount,
      usedAt,
    };
    setData([...data, newItem]);
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
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImhhcmluMTRAbmF2ZXIuY29tIiwibWVtYmVySWQiOjEsImlhdCI6MTY5MTA0NDcyOCwiZXhwIjoxNjkxNjQ5NTI4fQ.JusnJ0lDLqH5nBSHCfFC40iKUbuwAHqCCxtqqrRC3W0",
      },
      body: JSON.stringify(obj),
    };
    fetch("http://localhost:8080/payment-history/settlement-exp", postData)
      .then((res) => res.text())
      .then(console.log);
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
            {data.map((it) => (
              <PaymentItem key={it.id} {...it} />
            ))}
            <PaymentAdd onCreate={onCreate} />
            <div className={styles.total}>총합: {}원</div>
            <button className={styles.btn} onClick={postExp}>
              정산하기
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PaymentHistory;
