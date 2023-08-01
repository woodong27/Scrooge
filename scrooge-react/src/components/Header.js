import styles from "./Header.module.css";

const Header = (props) => {
  return (
    <div className={styles.container}>
      <div className={styles.item}>스크루지 파이트</div>
      {props.children}
    </div>
  );
};

export default Header;
