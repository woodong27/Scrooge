import { Link, useParams, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import axios from "axios";

import styles from "./ChallengeJoin.module.css";
import Toast from "../../components/UI/Toast";
import backImg from "../../assets/back.png";

const ChallengeJoin = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const memberId = useSelector((state) => state.memberId);

  const [data, setData] = useState([]);
  const [makeToast, setMakeToast] = useState(false);
  const [joinToast, setJoinToast] = useState(false);
  const [isJoin, setIsJoin] = useState(false);
  const params = useParams();
  const navigate = useNavigate();

  const joinChallengeHandler = () => {
    axios
      .post(
        `https://day6scrooge.duckdns.org/api/challenge/${params.id}/participate`,
        "",
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: globalToken,
          },
        }
      )
      .then(() => {
        setJoinToast(true);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  };
  const startChallengeHandler = () => {
    axios
      .put(
        `https://day6scrooge.duckdns.org/api/challenge/${params.id}/start`,
        "",
        {
          headers: {
            Authorization: globalToken,
          },
        }
      )
      .then((resp) => {
        resp.data.status === "Fail"
          ? setMakeToast(true)
          : navigate("/challenge", { state: "시작" });
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  };

  useEffect(() => {
    axios
      .get(`https://day6scrooge.duckdns.org/api/challenge/${params.id}`)
      .then((response) => {
        setData(response.data);

        for (const id of response.data.participantIds) {
          if (memberId === id) {
            setIsJoin(true);
            break;
          }
        }
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, [joinToast]);

  return (
    <div className={styles.layout}>
      <div className={styles.body}>
        <Link className={styles.back} to="/challenge">
          <img src={backImg} alt="뒤로가기"></img>
        </Link>
        <img className={styles.img} src={data.mainImageAddress} alt="더미" />
        <div className={styles.container}>
          <h1>{data.title}</h1>
          <p className={styles.tag}>
            #{data.category} #{data.period}
          </p>
          <div className={styles.content}>
            <span>
              현재인원: {data.currentParticipants} / {data.minParticipants}명
            </span>
            <span>경험치 500+</span>
          </div>
        </div>
        <div className={styles.mid_container}>
          <h2>이렇게 인증해주세요</h2>
          <div>- 매일 전날의 소비 내역을 불러와주세요.</div>
          <div>- 사진이 잘 보이도록 촬영해주세요.</div>
          <div className={styles.auth_img}>
            {data.challengeExampleImageDtoList &&
              data.challengeExampleImageDtoList.map((e, i) => (
                <img
                  className={styles.img_list}
                  key={i}
                  src={e["imgAddress"]}
                  alt=""
                />
              ))}
          </div>
        </div>
        <div className={styles.bottom_container}>
          <h2>이런 분들께 추천해요</h2>
          <div>{data.description}</div>
        </div>

        {memberId === data.masterId ? (
          <div className={styles.primary} onClick={startChallengeHandler}>
            챌린지 시작하기!
            <div className={styles.shadow}></div>
          </div>
        ) : (
          <div
            style={{
              position: "absolute",
              bottom: "2rem",
              left: "50%",
              transform: "translateX(-50%)",
            }}
          >
            {isJoin ? (
              <div className={styles.primary2}>
                이미 참여중이에요!
                <div className={styles.shadow}></div>
              </div>
            ) : (
              <div className={styles.primary} onClick={joinChallengeHandler}>
                챌린지에 도전할래요!
                <div className={styles.shadow}></div>
              </div>
            )}
          </div>
        )}

        {makeToast && (
          <Toast setToast={setMakeToast} text="시작 가능한 인원이 부족해요!" />
        )}
        {joinToast && (
          <Toast setToast={setJoinToast} text="참여가 완료 되었어요!" />
        )}
      </div>
    </div>
  );
};

export default ChallengeJoin;
