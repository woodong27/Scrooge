import styles from "./SettingModal.module.css";

const SettingModal = ({message, onConfirm, onCancel}) => {
  return(
    <div className={styles.modalContainer}>
      <div className={styles.modal}>
        <p>{message}</p>
        <div className={styles.modalBtn}>
          <button onClick={onConfirm}>확인</button>
          <button onClick={onCancel}>취소</button>
        </div>
      </div>
    </div>
  );
};

export default SettingModal;