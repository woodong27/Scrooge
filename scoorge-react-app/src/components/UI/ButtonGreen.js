import styles from "./ButtonGreen.module.css";

const ButtonGreen = (props) => {
  return (
    <div className={styles.primary}>
      {props.text}
      <div className={styles.shadow}></div>
    </div>
  );
};

export default ButtonGreen;
