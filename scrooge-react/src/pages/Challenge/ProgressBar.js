import React, { useState, useEffect } from "react";
import styles from "./ProgressBar.module.css";

const ProgressBar = (props) => {
  const [progress, setProgress] = useState({ ally: 50, enemy: 50 });

  useEffect(() => {
    const allCnt = props.ally + props.enemy;
    const allyCnt = parseInt((props.ally / allCnt) * 100);
    const enemyCnt = parseInt((props.enemy / allCnt) * 100);

    allCnt && setProgress({ ally: allyCnt, enemy: enemyCnt });
  }, [props]);

  return (
    <div className={styles["progress-bar-container"]}>
      <p
        className={styles["progress-text"]}
      >{`우리팀(${progress.ally}%) 👊 적팀(${progress.enemy}%)`}</p>
      <div className={styles["progress-bar-bg"]}>
        <div
          className={styles["progress-bar"]}
          style={{
            width: `${progress.ally}%`,
          }}
        ></div>
      </div>
    </div>
  );
};

export default ProgressBar;
