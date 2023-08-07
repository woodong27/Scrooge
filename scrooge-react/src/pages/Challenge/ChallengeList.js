import ChallengeItem from "./ChallengeItem";
import styles from "./ChallengeList.module.css";

const ChallengeList = () => {
  const dummy = ["[식비] 배달 일주일에 한번만 시키기", "커피", "핏자", "라면"];
  return (
    <div className={styles.list}>
      {dummy.map((e) => {
        return <ChallengeItem title={e}></ChallengeItem>;
      })}
    </div>
  );
};

export default ChallengeList;
