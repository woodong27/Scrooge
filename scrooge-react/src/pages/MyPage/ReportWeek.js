import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { BarChart, Bar, XAxis } from 'recharts';
import styles from "./ReportWeek.module.css";

const ReportWeek = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const [data, setData] = useState([]);
  const [startPoint, setStartPoint] = useState(null);  // 시작일은 월요일 
  const [weeklyDates, setWeeklyDates] = useState([]); // 주간 날짜들 저장 

  // api 받아오기 위해 데이터 변형 
  const formatDate = (date) => {
    const year = date.getFullYear();
    const month = (date.getMonth() +1).toString().padStart(2,"0");
    const day = date.getDate().toString().padStart(2,"0");
    return `${year}-${month}-${day}`;
  };

  const getStandardDate = () => {
    const currentDate = new Date();         // 현재 날짜
    const dayOfWeek = currentDate.getDay(); // 현재 요일
    const daysToAdd = dayOfWeek === 0 ? -6 : 1 - dayOfWeek // 시작요일: 월요일 
    const startDate = new Date(currentDate);
    startDate.setDate(currentDate.getDate() + daysToAdd);

    const endDate = new Date(startDate);
    endDate.setDate(startDate.getDate() + 6);

    return { startDate, endDate };
  }


  const getWeeklyDatesArray = () => {
    const {startDate, endDate } = getStandardDate();
    const weeklyDatesArray = [];

    for (let date = new Date(startDate); date <= endDate ; date.setDate(date.getDate() + 1)) {
      weeklyDatesArray.push(new Date(date));
    }

    return weeklyDatesArray;
  }

  const getWeeklyData = async () => {
    const { startDate, endDate } = getStandardDate();
    const weeklyDatesArray = getWeeklyDatesArray(startDate);

    const postData = {
      method: "GET",
      headers: {
        Authorization: globalToken,
      },
    };

    try{
      const weeklyDataPromises = weeklyDatesArray.map(async (date) => {
        const formattedDate = formatDate(date);
        const resp = await fetch(`https://day6scrooge.duckdns.org/api/payment-history/date-total/${formattedDate}`, postData)
        const data = await resp.json();
        return {date: formattedDate, amount: data};
      });

      const weeklyDataResults = await Promise.all(weeklyDataPromises)
      setData(weeklyDataResults.map((result) => result.amount));
      setStartPoint(startDate);
      setWeeklyDates(weeklyDataResults.map((result)=> result.date));

      console.log("일주일 데이터", weeklyDataResults.map((result) => result.amount));
      console.log("일주일 날짜", weeklyDataResults.map((result) => result.date));

    } catch (error) {
      console.log(error);
    }
  };



  useEffect(() => {
    getWeeklyData();
  },[]) 


  return (
    <div>
      {/* 날짜 이동 */}
      <div className={styles.weekDays}>
        <button onClick={handleLastWeek}>
          <img
            src={`${process.env.PUBLIC_URL}/images/left.svg`}
            alt="이전" />
        </button>
        <h3>M월 D일 ~ M월 D일</h3>
        <button>
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
          <div><b>얼마얼마</b> 원</div>
        </div>
        <div className={styles.weekMax}>
          <div>최대절약금액</div>
          <div><b>얼마얼마~</b> 원</div>
        </div>
      </div>
  
      {/* 소비 그래프 */}
      <div className={styles.chartContainer}>
        <BarChart width={350} height={200} data={data}>
          <Bar dataKey="amount" fill="#A2D660" />
          <XAxis dataKey="date" interval={0} tick={{ angle: -45 }} />
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
