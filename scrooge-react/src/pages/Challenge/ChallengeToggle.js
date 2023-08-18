import styles from "./ChallengeToggle.module.css";

const ChallengeToggle = (props) => {
  return (
    <div className={styles["challenge-toggle"]}>
      <div
        className={`${styles.tab} ${
          props.isMyChallenge ? "" : styles.active_tab
        }`}
        onClick={props.allChallengeeHandler}
      >
        전체 챌린지
      </div>
      <div
        className={`${styles.tab} ${
          props.isMyChallenge ? styles.active_tab : ""
        }`}
        onClick={props.myChallengeHandler}
      >
        마이 챌린지
      </div>
    </div>
  );
};

export default ChallengeToggle;
