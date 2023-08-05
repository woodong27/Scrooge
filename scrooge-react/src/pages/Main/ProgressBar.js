import React, { useState, useEffect } from "react";
import styles from "./ProgressBar.module.css";

const ProgressBar = ({ goal, consum }) => {
  const [progress, setProgress] = useState(0);

  useEffect(() => {
    console.log(goal, consum);
    setProgress(consum / goal);
  }, [consum]);

  return (
    <div className={styles["progress-bar-container"]}>
      <div className={styles["progress-bar-bg"]}>
        {progress < 1 ? (
          <div
            className={styles["progress-bar"]}
            style={{
              width: `${progress * 100}%`,
            }}
          />
        ) : (
          <div className={styles["progress-bar-red"]} />
        )}
      </div>
      <p className={styles["progress-text"]}>
        이번 달 남은 금액: {goal - consum}원
      </p>
    </div>
  );
};

export default ProgressBar;
