import React, { useState, useEffect } from "react";
import styles from "./ProgressBar.module.css";

const ProgressBar = ({ goal, consum }) => {
  const [progress, setProgress] = useState(0);

  useEffect(() => {
    setProgress(goal / consum);
  }, []);

  return (
    <div className={styles["progress-bar-container"]}>
      <div className={styles["progress-bar-bg"]}>
        <div
          className={styles["progress-bar"]}
          style={{
            width: `${(consum / goal) * 100}%`,
          }}
        >
          <img
            src={`${process.env.PUBLIC_URL}/images/happy-icon.png`}
            alt="^-^"
          />
        </div>
      </div>
      <p className={styles["progress-text"]}>
        이번 달 남은 금액: {goal - consum}원
      </p>
    </div>
  );
};

export default ProgressBar;
