import styles from "./PaymentAdd.module.css";

const PaymentAdd = () => {
  const postPaymentItem = () => {};

  return (
    <div className={styles.box}>
      <p className={styles.usedAt}>사용처</p>
      <p className={styles.amount}>금액</p>
      <p className={styles.paidAt}>결제시간</p>
      <button className={styles.btn} onClick={postPaymentItem}>
        추가
      </button>
    </div>
  );
};

export default PaymentAdd;
