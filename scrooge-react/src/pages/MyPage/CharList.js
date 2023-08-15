import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import styles from "./CharList.module.css";

const Button = ({ onClick, children, className }) => {
  return (
    <button className={className} onClick={onClick}>
      {children}
    </button>
  );
};

const CharList = ({ setModal, setItem, handleModalOpen }) => {
  const globalToken = useSelector((state) => state.globalToken);
  const [characters, setCharacters] = useState([]);

  useEffect(() => {
    const getData = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    fetch(`https://day6scrooge.duckdns.org/api/avatar/my-avatar`, getData)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);

        const id = data.map((item) => item.avatar.id);
        setCharacters(id);
      });
  }, []);

  const handleGacha = () => {
    const putData = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    fetch("https://day6scrooge.duckdns.org/api/member/gacha", putData)
      .then((res) => {
        if (!res.ok) {
          throw new Error("캐릭터 가챠 실패");
        }
        return res.json();
      })
      .then((data) => {
        console.log(data);
        setCharacters(...characters, data.avatarId);
        setItem(data);
        handleModalOpen();
      });
  };

  return (
    <div className={styles.frame}>
      <div className={styles.btnContainer}>
        <Button className={styles.gachaBtn} onClick={handleGacha}>
          캐릭터 뽑기
        </Button>
      </div>

      <div className={styles.characters}>
        {characters.length > 0 && (
          <div className={styles.charContainer}>
            {Array.from({ length: 100 }).map((_, index) => {
              const imageUrl = characters.includes(index)
                ? `https://storage.googleapis.com/scroogestorage/avatars/${index}-1.png`
                : `${process.env.PUBLIC_URL}/Character/gacha.png`;

              return (
                <div key={index} className={styles.item}>
                  <div className={styles.one}>
                    <img
                      src={imageUrl}
                      className={styles.profile}
                      alt={`Item ${index}`}
                    />
                    <span>{index}</span>
                  </div>
                </div>
              );
            })}
          </div>
        )}
      </div>
    </div>
  );
};

export default CharList;
