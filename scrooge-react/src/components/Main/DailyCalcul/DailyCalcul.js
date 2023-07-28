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

  const getCurrentDate = () => {
    const today = new Date();
    const month = today.toLocaleString("default", { month: "long" });
    const day = today.getDate();
    return `${month} ${day}일 소비`;
  };

  return (
    <div>
      <div className={styles.header} id="date">
        {getCurrentDate()}
      </div>
      <div className={styles.box}>
        {data.map((item) => (
          <PayList
            key={item.id}
            name={item.usedAt}
            amount={item.amount}
            date={item.paidAt.split("T")[1].split(".")[0]}
            cardName={item.cardName}
          ></PayList>
        ))}
      </div>
    </div>
  );
};

export default DailyCalcul;
