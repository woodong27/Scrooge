import styles from "./HeaderToggle.module.css";

const HeaderToggle = (props) => {
  return (
    <div className={styles["challenge-toggle"]}>
      <div
        className={`${styles.tab} ${props.isMine ? "" : styles.active_tab}`}
        onClick={props.allHandler}>
        전체 글
      </div>
      <div
        className={`${styles.tab} ${props.isMine ? styles.active_tab : ""}`}
        onClick={props.myHandler}>
        나의 글
      </div>
    </div>
  );
};

export default HeaderToggle;
