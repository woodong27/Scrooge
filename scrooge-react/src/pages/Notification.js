import React, { useState } from "react";

import styles from "./Notification.module.css";

const Notification = (props) => {
  const [selectedTime, setSelectedTime] = useState("");
  const [savedTime, setSavedTime] = useState("");

  const handleTimeChange = (event) => {
    setSelectedTime(event.target.value);
  };

  const handleSaveTime = () => {
    if (window.AndroidInterface) {
      window.AndroidInterface.sendTimeToApp(selectedTime);
      setSavedTime(selectedTime);
    }
  };

  return (
    <div className={styles.notificationContainer}>
      <h2>알림 설정</h2>
      <label>
        시간 선택:
        <input type="time" value={selectedTime} onChange={handleTimeChange} />
      </label>
      <button onClick={handleSaveTime}>저장</button>
      <p>저장된 시간: {savedTime}</p>
    </div>
  );
};

export default Notification;
