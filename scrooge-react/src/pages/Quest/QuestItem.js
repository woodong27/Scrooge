import YellowCard from "../../components/UI/YellowCard";
import styles from "./QuestItem.module.css";

const QuestItem = (props) => {
  console.log(props);
  return (
    <div className={styles.box}>
      <YellowCard props={props.quest.description} />
    </div>
  );
};

export default QuestItem;
