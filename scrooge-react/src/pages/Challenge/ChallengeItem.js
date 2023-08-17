import { Link } from "react-router-dom";

import styles from "./ChallengeItem.module.css";

const ChallengeItem = (props) => {
  return (
    <div className={styles.container}>
      <div className={styles.item_top}>
        <div className={styles.img_contianer}>
          <img src={props.mainImg} alt="" />
        </div>
        <div className={styles.text_container}>
          <p className={styles.title}>{props.title}</p>
          <p>
            참여인원: {props.currentParticipants} / {props.minParticipants}명
          </p>
          <p>
            #{props.category} #{props.period}
          </p>
        </div>
      </div>

      {props.text === "인증하기" ? (
        <Link to={`/challenge/my/${props.id}`}>
          <button>{props.text}</button>
        </Link>
      ) : props.text === "살펴보기" || props.text === "참여하기" ? (
        <Link to={`/challenge/${props.id}`}>
          <button>{props.text}</button>
        </Link>
      ) : props.text ? (
        <div className={styles.isWin}>승리</div>
      ) : (
        <div className={styles.isLose}>패배</div>
      )}
    </div>
  );
};

export default ChallengeItem;
