import React, { Fragment, useState, useEffect } from "react";

import styles from "./Notification.module.css";

const Notification = ({ handleAlarmClose }) => {
  const [selectedTime, setSelectedTime] = useState("23:00:00");
  const [savedTime, setSavedTime] = useState(null);

  //로컬에 저장된 시간 불러오기
  useEffect(() => {
    const storedTime = localStorage.getItem("savedTime");
    if (storedTime) {
      setSavedTime(storedTime);
    }
    if (window.AndroidAlarmAllow) {
      window.AndroidAlarmAllow.sendAllowToApp();
    }
  }, []);

  const handleTimeChange = (event) => {
    setSelectedTime(event.target.value);
  };

  const handleSaveTime = () => {
    if (selectedTime && window.AndroidInterface ) {
      localStorage.setItem("savedTime", selectedTime);
      window.AndroidInterface.sendTimeToApp(selectedTime);
      setSavedTime(selectedTime);
      handleAlarmClose();
    }
  };

  const handleDeleteTime = () => {
    if (window.AndroidInterface) {
      window.AndroidInterface.cancelNotification();
      localStorage.removeItem("savedTime");
      setSavedTime("");
      handleAlarmClose();
    }
  };

  return (
    <Fragment>
      <div className={styles.modal_overlay} onClick={handleAlarmClose}></div>
      <div className={styles.modalContainer}>
        <div className={styles.modal}>
          <h2 className={styles.title}>알림 설정</h2>
          <div className={styles.saveTime}>
            <label>
              시간 선택:
              <input
                type="time"
                value={selectedTime}
                onChange={handleTimeChange}
              />
            </label>
            <button onClick={handleSaveTime}>저장</button>
          </div>
          {savedTime && (
            <div className={styles.saveTime}>
              <p>저장 시간: {savedTime}</p>
              <button onClick={handleDeleteTime}>삭제</button>
            </div>
          )}
        </div>
      </div>
    </Fragment>
  );
};

export default Notification;
