import React, { useState } from "react";
import { useSelector } from "react-redux";

import YellowCard from "../../components/UI/YellowCard";
import styles from "./QuestItem.module.css";

const QuestItem = ({ title, handleAdd, show, selectQuest, id, questId }) => {
  const globalToken = useSelector((state) => state.globalToken);
  const [isOpen, setOpen] = useState(false);
  const [data, setData] = useState(null);

  const handleUnselectOpen = () => {
    const postData = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    fetch(`https://day6scrooge.duckdns.org/api/quest/${questId}`, postData)
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
      })
      .catch((error) => console.log(error));

    setOpen(true);
  };

  const handleSelectOpen = () => {
    const postData = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    fetch(`https://day6scrooge.duckdns.org/api/quest/member`, postData)
      .then((resp) => resp.json())
      .then((data) => {
        console.log(data);
        console.log(data.find((item) => item.id === id));
        setData(data.find((item) => item.id === id));
      })
      .catch((error) => console.log(error));

    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };
  const handleSend = () => {
    handleAdd(questId);
  };

  return (
    <div className={styles.body}>
      {selectQuest ? (
        <div className={styles.box} onClick={handleSelectOpen}>
          <YellowCard props={title} />
        </div>
      ) : (
        <div className={styles.box} onClick={handleUnselectOpen}>
          <YellowCard props={title} />
        </div>
      )}
      {isOpen &&
        data &&
        (selectQuest ? (
          <div className={styles["modal-container"]} onClick={handleClose}>
            <div className={styles.modal}>
              <div className={styles.title}>{data.quest.title}</div>
              <div className={styles.description}>{data.quest.description}</div>
              <div className={styles.line}>
                <div className={styles.cnt}>
                  달성: {data.completeCount} / {data.quest.maxCount}회
                </div>
              </div>
            </div>
          </div>
        ) : (
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
        ))}
    </div>
  );
};

export default QuestItem;
