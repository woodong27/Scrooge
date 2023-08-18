import styles from "./PlusBtn.module.css";

const plusBtn = () => {
  return (
    <button className={styles.btnContainer}>
      <div>+</div>
    </button>
  );
};

export default plusBtn;
