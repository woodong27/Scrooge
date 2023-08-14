import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import styles from "./MyPageProfile.module.css";
import MyPageExpBar from "./MyPageExpBar";
import Report from "./Report";
import Item from "./Item";

const MyPageProfile = () => {
  const globalToken = useSelector((state) => state.globalToken);

  const [data, setData] = useState([]); 
  const [showItemList, setShowItemList] = useState(false);
  const [exp, setExp] = useState(50);
  const maxExp = 100;

  // 경험치 증가
  const increaseExp = () => {
    if (exp === maxExp) {
      setExp(0);
      setLevelId((prevLevelId) => prevLevelId + 1);
    } else {
      setExp((prevExp) => 
        prevExp < maxExp ? prevExp + 50 : prevExp
      );
    };
  }
  
  const [levelId, setLevelId] = useState(1);

  const handleEditBtn = () => {
    setShowItemList((prevState) => !prevState);
  };

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
        setData(data);
        console.log("레벨업 데이터",data)
        setLevelId(data.levelId)
      })
      .catch((error) => console.log(error));
  }, [globalToken]);


  return(
    <div className={styles["profile-container"]}>
      {data && data.levelId && data.mainAvatar.id &&(       
        <div className={styles["profile-content"]}>
          <div className={styles.profileImage}>
            <img 
              src={`https://storage.googleapis.com/scroogestorage/avatars/${data.mainAvatar.id}-1.png`} 
              alt="프로필 사진"/>
          </div>
          <ul className={styles["profile-info"]}>
            <li>Lv.{data.levelId}</li>
            <li>{data.nickname}</li>
            <li className={styles["items-info"]}>
              <div>
                <img src ={`${process.env.PUBLIC_URL}/images/profile-icon.png`} alt="캐릭터 아이콘"/>
                <span>12</span>
              </div>
              <div>
                <img src ={`${process.env.PUBLIC_URL}/images/badge-icon.png`} alt="뱃지 아이콘"/>
                <span>6</span>
              </div>
            </li>
          </ul>
        </div>
      )}
      <MyPageExpBar exp={exp} maxExp={maxExp} />
      <button onClick={increaseExp}>경험치 획득</button> 

      <div
        className={`${styles["edit-btn"]} ${showItemList ? "active" : ""}`}
        onClick={handleEditBtn}
      >
        {showItemList ? "소비 리포트 보러 가기" : "캐릭터 뽑으러 가기"}
      </div>
      {showItemList ? <Item /> : <Report />}
    </div>
  );
};

export default MyPageProfile;