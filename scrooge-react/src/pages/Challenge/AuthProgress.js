import styles from "./AuthProgress.module.css";

const AuthProgress = (props) => {
  return (
    <div className={styles["progress-bar-container"]}>
      <div className={styles.txt}>현재 달성률: {props.per}%</div>
      <div className={styles["progress-bar-bg"]}>
        <div
          className={styles["progress-bar"]}
          style={{
            width: `${props.per}%`,
          }}
        ></div>
      </div>
    </div>
  );
};

export default AuthProgress;
