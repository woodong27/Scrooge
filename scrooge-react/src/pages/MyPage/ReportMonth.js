import { useState } from "react";
import styles from "./ReportWeek.module.css";

const ReportMonth = () => {
  const currentDate = new Date();
  const currentMonth = currentDate.getMonth() + 1;
  const currentYear = currentDate.getFullYear();
  const [selectedMonth, setSelectedMonth] = useState(currentMonth);
  const [selectedYear, setSelectedYear] = useState(currentYear);

  const handleMonth = (change) => {
    let newMonth = selectedMonth + change;
    let newYear = selectedYear;

    if (newMonth < 1) {
      newMonth = 12;
      newYear--;
    } else if (newMonth > 12) {
      newMonth = 1;
      newYear++;
    }

    setSelectedMonth(newMonth);
    setSelectedYear(newYear);
  }

  return(
    <div className={styles.reportContainer}>
      {/* 날짜 */}
      <div className={styles.weekDays}>
        <button onClick={() => handleMonth(-1)}>
          <img
            src={`${process.env.PUBLIC_URL}/images/left.svg`}
            alt="이전"
          />
        </button>
        <h3>{`${selectedYear}년 ${selectedMonth}월`}</h3>
        <button onClick={() => handleMonth(1)}>
          <img
            src={`${process.env.PUBLIC_URL}/images/right.svg`}
            alt="다음"
          />          
        </button>
      </div>
    </div>
  )
};

export default ReportMonth;
