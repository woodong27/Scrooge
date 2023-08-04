import { BarChart, Bar, XAxis} from 'recharts';
import styles from "./ReportChart.module.css";

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
            <span>{Math.floor(avgAmt).toLocaleString()}</span>
            <span> 원</span>
          </div>
        </div>
        <div className={styles.weekMax}>
          <div>최대절약금액</div>
          <div>
            <span>{minAmt.toLocaleString()}</span>
            <span> 원</span>
          </div>
        </div>
      </div>

      <BarChart width={350} height={200} data={data}>
        <Bar dataKey="amount" fill="#8884d8" />
        <XAxis dataKey="name" />
      </BarChart>
    </div>
  );
}
