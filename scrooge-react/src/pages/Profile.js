import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useSelector } from "react-redux";

import styles from "./Profile.module.css";
import CharacterCard from "../components/UI/CharacterCard";
import Card from "../components/UI/Card";
import BackGround from "../components/BackGround";

const Profile = (props) => {
  const memberId = useSelector((state) => state.memberId);
  const params = useParams();

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
        console.log("hereData", data);
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
        console.log("myData", data);
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
          <Card height={246}>
            <div className={styles.todayCard}>
              <div>
                <table className={styles.table}>
                  <tr className={styles.up}>
                    <th className={styles.one}>비교</th>
                    <th className={styles.two}>나</th>
                    <th className={styles.three}>{data.nickname}</th>
                  </tr>
                  <tr>
                    <td className={styles.one}>이번 달</td>
                    <td className={styles.two}>{myData.thisMonthTotal}</td>
                    <td className={styles.three}>{hereData.thisMonthTotal}</td>
                  </tr>
                  <tr>
                    <td className={styles.one}>주 소비</td>
                    <td className={styles.two}>{myData.category}</td>
                    <td className={styles.three}>{hereData.category}</td>
                  </tr>
                  <tr>
                    <td className={styles.one}>정산일</td>
                    <td className={styles.two}>{myData.maxStreak}</td>
                    <td className={styles.three}>{hereData.maxStreak}</td>
                  </tr>
                </table>
                <div>
                  지난달과 비교했을 때 <br />
                  당신이
                  <span className={styles.highlight}>
                    {hereData.lastMonthTotal - hereData.thisMonthTotal >
                    myData.lastMonthTotalMonthTotal - myData.thisMonthTotal
                      ? " 더 "
                      : " 덜 "}
                  </span>
                  절약했네요!
                </div>
              </div>
            </div>
          </Card>
        </div>
      )}
    </BackGround>
  );
};

export default Profile;
