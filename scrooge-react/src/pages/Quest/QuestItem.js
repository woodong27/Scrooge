import React, { useState } from "react";
import { useSelector } from "react-redux";

import YellowCard from "../../components/UI/YellowCard";
import styles from "./QuestItem.module.css";

const QuestItem = (props) => {
  const globalToken = useSelector((state) => state.globalToken);

  const [startX, setStartX] = useState(null);
  const [swiped, setSwiped] = useState(false);

  const handleTouchStart = (e) => {
    setStartX(e.touches[0].clientX);
    setSwiped(false);
  };

  const handleTouchMove = (e) => {
    if (startX !== null) {
      const currentX = e.touches[0].clientX;
      const diffX = startX - currentX;

      const maxOffset = 0.3 * window.innerWidth; // 화면의 30%
      const newOffset = Math.min(0, Math.max(-maxOffset, -diffX));

      if (diffX > maxOffset) {
        setSwiped(true);
      } else {
        setSwiped(false);
      }
      // setOffset(newOffset);
    }
  };

  const handleTouchEnd = () => {
    if (swiped) {
      handleAdd();
    }
    // setOffset(0);
    setStartX(null);
    setSwiped(false);
  };

  const handleAdd = () => {
    console.log("얍");
    const postData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    fetch(
      `https://day6scrooge.duckdns.org/api/quest/${props.id}/select`,
      postData
    )
      .then((resp) => resp.text())
      .then((data) => {
        console.log(data);
        if (data === "3개 이상의 퀘스트를 선택할 수 없습니다.") {
          console.log("이미 3개!");
        } else {
          //list에서 빼고, ing에 넣기...
        }
      })
      .catch((error) => console.log(error));
  };

  return (
    <div
      className={styles.box}
      onTouchStart={handleTouchStart}
      onTouchMove={handleTouchMove}
      onTouchEnd={handleTouchEnd}>
      <YellowCard props={props.quest.description} />
    </div>
  );
};

export default QuestItem;
