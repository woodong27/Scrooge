import { useState } from "react";

import { Link } from "react-router-dom";

import QuestHeader from "../../components/QuestHeader";
import styles from "./NewArticle.module.css";

const NewArticle = ({}) => {
  const [content, setContent] = useState("");

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
          src={`${process.env.PUBLIC_URL}/images/fileFrame.png`}
          alt="퀘스트"
        />
        <img
          className={styles.character}
          src={`${process.env.PUBLIC_URL}/Character/0.png`}
          alt="퀘스트"
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
          <img
            src={`${process.env.PUBLIC_URL}/images/camera.svg`}
            alt="더보기"
          />
          <div className={styles.text}>사진</div>
        </div>

        <div className={styles.upload}>게시하기</div>
      </div>
    </div>
  );
};

export default NewArticle;
