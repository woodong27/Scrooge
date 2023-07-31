import React, { useState, useEffect } from "react";
import styles from "./ProgressBar.module.css";

const ProgressBar = (props) => {
  const [progress, setProgress] = useState(0);

  useEffect(() => {
    setProgress(props.weeklyGoal / props.weeklyConsum);
  }, []);

  return (
    <div className={styles["progress-bar-container"]}>
      <div>{console.log(props.weeklyGoal, props.weeklyConsum)}</div>
      <div className={styles["progress-bar-bg"]}>
        <div
          className={styles["progress-bar"]}
          style={{
            width: `${(props.weeklyConsum / props.weeklyGoal) * 100}%`,
          }}
        >
          <img
            src={`${process.env.PUBLIC_URL}/images/happy-icon.png`}
            alt="^-^"
          />
        </div>
      </div>
      <p className={styles["progress-text"]}>
        이번 달 남은 금액: {props.weeklyGoal - props.weeklyConsum}원
      </p>
    </div>
  );
};

export default ProgressBar;
