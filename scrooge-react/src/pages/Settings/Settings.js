import styles from "./Settings.module.css";
import BackGround from "../../components/BackGround";
import SettingModal from "../../components/UI/SettingModal";
import Notification from "../../pages/Notification";

import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";

const Settings = ({ onLogout }) => {
  const globalToken = useSelector((state) => state.globalToken);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [showLogoutModal, setShowLogoutModal] = useState(false);
  const [showWithdrawModal, setShowWithdrawModal] = useState(false);

  const [alarmModal, setAlarmModal] = useState(false);

  // BGM 관련
  const [isSoundOn, setIsSoundOn] = useState(true)

  useEffect(() => {
    const storedSoundStatus = localStorage.getItem("isSoundOn");
    if (storedSoundStatus !== null) {
      setIsSoundOn(storedSoundStatus === "true");
    }
    else {
      setIsSoundOn(true);
      localStorage.setItem("isSoundOn", "true");
    }
  }, []);

  console.log(isSoundOn)

  const handleLogoutModal = () => {
    setShowLogoutModal(!showLogoutModal);
  };

  const handleWithdrawModal = () => {
    setShowWithdrawModal(!showWithdrawModal);
  };

  const handleAlarmOpen = () => {
    setAlarmModal(true);
  };
  const handleAlarmClose = () => {
    setAlarmModal(false);
  };

  const handleSoundToggle = () => {
    // Android와 통신하여 소리 상태 변경하기
    if(window.AndroidSound) {
      window.AndroidSound.sendSoundToggleToAndroid(isSoundOn);
    }

    const newSoundState = !isSoundOn;
    setIsSoundOn(newSoundState);
    localStorage.setItem("isSoundOn", newSoundState.toString());
  }


  const confirmLogout = () => {
    onLogout();
    dispatch({ type: "SET_TOKEN_STRING", payload: "" }); // 로그아웃: 리덕스 스토어에서 토큰 정보 지우기
    navigate("/"); // 로그아웃 후 리디렉션: "/"
  };



  const confirmWithdraw = async () => {
    try {
      const resp = await fetch(
        `https://day6scrooge.duckdns.org/api/member/delete`,
        {
          method: "DELETE",
          headers: {
            Authorization: globalToken,
          },
        }
      );

      if (resp.status === 204) {
        // 회원탈퇴 성공 시 처리
        confirmLogout();
        console.log("회원탈퇴 성공");
      } else {
        console.log("회원탈퇴 실패");
      }
    } catch (error) {
      console.error("API 호출 오류:", error);
    }
  };

  return (
    <BackGround>
      <div>설정페이지 헤더</div>
      <div className={styles.settingContainer}>
        <div className={styles.settingContent}>
          <div className={styles.infoHeader}>계정설정</div>
          <div className={styles.infoContent}>
            <Link to ="/nicknameChange">
              <div>프로필 변경</div>
            </Link>
            <Link to="/passwordChange">
              <div>비밀번호 변경</div>
            </Link>
          </div>

          <div className={styles.infoHeader}>추가설정</div>
          <div className={styles.infoContent}>
            <div onClick={handleAlarmOpen}>정산 알림 보내기</div>
            <div onClick={handleSoundToggle}>
             {isSoundOn ? "BGM 끄기" : "BGM 켜기"}
            </div>
          </div>

          <div className={styles.infoHeader}>스크루지</div>
          <div className={styles.infoContent}>
            <div>공지사항</div>
            <div>스크루지 정보</div>
            <div>고객센터</div>
          </div>

          <div className={styles.infoHeader}>ETC</div>
          <div className={styles.infoContent}>
            <div onClick={handleLogoutModal}>로그아웃</div>
            <div onClick={handleWithdrawModal}>회원탈퇴</div>
          </div>
        </div>
      </div>

      {showLogoutModal && (
        <SettingModal
          message="로그아웃 하시겠습니까?"
          onConfirm={confirmLogout}
          onCancel={handleLogoutModal}
        />
      )}

      {showWithdrawModal && (
        <SettingModal
          message="탈퇴를 진행하시겠습니까?"
          onConfirm={confirmWithdraw}
          onCancel={handleWithdrawModal}
        />
      )}

      {alarmModal && <Notification handleAlarmClose={handleAlarmClose} />}
    </BackGround>
  );
};

export default Settings;
