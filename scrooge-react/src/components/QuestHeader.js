import { useNavigate } from "react-router-dom";

import styles from "./QuestHeader.module.css";

const QuestHeader = (props) => {
  const navigate = useNavigate();

  const gradientStyle = {
    background: `linear-gradient(180deg, ${props.color} 0%, rgba(169, 217, 244, 0) 100%)`,
  };
  const titleStyle = {
    textShadow: `2px 2px 0 ${props.titleColor}`,
  };
  const handleBack = () => {
    navigate("/community");
  };

  return (
    <div className={styles.container} style={gradientStyle}>
      <div
        className={
          props.title === "스크루지 드림" ? styles.onlyItem : styles.item
        }
        style={titleStyle}
      >
        {props.title}
      </div>
      {props.show && (
        <img
          src={`${process.env.PUBLIC_URL}/images/left.svg`}
          alt="뒤"
          className={styles.back}
          onClick={handleBack}
        />
      )}
      {props.children}
    </div>
  );
};

export default QuestHeader;
