import styles from "./Header.module.css";

const Header = (props) => {
  return (
    <div className={styles.container}>
      <div className={styles.item}>{props.text}</div>
      <div>{props.children}</div>
    </div>
  );
};

export default Header;
