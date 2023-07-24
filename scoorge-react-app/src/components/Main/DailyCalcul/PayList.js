import "./PayList.css";

const PayList = (props) => {
  return (
    <div className="box">
      <p className="name">{props.name}</p>
      <p className="name">{props.amount}</p>
      <p className="name">{props.date}</p>
    </div>
  );
};

export default PayList;
