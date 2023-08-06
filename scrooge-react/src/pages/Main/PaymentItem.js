import styles from "./PaymentItem.module.css";

const PaymentItem = (props) => {
  return (
    <div className={styles.box}>
      <p className={styles.usedAt}>{props.usedAt}</p>
      <p className={styles.amount}>{props.amount}</p>
      <p className={styles.paidAt}>
        {props.paidAt.split("T")[1].split(".")[0]}
      </p>
      <p className={styles.cardName}>{props.cardName}</p>
    </div>
  );
};

export default PaymentItem;
