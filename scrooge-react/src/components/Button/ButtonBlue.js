import styles from "./ButtonBlue.module.css";

const ButtonBlue = (props) => {
  return (
    <div className={styles.primary} onClick={props.onClick}>
      {props.children}
      <div className={styles.shadow}></div>
    </div>
  );
};

export default ButtonBlue;
