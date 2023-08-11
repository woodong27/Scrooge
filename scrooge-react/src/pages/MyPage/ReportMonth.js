import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import styles from "./ReportMonth.module.css";


const titles = ['월','화','수','목','금','토','일'];

const ReportMonth = () => {
  const globalToken = useSelector((state) => state.globalToken);

  const currentDate = new Date();
  const year = currentDate.getFullYear();
  const month = currentDate.getMonth() +1;

  const formattedMonth = `${year}-${String(month).padStart(2,'0')}`;
  const [monthlyData, setMonthlyData] = useState([]);

  useEffect(() => {
    const getMonthlyData = async () => {
      const postData = {
        method: "GET",
        headers: {
          Authorization: globalToken,
        },
      };

      try{
        const resp = await fetch(`https://day6scrooge.duckdns.org/api/payment-history/month/${formattedMonth}`,postData)
        const data = await resp.json();

        const processedData = [];
        data.forEach(item => {
          const date = item.paidAt.split('T')[0];
          const testData = processedData.find(dataItem => dataItem.date === date);

          if (testData) {
            testData.amount += item.amount;
          } else {
            processedData.push({date, amount: item.amount});
          }
        });

        setMonthlyData(processedData);
        console.log("데이터..뭡네까", processedData)
      } catch (error) {
        console.log(error)
      }
    };
    getMonthlyData();
  },[formattedMonth]);



  const getMonthInfo = (year, month) => {
    const startDay = new Date(year, month -1, 1).getDay();
    const endDate = new Date(year, month, 0).getDate();

    const adjStartDay = startDay === 0 ? 6 : startDay -1;
    const info = {startDay: adjStartDay, endDate}
    console.log(info)
    // return {startDay: adjStartDay, endDate};
    return info
  };

  const getCalendar = (year, month) => {
    const { endDate, startDay } = getMonthInfo(year, month);
    const weekNumber = Math.ceil((startDay + endDate) / 7);
    const calendar = [];

    let nowDate = 0;
    let nowDay = 0;

    for (let i = 0; i <weekNumber ; i++) {
      const nowWeek = [];
      for (let j = 0; j < 7; j++) {
        if (startDay <= nowDay && nowDate < endDate) {
          nowDate++;
          nowWeek.push(nowDate);
        } else {
          nowWeek.push(0);
        };
        nowDay++;
      }
      calendar.push(nowWeek);
    };
    console.log("달력~",calendar)
    return calendar;
  };
    



  const datas = getCalendar(year, month);
  const components = [];

  datas.forEach((week, weekIndex) => {
    const weeks = week.map((date, dayIndex) => {
      const foundData = monthlyData.find(dataItem => {
        const dataDay = new Date(dataItem.date).getDate();
        return dataDay === date;
      });
      // console.log("나와라고우",foundData)

      const amountToShow = foundData ? foundData.amount : '';

      return {
        key: date !== 0 ? date: dayIndex,
        render: (
          date !== 0? 
          <div className={styles.dayBox}>
            <p>{date}</p>
            <p className={styles.calAmt}>{amountToShow.toLocaleString()}</p>
          </div> 
          :
          <div>🎀</div>
        ),
      };
    }) ;

    const weekData = {
      id: `w_${month}${weekIndex}`,
      columns: weeks,
    };
    components.push(weekData);
  });
  // console.log("이게머지", components);


  return(
    <div>
      <h2>{year}년 {month}월</h2>
      <div className={styles.tableContainer}>
        <table className={styles.tableContent}>
          <thead>
            <tr>
              {titles.map((title, index) => (
                <th key={index}>{title}</th>
              ))}
            </tr>
          </thead>
          <tbody>
            {components.map((weekData, weekIndex) => (
              <tr key={weekData.id}>
                {weekData.columns.map(column => (
                  <td key={column.key}>{column.render}</td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>

      </div>
    </div>
  )
};

export default ReportMonth;
