import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import styles from "./MyPageProfile.module.css";
import ProgressBar from "../Challenge/ProgressBar"; //Exp 바로 
import Report from "./Report";
import Item from "./Item";

const MyPageProfile = () => {
  const globalToken = useSelector((state) => state.globalToken);

  const [data, setData] = useState([]); 
  const [showItemList, setShowItemList] = useState(false);
  
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
      })
      .catch((error) => console.log(error));
  }, []);


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

      <ProgressBar />
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