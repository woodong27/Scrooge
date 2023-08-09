import { useState, useEffect } from "react";
import styles from "./Toast.module.css";

const Toast = ({ setToast, text }) => {
  const [show, setShow] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setShow(false);
      // 트랜지션 완료 후에 토스트 창을 닫음
      setTimeout(() => {
        setToast(false);
      }, 300);
    }, 1500);
    return () => {
      clearTimeout(timer);
    };
  }, [setToast]);

  return (
    <div className={`${styles.toast} ${show ? styles.show : styles.hide}`}>
      <div className={styles.primary}>{text}</div>
    </div>
  );
};

export default Toast;
