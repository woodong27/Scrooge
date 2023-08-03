import { useState } from "react";
import Modal from "../../components/UI/Modal";
import styles from "./PaymentItem.module.css";

const PayList = (props) => {
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
      <p className={styles.paidAt}>{props.paidAt.split("T")[1]}</p>
      <button className={styles.btn} onClick={handleOpenModal}>
        수정
      </button>
      {modal && (
        <Modal
          onCloseModal={handleCloseModal}
          name={props.name}
          amount={props.amount}
          date={props.date}
          cardName={props.cardName}
        />
      )}
    </div>
  );
};

export default PayList;
