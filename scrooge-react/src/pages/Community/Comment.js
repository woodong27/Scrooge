import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import styles from "./Article.module.css";

const Comment = (props) => {
  const globalToken = useSelector((state) => state.globalToken);

  useEffect(() => {});

  return (
    <div className={styles.box}>
      <img
        className={styles.character}
        src={`http://day6scrooge.duckdns.org:8081/${props.memberAvatarAddress}`}
        alt="캐릭터"
      />
      <div className={styles.nickname}>{props.memberNickname}</div>
      <div className={styles.createdAt}>
        {props.createdAt.split("T")[0]} {props.createdAt.split("T")[1]}
      </div>

      <div className={styles.content}>{props.content}</div>
    </div>
  );
};

export default Comment;
