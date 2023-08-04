import styles from "./CharList.module.css";

const CharList = () => {
  return(
    <div>
      <div className={styles.charContent}>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/1.png`} alt="프로필 사진"/>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/2.png`} alt="프로필 사진"/>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/3.png`} alt="프로필 사진"/>
      </div>
      <div className={styles.charContent}>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/4.png`} alt="프로필 사진"/>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/5.png`} alt="프로필 사진"/>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/6.png`} alt="프로필 사진"/>
      </div>
      <div className={styles.charContent}>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/7.png`} alt="프로필 사진"/>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/8.png`} alt="프로필 사진"/>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/9.png`} alt="프로필 사진"/>
      </div>
      <div className={styles.charContent}>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/10.png`} alt="프로필 사진"/>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/11.png`} alt="프로필 사진"/>
        <img className={styles["profile-image"]} src={`${process.env.PUBLIC_URL}/Character/12.png`} alt="프로필 사진"/>
      </div>
    </div>
  );
}

export default CharList;