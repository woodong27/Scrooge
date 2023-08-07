import styles from "./Settings.module.css";
import BackGround from "../../components/BackGround";

import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";


const Settings = ({ onLogout }) => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogout = () => {
    onLogout();

    // 로그아웃: 리덕스 스토어에서 토큰 정보 지우기 
    dispatch({type: "SET_TOKEN_STRING", payload:""});

    // 로그아웃 후 리디렉션: "/Loading"
    navigate("/")
  };

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
    </BackGround>
  );
};

export default Settings;