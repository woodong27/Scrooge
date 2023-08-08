import { BarChart, Bar, XAxis} from 'recharts';
import styles from "./ReportChart.module.css";

// 금액 또한 해당 날짜에 정산된 금액으로 불러와져야 함 
const ReportChart = ({ currentDate }) => {
  const data = [];
  const startDate = new Date(currentDate);
  const dayOfWeek = startDate.getDay();
  startDate.setDate(startDate.getDate() - dayOfWeek + (dayOfWeek === 0? -6 : 1)) //추가
  for (let i = 0; i<7; i++) {
    const labelDate = new Date(startDate);
    labelDate.setDate(startDate.getDate() + i);
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
      {/* 소비 요약 */}
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
      {/* 소비 그래프 */}
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