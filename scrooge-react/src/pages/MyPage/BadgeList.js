import styles from "./CharList.module.css";

const BadgeList = () => {
  const badgeCount = 9
  const badges = [];

  for (let i =1; i <= badgeCount; i++){
    const badgePath = `${process.env.PUBLIC_URL}/Badge/${i}.png`;
    badges.push(
      <img
        key={i}
        className={styles["profile-image"]}
        src={badgePath}
        alt={`뱃지 사진 ${i}`}
      />
    );
  };

  return(
    <div className={styles.charContainer}>
      {badges}
    </div>
  )
};

export default BadgeList;

