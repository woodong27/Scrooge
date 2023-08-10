import React, { useState } from "react";
import { useSelector } from "react-redux";

import YellowCard from "../../components/UI/YellowCard";
import styles from "./QuestItem.module.css";

const QuestItem = (props) => {
  const globalToken = useSelector((state) => state.globalToken);
  const [isOpen, setOpen] = useState(false);
  const [data, setData] = useState();

  const handleOpen = () => {
    // const postData = {
    //   method: "GET",
    //   headers: {
    //     "Content-Type": "application/json",
    //     Authorization: globalToken,
    //   },
    // };
    // fetch(`https://day6scrooge.duckdns.org/api/quest/${props.id}`, postData)
    //   .then((resp) => resp.json())
    //   .then((data) => {
    //     setData(data);
    //   })
    //   .catch((error) => console.log(error));

    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  const dummy = {
    id: 1,
    title: "퀘스트 이름",
    description: "퀘스트에 대한 간단한 설명",
    maxCount: 3,
  };
  return (
    <div>
      <div className={styles.box} onClick={handleOpen}>
        <YellowCard props={dummy.title} />
      </div>
      {isOpen && (
        <div className={styles["modal-container"]} onClick={handleClose}>
          <div className={styles.modal}>
            <div className={styles.line}>
              <div className={styles.title}>{dummy.title}</div>
              <div className={styles.cnt}>조건: {dummy.maxCount}회</div>
            </div>
            <div className={styles.description}>{dummy.description}</div>
            <div className={styles.foot}>
              <button className={styles.addBtn} onClick={props.handleAdd}>
                추가하기
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default QuestItem;
