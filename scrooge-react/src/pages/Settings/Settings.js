import styles from "./Settings.module.css";
import BackGround from "../../components/BackGround";

import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";


const Settings = ({ onLogout }) => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [showModal, setShowModal] = useState(false);

  const handleModal = () => {
    setShowModal(!showModal);
  };

  const handleLogout = () => {
    handleModal();
  };

  const confirmLogout = () => {
    onLogout();
    dispatch({type: "SET_TOKEN_STRING", payload:""}); // 로그아웃: 리덕스 스토어에서 토큰 정보 지우기 
    navigate("/"); // 로그아웃 후 리디렉션: "/"
  }



  return(
    <BackGround>
      <div>설정페이지 헤더</div>
      <div className={styles.settingContainer}>
        <div className={styles.settingContent}>
          <div className={styles.infoHeader}>계정설정</div>
          <div className={styles.infoContent}>
            <div>프로필 변경</div>
            <div>비밀번호 변경</div>
          </div>

          <div className={styles.infoHeader}>알림설정</div>
          <div className={styles.infoContent}>
            <div>커뮤니티 댓글</div>
            <div>챌린지 댓글</div>
            <div>새로운 챌린지</div>
          </div>

          <div className={styles.infoHeader}>스크루지</div>
          <div className={styles.infoContent}>
            <div>공지사항</div>
            <div>스크루지 정보</div>
            <div>고객센터</div>
          </div>

          <div className={styles.infoHeader}>ETC</div>
          <div className={styles.infoContent}>
            <div onClick={handleLogout}>로그아웃</div>
            <div>회원탈퇴</div>
          </div>


        </div>
      </div>
      
      {showModal && (
        <div className={styles.modalContainer}>
          <div className={styles.modal}>
            <p>로그아웃 하시겠습니까?</p>
            <div className={styles.modalBtn}>
              <button onClick={confirmLogout}>확인</button>
              <button onClick={handleModal}>취소</button>
            </div>
          </div>
        </div>
      )}
    </BackGround>
  );
};

export default Settings;