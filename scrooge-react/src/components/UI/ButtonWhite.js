import styles from "./ButtonWhite.module.css";

const ButtonGreen = (props) => {
  return (
    <div className={styles.shadow}>
      <div className={styles.primary} onClick={props.onClick}>
        {props.text}
      </div>
    </div>
  );
};

export default ButtonGreen;
