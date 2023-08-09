import React, { useEffect, useState } from "react";
import styles from "./CharList.module.css";

const Button = ({ onClick, children, className }) => {
  return (
    <button className={className} onClick={onClick}>
      {children}
    </button>
  );
};

const CharList = () => {
  const imageCount = 80; // 캐릭터 개수 만큼
  const imagePath = `${process.env.PUBLIC_URL}/Character`;
  const [randomImages, setRandomImages] = useState([]);

  useEffect(() => {
    const initialImages = Array.from({ length: imageCount }, (_, index) => (
      <img
        key={index} // 08-08 얘가 중요해요..
        className={styles["profile-image"]}
        src={`${imagePath}/gacha.png`}
        alt={`프로필 사진 ${index + 1}`}
      />
    ));

    setRandomImages(initialImages);
  }, []); // 컴포넌트가 처음 마운트될 때 한 번만 실행

  return (
    <div>
      <div className={styles.btnContainer}>
        <Button
          className={styles.gachaBtn}
          onClick={() => console.log("캐릭터....뽑습니다....")}
        >
          캐릭터 뽑기!!!!!!!!!!!!!!!
        </Button>
      </div>
      <div className={styles.charContainer}>{randomImages}</div>
    </div>
  );
};

export default CharList;


