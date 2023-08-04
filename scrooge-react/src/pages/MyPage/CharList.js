import styles from "./CharList.module.css";

const CharList = () => {
  const imageCount = 24; //캐릭터 개수 만큼
  const images = [];

  for (let i =1; i <= imageCount; i++){
    const imagePath = `${process.env.PUBLIC_URL}/Character/${i}.png`;
    images.push(
      <img
        key={i}
        className={styles["profile-image"]}
        src={imagePath}
        alt={`프로필 사진 ${i}`}
      />
    );
  };

  return(
    <div className={styles.charContainer}>
      {images}
    </div>
  )
}

export default CharList;

