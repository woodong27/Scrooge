import React, { useState, useEffect } from "react";
import styles from "./PaymentItem.module.css";

const PaymentItem = (props) => {
  const [amount, setAmount] = useState();
  useEffect(() => {
    setAmount(props.amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
  }, [props]);
  return (
    <div className={styles.box}>
      <p className={styles.usedAt}>{props.usedAt}</p>
      <p className={styles.amount}>{amount}</p>
      <p className={styles.paidAt}>
        {props.paidAt.split("T")[1].split(".")[0]}
      </p>
      <p className={styles.cardName}>{props.cardName}</p>
    </div>
  );
};

export default PaymentItem;
