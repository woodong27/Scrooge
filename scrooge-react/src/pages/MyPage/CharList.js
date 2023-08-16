import React, { useState } from "react";

import SettingModal from "../../components/UI/SettingModal";
import styles from "./CharList.module.css";

const CharList = ({ handleCharacterChange, characters }) => {
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
          {characters.map((e, index) => {
            return (
              <div key={index} className={styles.item}>
                <div className={styles.one} onClick={() => handleModalOpen(e)}>
                  <img
                    src={`https://storage.googleapis.com/scroogestorage/avatars/${e}-1.png`}
                    className={styles.profile}
                    alt={`Item ${index}`}
                  />
                  <span>{e}</span>
                </div>
              </div>
            );
          })}

          {Array.from({ length: 100 }).map(
            (_, index) =>
              !characters.includes(index) && (
                <div key={index} className={styles.item}>
                  <div className={styles.one}>
                    <img
                      src={`${process.env.PUBLIC_URL}/Character/gacha.png`}
                      className={styles.profile}
                      alt={`Item ${index}`}
                    />
                    <span>{index}</span>
                  </div>
                </div>
              )
          )}
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
