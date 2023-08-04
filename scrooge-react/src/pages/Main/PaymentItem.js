import { useState } from "react";
import Modal from "../../components/UI/Modal";
import styles from "./PaymentItem.module.css";

// const serverTimezoneOffset = 540;

const PaymentItem = (props) => {
  // const serverTime = new Date(props.paidAt);
  // const localTime = new Date(serverTime.getTime() + serverTimezoneOffset * 60 * 1000);
  const [modal, setModal] = useState(false);

  const handleOpenModal = () => {
    setModal(true);
  };

  const handleCloseModal = () => {
    setModal(false);
  };

  return (
    <div className={styles.box}>
      <p className={styles.usedAt}>{props.usedAt}</p>
      <p className={styles.amount}>{props.amount}</p>
      <p className={styles.paidAt}>
      {props.paidAt.split("T")[1].split(".")[0]}
      </p>
      <button className={styles.btn} onClick={handleOpenModal}>
        확인
      </button>
      {modal && (
        <Modal
          onEdit={props.onEdit}
          onCloseModal={handleCloseModal}
          usedAt={props.usedAt}
          amount={props.amount}
          paidAt={props.paidAt}
          cardName={props.cardName}
          id={props.id}
        />
      )}
    </div>
  );
};

export default PaymentItem;
