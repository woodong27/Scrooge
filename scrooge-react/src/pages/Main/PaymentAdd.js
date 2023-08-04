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
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImhhcHB5QGdtYWlsLmNvbSIsIm1lbWJlcklkIjoyLCJpYXQiOjE2OTEwNTUzOTIsImV4cCI6MTY5MTY2MDE5Mn0.GSDDPI26jaeE7zZzhHGIlImyCWcZi3GbE6K8rIZhi30",
      },
      body: JSON.stringify(obj),
    };
    fetch("http://day6scrooge.duckdns.org:8081/payment-history/1", postData)
      .then((res) => res.text())
      .then(console.log);
    onCreate(state.usedAt, state.amount, new Date());
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
