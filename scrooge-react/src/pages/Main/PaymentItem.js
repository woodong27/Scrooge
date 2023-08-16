import React, { useState, useEffect } from "react";
import styles from "./PaymentItem.module.css";

const PaymentItem = (props) => {
  const [amount, setAmount] = useState();
  useEffect(() => {
    setAmount(props.amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
  }, [props]);
  return (
    <div className={styles.box}>
      <div className={styles.upLine}>
        <p className={styles.usedAt}>{props.usedAt}</p>
        <p className={styles.paidAt}>
          {props.paidAt.split("T")[1].split(".")[0]}
        </p>
      </div>
      <div className={styles.downLine}>
        <p className={styles.amount}>{amount}</p>
        <p className={styles.cardName}>{props.cardName}</p>
      </div>
    </div>
  );
};

export default PaymentItem;
