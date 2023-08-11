import React, { useRef, useState } from "react";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";

import styles from "./PaymentAdd.module.css";
import { handleTokenError } from "../../utils/commonJwtError";

const PaymentAdd = ({ onCreate, date }) => {

  const dispatch = useDispatch();

  const globalToken = useSelector((state) => state.globalToken);

  const usedAtInput = useRef();
  const amountInput = useRef();
  const cardNameInput = useRef();

  const [state, setState] = useState({
    usedAt: "",
    amount: "",
    cardName: "",
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
    if (state.cardName.length < 3) {
      cardNameInput.current.focus();
      return;
    }

    const input = `2023-${date[0].toString().padStart(2, "0")}-${date[1]
      .toString()
      .padStart(2, "0")}`;
    const inputDate = new Date(input);
    const currentDateTime = new Date();
    const currentHours = currentDateTime.getHours();
    const currentMinutes = currentDateTime.getMinutes();
    inputDate.setDate(inputDate.getDate());
    inputDate.setHours(currentHours);
    inputDate.setMinutes(currentMinutes);
    const year = inputDate.getFullYear();
    const month = String(inputDate.getMonth() + 1).padStart(2, "0");
    const day = String(inputDate.getDate()).padStart(2, "0");
    const hours = String(inputDate.getHours()).padStart(2, "0");
    const minutes = String(inputDate.getMinutes()).padStart(2, "0");
    const currentDate = `${year}-${month}-${day}T${hours}:${minutes}:00.000`;

    console.log(currentDate, "얍");
    const obj = {
      usedAt: state.usedAt,
      amount: state.amount,
      cardName: state.cardName,
      paidAt: currentDate,
    };
    const postData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
      body: JSON.stringify(obj),
      credentials: "include",
    };
    fetch(`http://localhost:8081/api/payment-history`, postData)
      .then((res) =>  {
        if(!res.ok) {
          const error = new Error("에러 발생");
          error.name = "ExpiredError";
          throw error;
        }
        return res.json()
      })
      .then((data) => {
        console.log(data);
        const koreaTime = new Date(
          new Date().getTime() + 9 * 60 * 60 * 1000
        ).toISOString();
        onCreate(
          data.id,
          state.usedAt,
          state.amount,
          koreaTime,
          state.cardName
        );
        setState({
          usedAt: "",
          amount: "",
          paidAt: "",
          cardName: "",
        });
      })
      .catch((error) => {
        if(error.name === "ExpiredError") {
          handleTokenError(error, globalToken, dispatch);
        }
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
        className={styles.cardName}
        ref={cardNameInput}
        name="cardName"
        value={state.cardName}
        placeholder="카드 이름"
        onChange={handleChangeState}
      />
      <button className={styles.btn} onClick={submitPaymentItem}>
        추가
      </button>
    </div>
  );
};

export default PaymentAdd;
