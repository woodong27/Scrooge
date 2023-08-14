import { Link, useParams } from "react-router-dom";
import axios from "axios";
import { useState, useEffect, useRef } from "react";
import { useSelector } from "react-redux";

import backImg from "../../assets/back.png";
import styles from "./ChallengeDetail.module.css";
import ProgressBar from "./ProgressBar";
import Chips from "../../components/UI/Chips";
import AuthProgress from "./AuthProgress";
import AuthModal from "./AuthModal";

const ChallengeDetail = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const headers = { Authorization: globalToken };
  const params = useParams();

  const [data, setData] = useState([]);
  const [authImg, setAuthImg] = useState([]);
  const imgRef = useRef();
  const [showModal, setShowModal] = useState(false);
  const [selectChip, setSelectChip] = useState("나의 인증 현황");
  const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
  const formData = new FormData();

  useEffect(() => {
    axios
      .get(
        `https://day6scrooge.duckdns.org/api/challenge/${params.id}/member/started`,
        { headers }
      )
      .then((response) => {
        const startDate = new Date(response.data.startDate);
        const endDate = new Date(response.data.endDate);

        const startMonth = startDate.getMonth() + 1;
        const startDay = startDate.getDate();
        const startAt = daysOfWeek[startDate.getDay()];
        const endMonth = endDate.getMonth() + 1;
        const endDay = endDate.getDate();
        const endAt = daysOfWeek[endDate.getDay()];

        response.data.startMonth = startMonth;
        response.data.startDay = startDay;
        response.data.startAt = startAt;
        response.data.endMonth = endMonth;
        response.data.endDay = endDay;
        response.data.endAt = endAt;

        setData(response.data);
      })
      .then(() => {
        axios
          .get(`https://day6scrooge.duckdns.org/api/challenge/${params.id}`)
          .then((response) => {
            setData((prevData) => ({
              ...prevData,
              authMethod: response.data.authMethod,
            }));
            setAuthImg(response.data.challengeExampleImageDtoList);

            console.log(authImg);
          })
          .catch((error) => {
            console.error("Error fetching data:", error);
          });
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

  const showModalHandler = () => setShowModal(true);
  const hideModalHandler = () => setShowModal(false);
  const submitAuthHandler = () => {
    formData.append("img", imgRef.current.files[0]);

    console.log(imgRef.current.files[0]);
    console.log(formData.images);
    console.log(globalToken);

    axios
      .post(
        `https://day6scrooge.duckdns.org/api/challenge/${params.id}/auth`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
            Authorization: globalToken,
          },
        }
      )
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  };

  return (
    <div className={styles.layout}>
      <div className={styles.img}>
        <Link className={styles.back} to="/challenge">
          <img src={backImg} alt=""></img>
        </Link>
        {authImg.length && (
          <img
            src={authImg[0].imgAddress}
            style={{ width: "100%", height: "100%", filter: "brightness(0.8)" }}
            alt=""
          />
        )}
        <div className={styles.title}>
          <p className={styles.day}>
            {data.startMonth}.{data.startDay}({data.startAt}) - {data.endMonth}.
            {data.endDay}({data.endAt})
          </p>
          {data.title}
          <p>#{data.period} #경험치 500+</p>
        </div>
      </div>
      <div className={styles.fight_container}>
        <div>대전 현황</div>
        <ProgressBar
          ally={data.teamOneSuccessCount + 1}
          enemy={data.teamZeroSuccessCount + 1}
        ></ProgressBar>
      </div>
      <hr></hr>
      <div className={styles.auth_condition}>
        <Chips selected={selectChip} setSelect={setSelectChip}>
          나의 인증 현황
        </Chips>
        <Chips selected={selectChip} setSelect={setSelectChip}>
          우리팀 인증 현황
        </Chips>
      </div>

      {selectChip === "나의 인증 현황" ? (
        <div>
          <AuthProgress per={60}></AuthProgress>
          <div className={styles.my_auth_container}>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>1</div>
              <div>8/3</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>2</div>
              <div>8/4</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>3</div>
              <div>8/5</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>4</div>
              <div>8/6</div>
              <div className={styles.fail}>실패</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
            <div className={styles.my_auth_middle}>
              <div className={styles.idx}>5</div>
              <div>8/7</div>
              <div className={styles.succes}>성공</div>
              <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            </div>
          </div>
        </div>
      ) : (
        <div>
          <AuthProgress per={60}></AuthProgress>
          <div className={styles.img_container}>
            <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
            <img src={`${process.env.PUBLIC_URL}/images/dummy.png`} alt="" />
          </div>
        </div>
      )}

      <div className={styles.foot}>
        <div className={styles.primary} onClick={showModalHandler}>
          인증하기
          <div className={styles.shadow}></div>
        </div>
      </div>

      {showModal && (
        <AuthModal onClose={hideModalHandler}>
          <div>
            <div
              style={{
                fontSize: "24px",
                marginTop: "1rem",
                paddingRight: "4vw",
              }}
            >
              인증 규정
            </div>
            <div className={styles.auth_img}>
              {authImg.map((e) => (
                <img src={e.imgAddress} alt=""></img>
              ))}
              {authImg.map((e) => (
                <img src={e.imgAddress} alt=""></img>
              ))}
            </div>
          </div>
          <div className={styles.body}>
            <div className={styles.body_title}>{data.authMethod}</div>
            <div className={styles.body_title}>꼭 알아주세요!</div>
            <div className={styles.detail}>
              <span>인증 빈도</span>
              매일
            </div>
            <div className={styles.detail}>
              <span>인증 가능 시간</span>
              00시 00분 ~ 23시 59분
            </div>
            <div className={styles.detail}>
              <span>하루 인증 횟수</span>
              1회
            </div>
          </div>

          <input
            style={{ display: "none" }}
            id="img"
            type="file"
            accept="image/*"
            ref={imgRef}
          />
          <label htmlFor="img" className={styles.loadImg}>
            사진 불러오기
          </label>

          <div className={styles.authImg} onClick={submitAuthHandler}>
            인증하기
          </div>
        </AuthModal>
      )}
    </div>
  );
};

export default ChallengeDetail;
