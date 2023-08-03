import styles from "./MyPageProfile.module.css";

const MyPageProfile = () => {
  return(
    <div className={styles["profile-container"]}>
      <div className={styles["profile-content"]}>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/20.png`} alt="프로필 사진"/>
        <ul className={styles["profile-info"]}>
          <li>Lv.3</li>
          <li>돈그만써</li>
          <li>
            <img src ={} alt="보유 캐릭터 수"/>
          </li>
        </ul>
        <div className={styles["edit-btn"]}>
          <img src={`${process.env.PUBLIC_URL}/images/edit-btn.png`} alt="편집 버튼"/> 
        </div> 
      </div>
    </div>
  );
};

export default MyPageProfile;