import { useState, useEffect } from "react";
import styles from "./MyPageExpBar.module.css";

const MyPageExpBar = ({ exp, maxExp }) => {
  const [progress, setProgress] = useState(0);
  const [showImg, setShowImg] = useState(false);

  useEffect(() => {
    setProgress(exp / maxExp);
    setShowImg(exp === maxExp);
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
        {showImg ? (
          <img
            src={`${process.env.PUBLIC_URL}/images/afterExp.svg`}
            alt="레벨업 후"
            className={`${styles.afterExp} ${styles.expBarImage}`}
          />
          ) : (        
          <img
            src={`${process.env.PUBLIC_URL}/images/beforeExp.svg`}
            alt="레벨업 전"
            className={`${styles.beforeExp} ${styles.expBarImage}`}
          />
        )}

      </div>
      <p className={styles.expText}>
        경험치 : {exp} / {maxExp}
      </p>
    </div>
  );
};

export default MyPageExpBar;
