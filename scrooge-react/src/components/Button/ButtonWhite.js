import styles from "./ButtonWhite.module.css";

const ButtonWhite = (props) => {
  return (
    <div className={styles.frame}>
      <div className={styles.card_shadow}>
        <div className={styles.card} onClick={props.onClick}>
          {props.text}
        </div>
      </div>
    </div>
  );
};

export default ButtonWhite;
