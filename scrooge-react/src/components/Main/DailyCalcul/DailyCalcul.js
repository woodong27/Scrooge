import PayList from "./PayList";
import styles from "./DailyCalcul.module.css";
import { useEffect, useState } from "react";

const DailyCalcul = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/payment-history/1/today")
      .then((resp) => resp.json())
      .then((data) => setData(data))
      .catch((error) => console.log(error));
  }, []);

  // const Dummy = [
  //   {
  //     name: "(주)우아한형제들",
  //     amount: "12,000",
  //     date: "17:52",
  //   },
  //   {
  //     name: "다이소",
  //     amount: "5,000",
  //     date: "21:00",
  //   },
  //   {
  //     name: "교촌치킨",
  //     amount: "2,000",
  //     date: "23:00",
  //   },
  // ];

  return (
    <div>
      <div className={styles.header}>7월 19일 소비</div>
      {data.map((item) => (
        <PayList
          key={item.userId}
          name={item.usedAt}
          amount={item.amount}
          date={item.paidAt}
          cardName={item.cardName}
        ></PayList>
      ))}
    </div>
  );
};

export default DailyCalcul;
