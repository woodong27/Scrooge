import styles from "./MyPageHeader.module.css";

const MyPageHeader = () => {
  return(
    <div className={styles["header-container"]}>
      <div className={styles["header-text"]}>마이 페이지</div>
      <img  className={styles["header-icon"]} src={`${process.env.PUBLIC_URL}/images/setting-icon.png`} alt="설정 아이콘"/>
    </div>
  );
};

export default MyPageHeader;