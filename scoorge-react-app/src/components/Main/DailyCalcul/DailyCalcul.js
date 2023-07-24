import PayList from "./PayList";
import "./DailyCalcul.css";

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
  ];
  return (
    <div>
      <div className="header">7월 19일 소비</div>
      {Dummy.map((e) => (
        <PayList
          key={e.date}
          name={e.name}
          amount={e.amount}
          date={e.date}
        ></PayList>
      ))}
    </div>
  );
};

export default DailyCalcul;
