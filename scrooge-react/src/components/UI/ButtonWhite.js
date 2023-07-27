import styles from "./ButtonWhite.module.css";

const ButtonGreen = (props) => {
  return (
    <div className={styles.primary} onClick={props.onClick}>
      {props.text}
      <div className={styles.shadow}></div>
    </div>
  );
};

export default ButtonGreen;
