import MyPageHeader from "./MyPageHeader";
import MyPageProfile from "./MyPageProfile";
import styles from "./Mypage.module.css";

const MyPage = () => {
  return (
    <div className={styles.body}>
      <MyPageHeader />
      <MyPageProfile />
    </div>
  );
};

export default MyPage;
