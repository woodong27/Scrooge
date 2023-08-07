import YellowCard from "../../components/UI/YellowCard";
import styles from "./QuestItem.module.css";

const QuestItem = (props) => {
  return (
    <div className={styles.box}>
      <YellowCard props={props.title} />
    </div>
  );
};

export default QuestItem;
