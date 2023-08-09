import { useState, useEffect, useRef } from "react";
import { useSelector } from "react-redux";
import styles from "./Comment.module.css";

const Comment = (props) => {
  const globalToken = useSelector((state) => state.globalToken);
  const memberId = useSelector((state) => state.memberId);
  const [isEdit, setIsEdit] = useState(false);
  const [comment, setComment] = useState("");

  useEffect(() => {
    setComment(props.content);
  }, []);

  const handleOpen = () => {
    if (memberId === props.memberId) {
      setIsEdit(true);
    }
  };

  const handleDelete = () => {
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
        <div className={styles.nickname}>
          {props.memberNickname}
          {isEdit ? (
            <div className={styles.delete} onClick={handleDelete}>
              삭제
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
      <div className={styles.content}>
        <div>{comment}</div>
      </div>
    </div>
  );
};

export default Comment;
