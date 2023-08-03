import React, { useContext, useRef, useState } from "react";

import styles from "./PaymentAdd.module.css";

const PaymentAdd = () => {
  const usedAtInput = useRef();
  const amountInput = useRef();
  const paidAtInput = useRef();

  const [state, setState] = useState({
    usedAt: "",
    amount: 1,
    paidAt: "",
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
    if (state.paidAt.length < 5) {
      paidAtInput.current.focus();
      return;
    }

    const obj = {
      usedAt: state.usedAt,
      amount: state.amount,
      paidAt: state.paidAt,
    };
    const postData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(obj),
    };
    console.log(obj);
    fetch("http://localhost:8080/payment-history/1", postData)
      .then((res) => res.text())
      .then(console.log);

    alert("저장 성공");
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
      <input
        className={styles.paidAt}
        ref={paidAtInput}
        name="paidAt"
        value={state.paidAt}
        placeholder="결제 시간"
        onChange={handleChangeState}
      />
      <button className={styles.btn} onClick={submitPaymentItem}>
        추가
      </button>
    </div>
  );
};

export default PaymentAdd;
