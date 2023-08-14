import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import styles from "./Profile.module.css";
import CharacterCard from "../components/UI/CharacterCard";
import BackGround from "../components/BackGround";

const Profile = (props) => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const postData = {
      method: "GET",
    };
    //API에 필요한 것만 받아오게...
    fetch(
      `https://day6scrooge.duckdns.org/api/member/info/${props.id}`,
      postData
    )
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
      })
      .catch((error) => console.log(error));
  }, []);

  return (
    <BackGround>
      {data && data.levelId && data.mainAvatar.id && (
        <div>
          <div className={styles.empty} />
          <CharacterCard>
            <div>
              <div className={styles.infoheader}>
                <img
                  className={styles.badge}
                  src={`${process.env.PUBLIC_URL}/images/sample-badge.svg`}
                  alt="뱃지"
                />
                <span>
                  <p>Lv. {data.levelId}</p>
                  <p>{data.nickname}</p>
                </span>
              </div>
              <div className={styles.border} />

              <span className={styles.charactercoin}>
                <img
                  className={styles.character}
                  src={`https://storage.googleapis.com/scroogestorage/avatars/${data.mainAvatar.id}-1.png`}
                  alt="캐릭터"
                />
              </span>
              <div className={styles.statemessage}>
                <textarea
                  className={styles.content}
                  value={data.message}
                  readOnly
                />
              </div>
            </div>
          </CharacterCard>
        </div>
      )}
    </BackGround>
  );
};

export default Profile;
