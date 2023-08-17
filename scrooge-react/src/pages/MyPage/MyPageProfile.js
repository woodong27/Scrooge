import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import styles from "./MyPageProfile.module.css";
import MyPageExpBar from "./MyPageExpBar";
import Report from "./Report";
import ItemTab from "./ItemTab";
import axios from "axios";

const MyPageProfile = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const headers = { Authorization: globalToken };

  const [data, setData] = useState([]);
  const [showItemList, setShowItemList] = useState(false);
  const [levelId, setLevelId] = useState(1);
  const [exp, setExp] = useState(0);
  const [maxExp, setMaxExp] = useState(0);
  const [modal, setModal] = useState(false);
  const [gacha, setGacha] = useState(0);
  const [avatar, setAvatar] = useState(0);
  const [characters, setCharacters] = useState([]);
  const [newCharacter, setNewCharacter] = useState([]);
  const [badges, setBadges] = useState([]);

  const handleEditBtn = () => {
    setShowItemList((prevState) => !prevState);
  };

  const handleModalOpen = () => {
    setModal(true);
  };
  const handleModalClose = () => {
    setShowItemList(true);
    setModal(false);
  };

  useEffect(() => {
    const postData = {
      method: "GET",
      headers,
    };
    fetch("https://day6scrooge.duckdns.org/api/member/info", postData)
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
        setExp(data.exp);
        setMaxExp(data.maxExp);
        setLevelId(data.levelId);
        setAvatar(data.mainAvatar.id);
        setGacha(data.remainGacha);
        if (data.levelId <= 5) {
          setMaxExp(500);
        } else if (data.levelId <= 10) {
          setMaxExp(600);
        } else if (data.levelId <= 15) {
          setMaxExp(700);
        } else if (data.levelId <= 20) {
          setMaxExp(800);
        } else if (data.levelId <= 25) {
          setMaxExp(900);
        } else {
          setMaxExp(1000);
        }
      })
      .catch((error) => console.log(error));
  }, [globalToken]);

  useEffect(() => {
    const getData = {
      method: "GET",
      headers,
    };
    fetch(`https://day6scrooge.duckdns.org/api/avatar/my-avatar`, getData)
      .then((res) => res.json())
      .then((data) => {
        const id = data.map((item) => item.avatar.id);
        setCharacters(id);
        setModal(false);
      });

    axios
      .get("https://day6scrooge.duckdns.org/api/badge/member", {
        headers,
      })
      .then((resp) => {
        setBadges(resp.data);
      })
      .catch((e) => console.log(e));
  }, []);

  const handleGacha = () => {
    if (gacha === 0) {
      return;
    }

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
        setGacha(gacha - 1);
        if (!characters.includes(parseInt(data.avatarId))) {
          characters.push(parseInt(data.avatarId));
          setCharacters(characters);
        }
        setNewCharacter(data);
        handleModalOpen();
      });
  };

  const handleChange = (newId) => {
    const putData = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    fetch(`https://day6scrooge.duckdns.org/api/avatar/${newId}`, putData)
      .then((res) => {
        if (!res.ok) {
          throw new Error("캐릭터 변경 실패");
        }
        return res.json();
      })
      .then((data) => {
        setAvatar(newId);
      });
  };

  return (
    <div className={styles["profile-container"]}>
      {data && data.levelId && data.mainAvatar.id && (
        <div className={styles["profile-content"]}>
          <div className={styles.profileImage}>
            <img
              src={`https://storage.googleapis.com/scroogestorage/avatars/${avatar}-1.png`}
              alt="프로필 사진"
            />
          </div>
          <ul className={styles["profile-info"]}>
            <li>Lv.{data.levelId}</li>
            <li>{data.nickname}</li>

            <li className={styles["items-info"]}>
              <div>
                <img
                  src={`${process.env.PUBLIC_URL}/images/profile-icon.png`}
                  alt="캐릭터 아이콘"
                />
                <span>{characters.length}</span>
              </div>
              <div>
                <img
                  src={`${process.env.PUBLIC_URL}/images/badge-icon.png`}
                  alt="뱃지 아이콘"
                />
                <span>{badges.length}</span>
              </div>
            </li>
            <li>
              <div className={styles.btnContainer}>
                <button className={styles.gachaBtn} onClick={handleGacha}>
                  뽑기 {gacha}회 가능
                </button>
              </div>
            </li>
          </ul>
        </div>
      )}

      {maxExp !== 0 && <MyPageExpBar exp={exp} maxExp={maxExp} />}

      <div
        className={`${styles["edit-btn"]} ${showItemList ? "active" : ""}`}
        onClick={handleEditBtn}
      >
        {showItemList ? "소비 리포트 보러 가기" : "캐릭터 뽑으러 가기"}
      </div>
      {showItemList && characters.length > 0 ? (
        <ItemTab handleCharacterChange={handleChange} characters={characters} />
      ) : (
        <Report />
      )}

      {modal && (
        <div>
          <div
            className={styles.modal_overlay}
            onClick={handleModalClose}
          ></div>
          <div className={styles.modalContainer}>
            <img
              src={`https://storage.googleapis.com/scroogestorage/avatars/${newCharacter.avatarId}-1.png`}
              className={styles.profile}
              alt={`gacha`}
            />
            <span className={styles.ment}>
              {newCharacter.isDuplicated
                ? "이미 가지고 있는 캐릭터에요.."
                : `${newCharacter.avatarId}번이 새로 생겼어요!!`}
            </span>
          </div>
        </div>
      )}
    </div>
  );
};

export default MyPageProfile;
