import styles from "./QuestHeader.module.css";

const QuestHeader = (props) => {
  const gradientStyle = {
    background: `linear-gradient(180deg, ${props.color} 0%, rgba(169, 217, 244, 0) 100%)`,
  };
  const titleStyle = {
    textShadow: `2px 2px 0 ${props.titleColor}`,
  };
  return (
    <div className={styles.container} style={gradientStyle}>
      <div className={styles.item} style={titleStyle}>
        {props.title}
      </div>
      {props.children}
    </div>
  );
};

export default QuestHeader;
