import styles from "./MyPageHeader.module.css";
import { Link } from "react-router-dom";

const MyPageHeader = () => {
  return(
    <div className={styles["header-container"]}>
      <div className={styles["header-text"]}>마이 페이지</div>
      <Link to="/mypage/settings">
      {/* <Link to="/settings"> */}
        <img 
          className={styles["header-icon"]} 
          src={`${process.env.PUBLIC_URL}/images/setting-icon.png`} 
          alt="설정 아이콘"/>
      </Link>
    </div>
  );
};

export default MyPageHeader;