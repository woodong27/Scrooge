import styles from "./QuestHeader.module.css";

const QuestHeader = (props) => {
  return (
    <div className={styles.container}>
      <div className={styles.item}>스크루지 드림</div>
      {props.children}
    </div>
  );
};

export default QuestHeader;
