import React, { useState } from "react";
import { BarChart, Bar, XAxis} from 'recharts';
import styles from "./ReportWeek.module.css";

const ReportWeek = () => {
  const [startDate, setStartDate] = useState(new Date());

  const handleLastWeek = () => {
    const lastWeek = new Date(startDate);
    lastWeek.setDate(startDate.getDate() - 7);
    setStartDate(lastWeek);
  };

  const handleNextWeek = () => {
    const nextWeek = new Date(startDate);
    nextWeek.setDate(startDate.getDate() + 7);
    setStartDate(nextWeek);
  }

  const data = [];
  const dayOfWeek = startDate.getDay(); 
  const startPoint = new Date(startDate);
  startPoint.setDate(startDate.getDate() - dayOfWeek + (dayOfWeek === 0? -6 : 1))
  const endPoint = new Date(startPoint) ;
  endPoint.setDate(startPoint.getDate() + 6);    
  
  for (let i = 0; i<7; i++) {
    const labelDate = new Date(startPoint);
    labelDate.setDate(startPoint.getDate() + i);
    const month = (labelDate.getMonth() + 1).toString().padStart(1, "0");
    const day = labelDate.getDate().toString().padStart(1,"0");
    data.push({
      name: `${month}/${day}`,
      amount: 5000,
    });
  }

  const avgAmt = data.reduce((acc, cur) => acc + cur.amount, 0) / data.length;
  const minAmt = data.reduce((min, cur) => (cur.amount < min ? cur.amount : min ), data[0].amount);
  
  return (
    <div>
      {/* 날짜 이동 */}
      <div className={styles.weekDays}>
        <button onClick={handleLastWeek}>
          <img
            src={`${process.env.PUBLIC_URL}/images/left.svg`}
            alt="이전"/>
        </button>
        <h3>{`${startPoint.getMonth() + 1}월 ${startPoint.getDate()}일 ~ ${endPoint.getMonth() + 1}월 ${endPoint.getDate()}일`}</h3>
        <button onClick={handleNextWeek}>
          <img
              src={`${process.env.PUBLIC_URL}/images/right.svg`}
              alt="다음"
          />          
        </button>
      </div>

      {/* 소비 요약 */}
      <div className={styles.reportContent}>
        <div className={styles.weekAvg}>
          <div>평균소비금액</div>
          <div><b>{Math.floor(avgAmt).toLocaleString()}</b> 원</div>
        </div>
        <div className={styles.weekMax}>
          <div>최대절약금액</div>
          <div><b>{minAmt.toLocaleString()}</b> 원</div>
        </div>
      </div>

      {/* 소비 그래프 */}
      <div className={styles.chartContainer}>
        <BarChart width={350} height={200} data={data}>
          <Bar dataKey="amount" fill="#A2D660" />
          <XAxis dataKey="name" />
        </BarChart>
      </div>

      {/* 카테고리 */}
      <div className={styles.cardContainer}>
        <div>카테고리 카드1</div>
        <div>카테고리 카드2</div>
      </div>      

    </div>
  );
}


export default ReportWeek;