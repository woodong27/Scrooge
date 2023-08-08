import { useState } from "react";
import styles from "./ReportWeek.module.css";

const ReportMonth = () => {
  const currentDate = new Date();
  const [currentMonth, setCurrentMonth] = useState(currentDate.getMonth() + 1);
  const [currentYear, setCurrentYear] = useState(currentDate.getFullYear());

  const handleLastMonth = () => {
    let newMonth = currentMonth - 1;
    let newYear = currentYear;
    if (newMonth === 0) {
      newMonth = 12;
      newYear--;
    }
    setCurrentMonth(newMonth);
    setCurrentYear(newYear);
  };

  const handleNextMonth = () => {
    let newMonth = currentMonth +1;
    let newYear = currentYear;
    if (newMonth === 13) {
      newMonth = 1;
      newYear++;
    }
    setCurrentMonth(newMonth);
    setCurrentYear(newYear);
  }

  return(
    <div className={styles.reportContainer}>
      {/* 날짜 */}
      <div className={styles.weekDays}>
        <button onClick={handleLastMonth}>
          <img
            src={`${process.env.PUBLIC_URL}/images/left.svg`}
            alt="이전"
          />
        </button>
        <h3>{`${currentYear}년 ${currentMonth}월`}</h3>
        <button onClick={handleNextMonth}>
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