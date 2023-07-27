import React, { useState, useEffect } from "react";
import styles from "./ProgressBar.module.css";

const ProgressBar = () => {
  const [progress, setProgress] = useState(0);

  useEffect(() => {
    setProgress(60);
  }, []);

  return (
    <div className={styles["progress-bar-container"]}>
      <div className={styles["progress-bar-bg"]}>
        <div
          className={styles["progress-bar"]}
          style={{ width: `${progress}%` }}
        >
          <img
            src={`${process.env.PUBLIC_URL}/images/happy-icon.png`}
            alt="^-^"
          />
        </div>
      </div>
      <p className={styles["progress-text"]}>
        이번 달 남은 금액: {progress * 100}원
      </p>
    </div>
  );
};

export default ProgressBar;
