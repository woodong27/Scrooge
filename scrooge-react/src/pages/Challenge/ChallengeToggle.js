import styles from "./ChallengeToggle.module.css";

const ChallengeToggle = () => {
  return (
    <div className={styles["challenge-toggle"]}>
      <div className={`${styles.tab} ${styles.active_tab}`}>전체 챌린지</div>
      <div className={styles.tab}>마이 페이지</div>
    </div>
  );
};

export default ChallengeToggle;
