import React, { useState, useEffect } from "react";
import styles from "./ProgressBar.module.css";

const ProgressBar = (props) => {
  const [progress, setProgress] = useState(50);

  useEffect(() => {
    setProgress(props.ally);
  }, []);

  return (
    <div className={styles["progress-bar-container"]}>
      <p
        className={styles["progress-text"]}
      >{`우리팀(${props.ally}%) 👊 적팀(${props.enemy}%)`}</p>
      <div className={styles["progress-bar-bg"]}>
        <div
          className={styles["progress-bar"]}
          style={{
            width: `${props.ally}%`,
          }}
        ></div>
      </div>
    </div>
  );
};

export default ProgressBar;
