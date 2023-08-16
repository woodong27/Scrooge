import { useState, useEffect, useRef } from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
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
    setIsEdit(true);
  };

  const handleDelete = () => {
    props.commentDelete(props.id);
  };

  return (
    <div className={styles.box}>
      <Link to={`/profile/${props.memberId}`}>
        <img
          className={styles.character}
          src={`https://storage.googleapis.com/scroogestorage/avatars/${props.memberAvatarAddress}-1.png`}
          alt="캐릭터"
        />
      </Link>
      <div className={styles.up}>
        <div className={styles.nickname}>
          <Link to={`/profile/${props.memberId}`}>{props.memberNickname}</Link>
          {memberId === props.memberId ? (
            <>
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
            </>
          ) : (
            ""
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
