import { useState } from "react";
import Modal from "../../UI/Modal";
import styles from "./PayList.module.css";

const PayList = (props) => {
  const [modalOpen, setModalOpen] = useState(false);

  const handleOpenModal = () => {
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
  };

  return (
    <div className={styles.box}>
      <p className={styles.name}>{props.name}</p>
      <p className={styles.amount}>{props.amount}</p>
      <p className={styles.date}>{props.date}</p>
      <button className={styles.btn} onClick={handleOpenModal}>
        상세 보기
      </button>
      {modalOpen && (
        <Modal
          onCloseModal={handleCloseModal}
          name={props.name}
          amount={props.amount}
          date={props.date}
        />
      )}
    </div>
  );
};

export default PayList;
