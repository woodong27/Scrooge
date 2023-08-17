import { useState } from "react";
import { useSelector } from "react-redux";

import { useNavigate } from "react-router-dom";

import QuestHeader from "../../components/QuestHeader";
import styles from "./NewArticle.module.css";
import Spinner from "../../components/Spinner";

const NewArticle = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const [content, setContent] = useState("");
  const navigate = useNavigate();

  const [selectedImage, setSelectedImage] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const formData = new FormData();
  const handleImageChange = (event) => {
    const selimg = event.target.files[0];
    setSelectedImage(selimg);
  };

  const handleSend = () => {
    if (content.length < 5) {
      const errorDiv = document.getElementById("errorcontent");
      errorDiv.style.display = "block";
      return;
    }
    setIsLoading(true);

    formData.append("content", content);
    formData.append("img", selectedImage);

    const postData = {
      method: "POST",
      headers: {
        Authorization: globalToken,
      },
      body: formData,
    };

    fetch(`https://day6scrooge.duckdns.org/api/community`, postData)
      .then((res) => {
        res.text();
        if (!res.ok) {
          throw new Error("글 등록 실패");
        }
      })
      .then((data) => {
        setContent("");
        setIsLoading(false);
        navigate("/community");
      })
      .catch((error) => {
        const errorDiv = document.getElementById("errorphoto");
        errorDiv.style.display = "block";
      });
  };

  return (
    <div>
      <QuestHeader
        title={"스크루지 빌리지"}
        titleColor={"#5B911F"}
        color={"#A2D660"}
        show={"true"}
      />

      <div className={styles.body}>
        <div className={styles.box}>
          <div className={styles.up}>
            <img
              className={styles.character}
              src={`${process.env.PUBLIC_URL}/images/scrooge_img.png`}
              alt="캐릭터"
            />
            <div className={styles.title}>
              오늘 하루 당신의 <br /> 짠내나는 소비는?!
            </div>
          </div>
          <div className={styles.content}>
            <textarea
              className={styles.textarea}
              rows={8}
              placeholder="본인의 절약을 뽐내보세요~"
              value={content}
              onChange={(e) => setContent(e.target.value)}
            />
          </div>
        </div>
        <div className={styles.underBtns}>
          <label htmlFor="fileInput" className={styles.photo}>
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
            style={{ display: "none" }}
            onChange={handleImageChange}
          ></input>

          <div className={styles.upload} onClick={handleSend}>
            게시하기
          </div>
        </div>
        <div id="errorcontent" className={styles.error}>
          본문이 너무 짧습니다.
        </div>
        <div id="errorphoto" className={styles.error}>
          사진을 첨부해주세요.
        </div>
      </div>

      {isLoading && <Spinner isGreen></Spinner>}
    </div>
  );
};

export default NewArticle;
