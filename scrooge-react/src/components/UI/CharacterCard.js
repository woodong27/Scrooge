import React from "react";
import styles from "./CharacterCard.module.css";

const CharacterCard = (props) => {
  return (
    <div className={styles.card}>
      {props.children}
      <div className={styles.card_shadow}></div>
    </div>
  );
};

export default CharacterCard;
