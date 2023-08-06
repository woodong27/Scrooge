import styles from "./BackGround.module.css";

const BackGround = (props) => {
  return <div className={styles.bg}>{props.children}</div>;
};

export default BackGround;
