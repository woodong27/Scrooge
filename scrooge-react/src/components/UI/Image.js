import React from "react";
import styles from "./Image.module.css";

function Image({ imageUrl, onClose }) {
  return (
    <div className={styles.modalOverlay} onClick={onClose}>
      <div className={styles.modalContent}>
        <img src={imageUrl} alt="확대" className={styles.fullSizeImage} />
      </div>
    </div>
  );
}

export default Image;
