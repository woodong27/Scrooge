import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

import styles from "./Profile.module.css";
import Card from "../components/UI/Card";
import BackGround from "../components/BackGround";

const Profile = (props) => {
  const navigate = useNavigate();
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
      })
      .catch((error) => console.log(error));
  }, [memberId]);

  return (
    <BackGround>
      <div className={styles.back}>
        <img
          src={`${process.env.PUBLIC_URL}/images/back.png`}
          onClick={() => navigate(-1)}
          alt="뒤로"
        />
      </div>

      {data && data.level && data.mainAvatar.id && (
        <div>
          <div className={styles.empty} />
          <Card height={44}>
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
          </Card>
          <br />
          <Card height={28}>
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
                    <td className={styles.one}>자주 소비</td>
                    <td className={styles.two}>
                      {myData.frequentlyUsedCategory}
                    </td>
                    <td className={styles.three}>
                      {hereData.frequentlyUsedCategory}
                    </td>
                  </tr>
                  <tr>
                    <td className={styles.one}>많이 소비</td>
                    <td className={styles.two}>
                      {myData.highSpendingCategory}
                    </td>
                    <td className={styles.three}>
                      {hereData.highSpendingCategory}
                    </td>
                  </tr>
                  <tr>
                    <td className={styles.one}>스트릭</td>
                    <td className={styles.two}>{myData.maxStreak}</td>
                    <td className={styles.three}>{hereData.maxStreak}</td>
                  </tr>
                </table>
                {hereData.hasLastMonthPaymentHistory &&
                myData.hasLastMonthPaymentHistory ? (
                  <div>
                    당신의 절약 금액이
                    <span className={styles.highlight}>
                      {hereData.totalDifference > myData.totalDifference
                        ? " 적어요 "
                        : " 많아요 "}
                    </span>
                  </div>
                ) : (
                  <div className={styles.notYet}>아직 비교가 어려워요</div>
                )}
              </div>
            </div>
          </Card>
        </div>
      )}
    </BackGround>
  );
};

export default Profile;
