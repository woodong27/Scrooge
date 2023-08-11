import React, { useState } from "react";
import { useSelector } from "react-redux";

import YellowCard from "../../components/UI/YellowCard";
import styles from "./QuestItem.module.css";

const QuestItem = ({ id, title, handleAdd, show }) => {
  const globalToken = useSelector((state) => state.globalToken);
  const [isOpen, setOpen] = useState(false);
  const [data, setData] = useState();

  const handleOpen = () => {
    const postData = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    fetch(`https://day6scrooge.duckdns.org/api/quest/${id}`, postData)
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
      })
      .catch((error) => console.log(error));

    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };
  const handleSend = () => {
    handleAdd(data.id);
  };

  return (
    <div>
      <div className={styles.box} onClick={handleOpen}>
        <YellowCard props={title} />
      </div>
      {isOpen && data && data.id && (
        <div className={styles["modal-container"]} onClick={handleClose}>
          <div className={styles.modal}>
            <div className={styles.title}>{data.title}</div>
            <div className={styles.description}>{data.description}</div>
            <div className={styles.line}>
              <div className={styles.cnt}>조건: {data.maxCount}회</div>
              {show === "true" && (
                <button className={styles.addBtn} onClick={handleSend}>
                  추가하기
                </button>
              )}
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default QuestItem;
