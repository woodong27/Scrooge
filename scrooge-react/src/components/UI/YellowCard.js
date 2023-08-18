import styles from "./YellowCard.module.css";

const YellowCard = ({ props }) => {
  return (
    <div className={styles.card}>
      <div className={styles.shadow}>
        <div className={styles.primary}>{props}</div>
      </div>
    </div>
  );
};

export default YellowCard;
