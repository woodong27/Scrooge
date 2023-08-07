import { BarChart, Bar, XAxis} from 'recharts';
import styles from "./ReportChart.module.css";

// name을 이번주 해당 날짜로 불러와야 함
// 금액 또한 해당 날짜에 정산된 금액으로 불러와져야 함 

const ReportChart = ({ currentDate }) => {
  const data = [];
  const startDate = new Date(currentDate);
  for (let i = 0; i<7; i++) {
    const labelDate = new Date(startDate);
    labelDate.setDate(startDate.getDate() + i);
    const month = (labelDate.getMonth() + 1).toString().padStart(2, "0");
    const day = labelDate.getDate().toString().padStart(2,"0");
    // const weekday=['일','월','화','수','목','금','토'][labelDate.getDay()];
    data.push({
      // name: `${month}/${day}/${weekday}`,
      name: `${month}/${day}`,
      amount: 5000,
    });
  }



  const avgAmt = data.reduce((acc, cur) => acc + cur.amount, 0) / data.length;
  const minAmt = data.reduce((min, cur) => (cur.amount < min ? cur.amount : min ), data[0].amount);
  return (
    <div>
      <div className={styles.reportContent}>
        <div className={styles.weekAvg}>
          <div>평균소비금액</div>
          <div>
            <span><b>{Math.floor(avgAmt).toLocaleString()}</b></span>
            <span> 원</span>
          </div>
        </div>
        <div className={styles.weekMax}>
          <div>최대절약금액</div>
          <div>
            <span><b>{minAmt.toLocaleString()}</b></span>
            <span> 원</span>
          </div>
        </div>
      </div>

      <div className={styles.chartContainer}>
        <BarChart width={350} height={200} data={data}>
          <Bar dataKey="amount" fill="#A2D660" />
          <XAxis dataKey="name" />
        </BarChart>
      </div>
    </div>
  );
}


export default ReportChart;