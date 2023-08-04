import React, { useState } from "react";
import styles from "./ReportWeek.module.css";
import ReportChart from "./ReportChart"

// 날짜 계산
const getWeekRange = (date) => {
  const startOfWeek = new Date(date);
  startOfWeek.setDate(date.getDate() - date.getDay() +1); //월요일  
  const endOfWeek = new Date(startOfWeek);
  endOfWeek.setDate(startOfWeek.getDate() + 6); // 일요일

  const startDateStr = startOfWeek.toLocaleDateString("ko-KR").slice(2)
  const endDateStr = endOfWeek.toLocaleDateString("ko-KR").slice(2); // MM.dd 형식으로 출력

  return `${startDateStr} ~ ${endDateStr}`;
};



const ReportWeek = () => {
  // 날짜
  const [currentDate, setCurrentDate] = useState(new Date());

  const handleLastsWeek = () => {
    const lastWeek = new Date(currentDate);
    lastWeek.setDate(currentDate.getDate() -7);
    setCurrentDate(lastWeek);
  };
  const handleNextWeek = () => {
    const nextWeek = new Date(currentDate);
    nextWeek.setDate(currentDate.getDate() + 7);
    setCurrentDate(nextWeek);
  };


  return (
    <div className={styles.reportContainer}>
      {/* 소비 날짜 */}
      <div className={styles.weekDays}>
        <button onClick={handleLastsWeek}>지난 주</button>
        <h3>{getWeekRange(currentDate)}</h3>
        <button onClick={handleNextWeek}>다음 주</button>
      </div>

      {/* 소비 차트 */}
      <div className={styles.chart}>
        <ReportChart />
      </div>
    </div>


  );
};

export default ReportWeek;