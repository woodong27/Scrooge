import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

import styles from "./NicknameChange.module.css";
import BackGround from "../../components/BackGround";
import back from "../../assets/blackback.png";

const NicknameChange = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const navigate = useNavigate();
  const [currentNickname, setCurrentNickname] = useState("");
  const [newNickname, setNewNickname] = useState("");
  const [isNicknameChanged, setIsNicknameChanged] = useState(false);
  const [currentMsg, setCurrentMsg] = useState("");
  const [msg, setMsg] = useState("");
  const [isMsgChanged, setIsMsgChanged] = useState(false);

  useEffect(() => {
    const postData = {
      method: "GET",
      headers: {
        Authorization: globalToken,
      },
    };

    fetch("https://day6scrooge.duckdns.org/api/member/info", postData)
      .then((resp) => resp.json())
      .then((data) => {
        console.log(data);
        setCurrentNickname(data.nickname);
        setCurrentMsg(data.message);
      })
      .catch((error) => console.log(error));
  }, [globalToken]);

  const handleNicknameChange = () => {
    const postData = {
      method: "PUT",
      headers: {
        Authorization: globalToken,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nickname: newNickname,
      }),
    };

    fetch(
      "https://day6scrooge.duckdns.org/api/member/change-nickname",
      postData
    )
      .then((resp) => resp.json())
      .then((data) => {
        setIsNicknameChanged(true);
      })
      .catch((error) => console.log(error));
  };

  const handleMsgChange = () => {
    const postData = {
      method: "PUT",
      headers: {
        Authorization: globalToken,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        message: msg,
      }),
    };

    console.log(msg);

    fetch("https://day6scrooge.duckdns.org/api/member/message", postData)
      .then((resp) => resp.json())
      .then(() => setIsMsgChanged(true))
      .catch((error) => console.log(error));
  };

  return (
    <BackGround>
      <div className={styles.settingContainer}>
        <div className={styles.settingContent}>
          <div className={styles.profile}>
            <img src={back} onClick={() => navigate(-1)} alt=""></img>
            <h1>프로필 변경</h1>
          </div>

          <div className={styles.body}>
            <h2>닉네임 변경</h2>
            <div className={styles.inputBody}>
              <input
                type="text"
                placeholder={currentNickname}
                value={newNickname}
                onChange={(e) => setNewNickname(e.target.value)}
              />
              <button onClick={handleNicknameChange}>변경</button>
            </div>
            {isNicknameChanged && <p>닉네임이 변경되었습니다.</p>}
          </div>

          <div className={styles.body}>
            <h2>상태 메시지 변경</h2>
            <div className={styles.inputBody}>
              <input
                type="text"
                placeholder={currentMsg}
                value={msg}
                onChange={(e) => setMsg(e.target.value)}
              />
              <button onClick={handleMsgChange}>변경</button>
            </div>
            {isMsgChanged && <p>상태메시지가 변경되었습니다.</p>}
          </div>
        </div>
      </div>
    </BackGround>
  );
};

export default NicknameChange;
