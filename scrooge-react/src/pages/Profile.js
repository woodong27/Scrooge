import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useSelector } from "react-redux";

import styles from "./Profile.module.css";
import CharacterCard from "../components/UI/CharacterCard";
import BackGround from "../components/BackGround";

const Profile = (props) => {
  const memberId = useSelector((state) => state.memberId);
  const params = useParams();
  console.log(memberId, params.id);

  const [data, setData] = useState([]);
  const [myData, setMyData] = useState([]);
  const [hereData, setHereData] = useState([]);

  useEffect(() => {
    //방문 페이지 정보 가져오기
    const postData = {
      method: "GET",
    };
    fetch(`https://day6scrooge.duckdns.org/api/member/${params.id}`, postData)
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
        console.log("data", data);
      })
      .catch((error) => console.log(error));

    const myData = {
      method: "GET",
    };

    //방문 페이지 리캡 가져오기
    fetch(
      `https://day6scrooge.duckdns.org/api/payment-history/recap/${params.id}`,
      myData
    )
      .then((resp) => resp.json())
      .then((data) => {
        setHereData(data);
        console.log("hereData", hereData);
      })
      .catch((error) => console.log(error));
  }, [params.id]);

  useEffect(() => {
    //내 리캡 가져오기
    fetch(
      `https://day6scrooge.duckdns.org/api/payment-history/recap/${memberId}`,
      myData
    )
      .then((resp) => resp.json())
      .then((data) => {
        setMyData(data);
        console.log("myData", myData);
      })
      .catch((error) => console.log(error));
  }, [memberId]);

  return (
    <BackGround>
      {data && data.level && data.mainAvatar.id && (
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
                  <p>Lv. {data.level}</p>
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
