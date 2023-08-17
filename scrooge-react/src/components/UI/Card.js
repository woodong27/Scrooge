import React from "react";
import styles from "./Card.module.css";

const Card = (props) => {
  const heightStyle = {
    height: props.height ? `${props.height}vh` : "332px",
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
