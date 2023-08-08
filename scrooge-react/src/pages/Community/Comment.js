import { useState, useEffect, useRef } from "react";
import { useSelector } from "react-redux";
import styles from "./Comment.module.css";

const Comment = (props) => {
  const globalToken = useSelector((state) => state.globalToken);

  const [isEdit, setIsEdit] = useState(false);
  const [comment, setComment] = useState("");

  useEffect(() => {
    setComment(props.content);
  }, []);

  const handleOpen = () => {
    setIsEdit(true);
  };

  const handleDelete = () => {
    console.log(props);
    props.commentDelete(props.id);
  };

  return (
    <div className={styles.box}>
      <img
        className={styles.character}
        src={`http://day6scrooge.duckdns.org:8081/${props.memberAvatarAddress}`}
        alt="캐릭터"
      />
      <div className={styles.up}>
        <div className={styles.nickname}>{props.memberNickname}</div>
        <div className={styles.createdAt}>
          {props.createdAt.split("T")[0]}{" "}
          {props.createdAt.split("T")[1].slice(0, 5)}
        </div>
      </div>
      <div className={styles.content}>
        <div>
          {comment}
          {isEdit ? (
            <div className={styles.delete} onClick={handleDelete}>
              {" "}
              삭제{" "}
            </div>
          ) : (
            <div className={styles.option} onClick={handleOpen}>
              <img
                src={`${process.env.PUBLIC_URL}/images/option.png`}
                alt="더보기"
              />
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default Comment;
