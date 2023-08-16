import React, { useState } from "react";

import SettingModal from "../../components/UI/SettingModal";
import styles from "./CharList.module.css";

const CharList = ({ handleCharacterChange, characters }) => {
  console.log(characters);
  const [modal, setModal] = useState(false);
  const [characterId, setCharacterId] = useState(0);

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
