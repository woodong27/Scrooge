import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { useHistory } from "react-router-dom";

import TodayCard from "../../components/UI/TodayCard";
import styles from "./Article.module.css";

const Article = (props) => {
  const history = useHistory();
  const handleDetail = () => {
    // 게시글 상세 페이지로 이동하면서 상태를 전달
    const detailArticle = props;
    history.push(`/community/${props.id}`, detailArticle);
  };

  const globalToken = useSelector((state) => state.globalToken);

  const [showContent, setContent] = useState(props.content);
  //게시글에 좋아요 싫어요 개수
  const [goodCnt, setGoodCnt] = useState(0);
  const [badCnt, setBadCnt] = useState(0);
  //유저가 게시글에 좋아요 싫어요 눌렀는지
  const [good, setGood] = useState(false);
  const [bad, setBad] = useState(false);

  useEffect(() => {
    if (showContent.length <= 40) {
      setContent(showContent);
    } else {
      setContent(showContent.slice(0, 37) + "...");
    }

    //좋아요 개수
    fetch(
      `http://day6scrooge.duckdns.org:8081/community/${props.id}/good-count`
    )
      .then((resp) => resp.json())
      .then((data) => {
        setGoodCnt(data.goodCount);
      })
      .catch((error) => console.log(error));
    //좋아요 여부
    const goodCntData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    fetch(
      `http://day6scrooge.duckdns.org:8081/community/${props.id}/good-count`,
      goodCntData
    )
      .then((res) => res.json())
      .then((data) => {
        setGood(data.isGood);
      });

    //싫어요 개수
    fetch(`http://day6scrooge.duckdns.org:8081/community/${props.id}/bad-count`)
      .then((resp) => resp.json())
      .then((data) => {
        setBadCnt(data.goodCount);
      })
      .catch((error) => console.log(error));

    const BadCntData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    //싫어요 조회
    fetch(
      `http://day6scrooge.duckdns.org:8081/community/${props.id}/bad-count`,
      BadCntData
    )
      .then((res) => res.json())
      .then((data) => {
        setBad(data.isGood);
      });
  }, []);

  const handleGood = () => {
    setGoodCnt((prev) => prev + 1);
  };
  const handleGoodCancle = () => {
    setGoodCnt((prev) => prev - 1);
  };
  const handleBad = () => {
    setBadCnt((prev) => prev + 1);
  };
  const handleBadCancle = () => {
    setBadCnt((prev) => prev - 1);
  };

  return (
    <div className={styles.box}>
      <TodayCard height={330}>
        <div className={styles.authorInfo}>
          <img
            className={styles.character}
            // memberAvatarAddress
            src={`${process.env.PUBLIC_URL}/Character/0.png`}
            alt="캐릭터"
          />
          <div className={styles.author}>{props.memberNickname}</div>
        </div>
        <div className={styles.detail} onClick={handleDetail()}>
          <img
            className={styles.picture}
            // imgAddress
            src={`${process.env.PUBLIC_URL}/images/dummy.png`}
            alt="사진"
          />
          <div className={styles.content}>{showContent}</div>
        </div>
        <div className={styles.line}>
          <div className={styles.reaction}>
            {good ? (
              <button onClick={handleGoodCancle} className={styles.emoji}>
                <img
                  src={`${process.env.PUBLIC_URL}/images/upColor.png`}
                  alt="환호"
                />
              </button>
            ) : (
              <button onClick={handleGood} className={styles.emoji}>
                <img
                  src={`${process.env.PUBLIC_URL}/images/up.png`}
                  alt="환호"
                />
              </button>
            )}
            <div className={styles.cnt}>{13}</div>
            {bad ? (
              <button onClick={handleBadCancle} className={styles.emoji}>
                <img
                  src={`${process.env.PUBLIC_URL}/images/downColor.png`}
                  alt="야유"
                />
              </button>
            ) : (
              <button onClick={handleBad} className={styles.emoji}>
                <img
                  src={`${process.env.PUBLIC_URL}/images/down.png`}
                  alt="야유"
                />
              </button>
            )}

            <div className={styles.cnt}>{4}</div>
          </div>
          <img
            className={styles.comment}
            src={`${process.env.PUBLIC_URL}/images/comment.svg`}
            alt="댓글"
            onClick={handleDetail}
          />
        </div>
      </TodayCard>
    </div>
  );
};

export default Article;
