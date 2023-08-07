import { BarChart, Bar, XAxis} from 'recharts';
import styles from "./ReportChart.module.css";

// name을 이번주 해당 날짜로 불러와야 함
// 금액 또한 해당 날짜에 정산된 금액으로 불러와져야 함 
const data = [
  {
    name: '월',
    amount: 24000,
  },
  {
    name: '화',
    amount: 137000,
  },
  {
    name: '수',
    amount: 0,
  },
  {
    name: '목',
    amount: 5700,
  },
  {
    name: '금',
    amount: 24000,
  },
  {
    name: '토',
    amount: 3600,
  },
  {
    name: '일',
    amount: 15500,
  },
];


export default function ReportChart() {
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
