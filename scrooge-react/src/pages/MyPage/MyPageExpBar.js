import { useState, useEffect } from "react";
import styles from "./MyPageExpBar.module.css";

const MyPageExpBar = ({ exp, maxExp }) => {
  const [progress, setProgress] = useState(0);

  useEffect(() => {
    setProgress(exp / maxExp);
  },[exp, maxExp]);

  return (
    <div className={styles.expBarContainer}>
      <div className={styles.expBarBg}>
        <div
          className={styles.expBar}
          style = {{
            width: `${progress * 100}%`
          }}
        />
      </div>
      <p className={styles.expText}>
        경험치 : {exp} / {maxExp}
      </p>
    </div>
  );
};

export default MyPageExpBar;
