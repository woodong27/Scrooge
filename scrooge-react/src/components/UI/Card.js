import React from "react";
import styles from "./Card.module.css";

const Card = (props) => {
  const heightStyle = {
    height: `${props.height}vh`,
  };
  return (
    <div className={styles.frame}>
      <div className={styles.card_shadow} style={heightStyle}>
        <div className={styles.card} style={heightStyle}>
          {props.children}
        </div>
      </div>
    </div>
  );
};

export default Card;
