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

  // 1부터 80까지의 고유한 랜덤한 숫자 배열을 생성하는 함수
  const generateRandomNumbers = (count) => {
    const numbers = [];
    while (numbers.length < count) {
      const randomNumber = Math.floor(Math.random() * count) + 1;
      if (!numbers.includes(randomNumber)) {
        numbers.push(randomNumber);
      }
    }
    return numbers;
  };

  useEffect(() => {
    // 랜덤한 숫자 배열을 생성합니다.
    const randomImageNumbers = generateRandomNumbers(imageCount);

    // 랜덤한 숫자에 해당하는 이미지 엘리먼트를 생성합니다.
    const images = randomImageNumbers.map((number) => (
      <img
        key={number}
        className={styles["profile-image"]}
        src={`${imagePath}/${number}.png`}
        alt={`프로필 사진 ${number}`}
      />
    ));

    // 랜덤 이미지 배열을 상태(State)로 업데이트합니다.
    setRandomImages(images);
  }, []); // 빈 의존성 배열은 컴포넌트가 처음 마운트될 때 한 번만 실행됨을 보장합니다.

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
