import React, { useState, useEffect } from "react";
import "./ProgressBar.css"; // CSS 파일 불러오기

const ProgressBar = () => {
  const [progress, setProgress] = useState(0);

  useEffect(() => {
    setProgress((prevProgress) => {
      // 100% 이상이 되지 않도록 처리
      return 70;
    });
  }, []);

  return (
    <div className="progress-bar-container">
      <div className="progress-bar-bg">
        <div className="progress-bar" style={{ width: `${70}%` }}></div>
      </div>
      <p className="progress-text">이번 달 남은 금액: {progress * 100}원</p>
    </div>
  );
};

export default ProgressBar;
