import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import SettingModal from "../../components/UI/SettingModal";
import styles from "./CharList.module.css";

const CharList = ({ handleCharacterChange }) => {
  const globalToken = useSelector((state) => state.globalToken);
  const [characters, setCharacters] = useState([]);
  const [modal, setModal] = useState(false);
  const [characterId, setCharacterId] = useState(0);

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
        setModal(false);
      });
  }, []);

  const handleModalOpen = (index) => {
    setCharacterId(index);
    setModal(true);
  };

  const handleModalClose = () => {
    setModal(false);
  };

  const handleConfirm = () => {
    handleCharacterChange(characterId);
    setModal(false);
  };

  return (
    <div className={styles.frame}>
      <div className={styles.characters}>
        {characters.length > 0 && (
          <div className={styles.charContainer}>
            {Array.from({ length: 100 }).map((_, index) => {
              const imageUrl = characters.includes(index)
                ? `https://storage.googleapis.com/scroogestorage/avatars/${index}-1.png`
                : `${process.env.PUBLIC_URL}/Character/gacha.png`;

              return (
                <div key={index} className={styles.item}>
                  <div
                    className={styles.one}
                    onClick={() => handleModalOpen(index)}
                  >
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
      {modal && (
        <SettingModal
          message="메인 아바타를 변경하겠습니까?"
          onConfirm={handleConfirm}
          onCancel={handleModalClose}
        />
      )}
    </div>
  );
};

export default CharList;
