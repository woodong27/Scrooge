import { useState } from "react";
import Modal from "../../UI/Modal";
import styles from "./PayList.module.css";

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
      <p className={styles.name}>{props.name}</p>
      <p className={styles.amount}>{props.amount}</p>
      <p className={styles.date}>{props.date}</p>
      <button className={styles.btn} onClick={handleOpenModal}>
        상세 보기
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
