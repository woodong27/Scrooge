import { useState } from "react";
import { UseSelector, useSelector } from "react-redux";
import styles from "./PasswordChange.module.css"


const PasswordChange = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleChangePassword = async () => {
    try{
      const obj = {
        currentPassword,
        newPassword
      };
      const postDate = {
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
      비번바꾸겠어요
    </div>
  );
};

export default PasswordChange;