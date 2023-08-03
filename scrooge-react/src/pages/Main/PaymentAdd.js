import React, { useContext, useRef, useState } from "react";

import styles from "./PaymentAdd.module.css";

const PaymentAdd = ({ onCreate }) => {
  const usedAtInput = useRef();
  const amountInput = useRef();
  // const paidAtInput = useRef();

  const [state, setState] = useState({
    usedAt: "",
    amount: "",
    // paidAt: "",
  });

  const handleChangeState = (e) => {
    setState({
      ...state,
      [e.target.name]: e.target.value,
    });
  };

  const getCurrentDate = () => {
    const now = new Date();
    const formattedDateTime = now.toISOString().slice(0, 19);
    return formattedDateTime;
  };

  const submitPaymentItem = () => {
    if (state.usedAt.length < 1) {
      usedAtInput.current.focus();
      return;
    }
    if (state.amount.length < 2) {
      amountInput.current.focus();
      return;
    }
    // if (state.paidAt.length < 5) {
    //   paidAtInput.current.focus();
    //   return;
    // }

    const obj = {
      usedAt: state.usedAt,
      amount: state.amount,
    };
    const postData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImhhcmluMTRAbmF2ZXIuY29tIiwibWVtYmVySWQiOjEsImlhdCI6MTY5MTA0NDcyOCwiZXhwIjoxNjkxNjQ5NTI4fQ.JusnJ0lDLqH5nBSHCfFC40iKUbuwAHqCCxtqqrRC3W0",
      },
      body: JSON.stringify(obj),
    };
    fetch("http://localhost:8080/payment-history/1", postData)
      .then((res) => res.text())
      .then(console.log);
    const currentDateTime = getCurrentDate();
    onCreate(state.usedAt, state.amount, currentDateTime);
    setState({
      usedAt: "",
      amount: "",
      paidAt: "",
    });
  };

  return (
    <div className={styles.box}>
      <input
        className={styles.usedAt}
        ref={usedAtInput}
        name="usedAt"
        value={state.usedAt}
        placeholder="사용처"
        onChange={handleChangeState}
      />
      <input
        className={styles.amount}
        ref={amountInput}
        name="amount"
        value={state.amount}
        placeholder="결제 금액"
        onChange={handleChangeState}
      />
      <button className={styles.btn} onClick={submitPaymentItem}>
        추가
      </button>
    </div>
  );
};

export default PaymentAdd;
