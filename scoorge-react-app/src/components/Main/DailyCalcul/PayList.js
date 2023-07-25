import "./PayList.css";

const PayList = (props) => {
  return (
    <div className="box">
      <p className="name">{props.name}</p>
      <p className="amount">{props.amount}</p>
      <p className="date">{props.date}</p>
    </div>
  );
};

export default PayList;
