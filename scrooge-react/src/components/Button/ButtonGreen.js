import styles from "./ButtonGreen.module.css";

const ButtonGreen = (props) => {
  return (
    <div className={styles.card}>
      <div className={styles.shadow}>
        <div className={styles.primary} onClick={props.onClick}>
          {props.text}
        </div>
      </div>
    </div>
  );
};

export default ButtonGreen;
