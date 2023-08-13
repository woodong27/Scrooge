import { useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import styles from "./PasswordChange.module.css"


const PasswordChange = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const navigate = useNavigate();
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleChangePassword = async () => {
    try{
      const obj = {
        currentPassword,
        newPassword
      };
      const postData = {
        method: "PUT",
        headers: {
          "Content-Type" : "application/json",
          Authorization: globalToken
        },
        body: JSON.stringify(obj)
      };

      const resp = await fetch(`https://day6scrooge.duckdns.org/api/member/change-password`,postData);

      if (resp.status === 200) {
        const data = await resp.json();
        setMessage("비밀번호 변경 완료");
        navigate(-1);
      } else {
        setMessage("비밀번호 변경 실패");
      }
    } catch(error) {
      console.error("API 호출 오류:", error);
      setMessage("다시 시도해주세요")
    }
  };

  return(   
    <div className={styles.container}>
      <button onClick={() => navigate(-1)}>이전</button>
      <h2>비밀번호 변경</h2>
      <div>
        <p>현재 비밀번호</p>
        <input
          className={styles.pwInput}
          type="password"
          placeholder="현재 비밀번호"
          value={currentPassword}
          onChange = {(e) => setCurrentPassword(e.target.value)} 
        />
      </div>
      <div>
        <p>새 비밀번호</p>
        <input
          className={styles.pwInput} 
          type="password"
          placeholder="새 비밀번호"
          value={newPassword}
          onChange={(e) => setNewPassword(e.target.value)}
        />
      </div>
      <button onClick={handleChangePassword}>비밀번호 변경</button>
      {message && <p className={styles.message}>{message}</p>}
    </div>
  );
};

export default PasswordChange;