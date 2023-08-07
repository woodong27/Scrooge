import React from "react";
import styles from "./TodayCard.module.css";

const TodayCard = (props) => {
  const heightStyle = {
    height: `${props.height}px`,
  };
  return (
    <div className={styles.card_shadow} style={heightStyle}>
      <div className={styles.card} style={heightStyle}>
        {props.children}
      </div>
    </div>
  );
};

export default TodayCard;
