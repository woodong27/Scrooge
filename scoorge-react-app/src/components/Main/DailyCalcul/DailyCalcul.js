import PayList from "./PayList";
import styles from "./DailyCalcul.module.css";

const DailyCalcul = () => {
  const Dummy = [
    {
      name: "(주)우아한형제들",
      amount: "12,000",
      date: "17:52",
    },
    {
      name: "다이소",
      amount: "5,000",
      date: "21:00",
    },
    {
      name: "교촌치킨",
      amount: "2,000",
      date: "23:00",
    },
  ];
  return (
    <div>
      <div className={styles.header}>7월 19일 소비</div>
      {Dummy.map((e) => (
        <PayList
          key={Math.random()}
          name={e.name}
          amount={e.amount}
          date={e.date}
        ></PayList>
      ))}
    </div>
  );
};

export default DailyCalcul;
