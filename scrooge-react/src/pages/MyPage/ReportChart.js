import { BarChart, Bar, XAxis} from 'recharts';

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
  return (
      <BarChart width={350} height={200} data={data}>
        <Bar dataKey="amount" fill="#8884d8" />
        <XAxis dataKey="name" />
      </BarChart>
  );
}
