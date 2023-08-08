import { Link } from "react-router-dom";

import styles from "./ChallengeItem.module.css";

const ChallengeItem = (props) => {
  return (
    <div className={styles.container}>
      <div className={styles.item_top}>
        <div className={styles.img_contianer}>
          <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="더미" />
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
      <Link to={`/challenge/${props.id}`}>
        <button>{props.text}</button>
      </Link>
    </div>
  );
};

export default ChallengeItem;
