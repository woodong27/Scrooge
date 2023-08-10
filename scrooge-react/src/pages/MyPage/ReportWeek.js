import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { BarChart, Bar, XAxis, LabelList,Rectangle } from 'recharts';
import styles from "./ReportWeek.module.css";

const ReportWeek = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const [data, setData] = useState([]);
  // const [startPoint, setStartPoint] = useState()
  const [weeklyDates, setWeeklyDates] = useState([]); // 주간 날짜들 저장 
  const [weeklyDataResults, setWeeklyDataResults] = useState([]);


  // 현재 요일 기준 이번 주 시작일(월요일), 종료일(일요일)
  const getStandardDate = () => {
    const currentDate = new Date();         
    const dayOfWeek = currentDate.getDay();
    const daysToAdd = dayOfWeek === 0 ? -6 : 1 - dayOfWeek  

    const startDate = new Date(currentDate);
    startDate.setDate(currentDate.getDate() + daysToAdd);
    const endDate = new Date(startDate);
    endDate.setDate(startDate.getDate() + 6);

    return { startDate, endDate };
  }

  // 7일 날짜 담는 배열 
  const getWeeklyDatesArray = () => {
    const {startDate, endDate } = getStandardDate();
    const weeklyDatesArray = [];

    for (let date = new Date(startDate); date <= endDate ; date.setDate(date.getDate() + 1)) {
      weeklyDatesArray.push(new Date(date));
    }

    return weeklyDatesArray;
  }

  // api 받아오기 위한 날짜 형식 변형 
  const formatDate = (date, format) => {
    const year = date.getFullYear();
    const month = (date.getMonth() +1).toString().padStart(2,"0");
    const day = date.getDate().toString().padStart(2,"0");

    const labelMonth = (date.getMonth()+1).toString().padStart(1,"0");
    const labelDay = date.getDate().toString().padStart(1,"0");

    if (format === "label") {
      return `${labelMonth}월 ${labelDay}일`
    } else if (format === "graph") {
      return `${labelMonth}/${labelDay}`
    }
    return `${year}-${month}-${day}`;
  };


  // 7일 날짜 사용 금액 데이터 담는 배열
  const getWeeklyData = async () => {
    const { startDate } = getStandardDate();
    const weeklyDatesArray = getWeeklyDatesArray(startDate);

    const postData = {
      method: "GET",
      headers: {
        Authorization: globalToken,
      },
    };

    try{
      const weeklyDataPromises = weeklyDatesArray.map(async (date) => {
        const chartDate = formatDate(date,"graph")
        const formattedDate = formatDate(date);
        const resp = await fetch(`https://day6scrooge.duckdns.org/api/payment-history/date-total/${formattedDate}`, postData)
        const data = await resp.json();
        return {date: chartDate, amount: data};
      });

      const weeklyDataResults = await Promise.all(weeklyDataPromises);
      setWeeklyDataResults(weeklyDataResults);

      // setData(weeklyDataResults.map((result) => result.amount));
      // setWeeklyDates(weeklyDataResults.map((result)=> result.date));
      // setStartPoint(startDate);

    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getWeeklyData();
  },[]) 

  const [startPoint, setStartPoint] = useState(getStandardDate().startDate); 
  const [endPoint, setEndPoint] = useState(getStandardDate().endDate);

  const resultsSummary = () => {
    const amounts = weeklyDataResults.map((result) => result.amount);

    const avg = amounts.reduce((sum, amount) => sum + amount, 0);
    const max = Math.max(...amounts);

    return {avg, max};
  }
  
  return (
    <div>
      {/* 날짜 이동 */}
      <div className={styles.weekDays}>
        <button>
          <img
            src={`${process.env.PUBLIC_URL}/images/left.svg`}
            alt="이전" />
        </button>
        <h3>{formatDate(startPoint,"label")} ~ {formatDate(endPoint,"label")}</h3>
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
          <div>평균소비금액💸</div>
          <div><b>{resultsSummary().avg.toLocaleString()}</b> 원</div>
        </div>
        <div className={styles.weekMax}>
          <div>이번주과소비😮</div>
          <div><b>{resultsSummary().max.toLocaleString()}</b> 원</div>
        </div>
      </div>
  
      {/* 소비 그래프 */}
      <div className={styles.chartContainer}>
        <BarChart width={350} height={200} data={weeklyDataResults} className={styles.chartBox}>
          <Bar dataKey="amount" fill="#A2D660" barSize={35} shape={<Rectangle radius={[15, 15, 0, 0]} />} >
            <LabelList dataKey ="amount" position="top" formatter = {(value) => `${value.toLocaleString()}`} />
          </Bar>
          <XAxis dataKey="date"  />
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