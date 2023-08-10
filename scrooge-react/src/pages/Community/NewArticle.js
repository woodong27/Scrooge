import { useState } from "react";
import { useSelector } from "react-redux";

import { Link } from "react-router-dom";

import QuestHeader from "../../components/QuestHeader";
import styles from "./NewArticle.module.css";

const NewArticle = ({}) => {
  const formData = new FormData();
  const globalToken = useSelector((state) => state.globalToken);
  const [content, setContent] = useState("");

  const [selectedImage, setSelectedImage] = useState(null);

  const handleImageChange = (event) => {
    const selimg = event.target.files[0];
    setSelectedImage(selimg);
  };

  const handleSend = () => {
    formData.append("content", content);
    formData.append("img", selectedImage);

    const postData = {
      method: "POST",
      headers: {
        // "Content-Type": "multipart/form-data",
        Authorization: globalToken,
      },
      body: formData,
    };

    fetch(`https://day6scrooge.duckdns.org/api/community`, postData)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setContent("");
      })
      .catch((error) => {
        console.log(error);
      })
  };

  return (
    <div>
      <QuestHeader
        title={"스크루지 빌리지"}
        titleColor={"#5B911F"}
        color={"#A2D660"}
      />

      <div className={styles.box}>
        <img
          className={styles.file}
          src={`${process.env.PUBLIC_URL}/images/fileframe.png`}
          alt="파일"
        />
        <img
          className={styles.character}
          src={`https://storage.googleapis.com/scroogestorage/avatars/73-1.png`}
          alt="캐릭터"
        />
        <div className={styles.title}>
          오늘 하루 당신의 <br /> 짠내나는 소비는?!
        </div>
        <textarea
          className={styles.content}
          placeholder="부서 회식 개이득... "
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />
        <div className={styles.photo}>
          <label htmlFor="fileInput">
            <img
              src={`${process.env.PUBLIC_URL}/images/camera.svg`}
              alt="더보기"
            />
            <div className={styles.text}>사진</div>
          </label>
          <input
            id="fileInput"
            type="file"
            accept="image/*"
            style={{display: "none"}}
            onChange={handleImageChange}>
          </input>
        </div>

        <div className={styles.upload} onClick={handleSend}>
          게시하기
        </div>
      </div>
    </div>
  );
};

export default NewArticle;
