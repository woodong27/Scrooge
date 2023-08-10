import React, { useState } from "react";

import YellowCard from "../../components/UI/YellowCard";
import styles from "./QuestItem.module.css";

const QuestItem = (props) => {
  const [isOpen, setOpen] = useState(false);
  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
      <div className={styles.box} onClick={handleOpen}>
        <YellowCard props={props.quest.title} />
      </div>
      {isOpen && (
        <div className={styles["modal-container"]} onClick={handleClose}>
          <div className={styles.modal}>
            {props.quest.description}
            <button className={styles.addBtn} onClick={props.handleAdd}>
              추가하기
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default QuestItem;
