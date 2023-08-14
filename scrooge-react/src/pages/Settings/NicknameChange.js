import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import styles from "./NicknameChange.module.css"

const NicknameChange = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const navigate = useNavigate();
  const [currentNickname, setCurrentNickname] = useState("");
  const [newNickname, setNewNickname] = useState("");
  const [isNicknameChanged, setIsNicknameChanged] = useState(false);
 
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
        setCurrentNickname(data.nickname);
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

    fetch("https://day6scrooge.duckdns.org/api/member/change-nickname", postData)
      .then((resp) => resp.json())
      .then((data) => {
        setIsNicknameChanged(true);
      })
      .catch((error) => console.log(error));
  };

  return (
    <div>
      <button onClick={() => navigate(-1)}>이전</button>
      <h1>닉네임 변경</h1>
      <input
        type="text"
        placeholder={currentNickname}
        value={newNickname}
        onChange={(e) => setNewNickname(e.target.value)}
      />
      <button onClick={handleNicknameChange}>변경</button>
      {isNicknameChanged && <p>닉네임이 변경되었습니다.</p>}
    </div>
  );
};

export default NicknameChange;