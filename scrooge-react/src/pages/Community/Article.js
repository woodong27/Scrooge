import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

import Card from "../../components/UI/Card";
import styles from "./Article.module.css";

const Article = ({
  status,
  content,
  id,
  memberId,
  memberNickname,
  imgAdress,
  memberAvatarAddress,
}) => {
  const globalToken = useSelector((state) => state.globalToken);
  const myId = useSelector((state) => state.memberId);

  const [showContent, setContent] = useState("");
  //게시글에 좋아요 싫어요 개수
  const [goodCnt, setGoodCnt] = useState(0);
  const [badCnt, setBadCnt] = useState(0);
  //유저가 게시글에 좋아요 싫어요 눌렀는지
  const [good, setGood] = useState(false);
  const [bad, setBad] = useState(false);

  useEffect(() => {
    if (content.length <= 36) {
      setContent(content);
    } else {
      setContent(content.slice(0, 34) + "...");
    }

    //좋아요 싫어요 개수
    fetch(`https://day6scrooge.duckdns.org/api/community/${id}/review-count`)
      .then((resp) => resp.json())
      .then((data) => {
        setGoodCnt(data.goodCount);
        setBadCnt(data.badCount);
      })
      .catch((error) => console.log(error));

    //좋아요 여부
    const goodCntData = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    fetch(
      `https://day6scrooge.duckdns.org/api/community/${id}/good`,
      goodCntData
    )
      .then((res) => res.json())
      .then((data) => {
        setGood(data.good);
      });

    const BadCntData = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    //싫어요 여부
    fetch(`https://day6scrooge.duckdns.org/api/community/${id}/bad`, BadCntData)
      .then((res) => res.json())
      .then((data) => {
        setBad(data.bad);
      });
  }, [status]);

  const handleGood = () => {
    if (bad) {
      handleBadCancle();
    }
    const goodData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };

    fetch(`https://day6scrooge.duckdns.org/api/community/${id}/good`, goodData)
      .then((res) => res.text())
      .then((data) => {
        if (data === "좋아요 완료") {
          setGoodCnt((prev) => prev + 1);
          setGood(true);
        }
      });
  };
  const handleGoodCancle = () => {
    const goodData = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };

    fetch(`https://day6scrooge.duckdns.org/api/community/${id}/good`, goodData)
      .then((res) => res.text())
      .then((data) => {
        setGoodCnt((prev) => prev - 1);
        setGood(false);
      });
  };
  const handleBad = () => {
    if (good) {
      handleGoodCancle();
    }
    const badData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };

    fetch(`https://day6scrooge.duckdns.org/api/community/${id}/bad`, badData)
      .then((res) => res.text())
      .then((data) => {
        if (data === "싫어요 성공") {
          setBadCnt((prev) => prev + 1);
          setBad(true);
        }
      });
  };
  const handleBadCancle = () => {
    const badData = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };

    fetch(`https://day6scrooge.duckdns.org/api/community/${id}/bad`, badData)
      .then((res) => res.text())
      .then((data) => {
        setBadCnt((prev) => prev - 1);
        setBad(false);
      });
  };

  return (
    <div className={styles.box}>
      <Card>
        {memberId === myId ? (
          <div className={styles.authorInfo}>
            <img
              className={styles.character}
              src={`https://storage.googleapis.com/scroogestorage/avatars/${memberAvatarAddress}-1.png`}
              alt="캐릭터"
            />
            <div className={styles.author}>{memberNickname}</div>
          </div>
        ) : (
          <Link to={`/profile/${memberId}`}>
            <div className={styles.authorInfo}>
              <img
                className={styles.character}
                src={`https://storage.googleapis.com/scroogestorage/avatars/${memberAvatarAddress}-1.png`}
                alt="캐릭터"
              />
              <div className={styles.author}>{memberNickname}</div>
            </div>
          </Link>
        )}

        <Link to={`/community/${id}`}>
          <div className={styles.detail}>
            <div className={styles.pictureFrame}>
              <img className={styles.picture} src={`${imgAdress}`} alt="사진" />
            </div>
            <div className={styles.content}>{showContent}</div>
          </div>
        </Link>
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
            <div className={styles.cnt}>{goodCnt}</div>
            {bad ? (
              <button onClick={handleBadCancle} className={styles.bademoji}>
                <img
                  src={`${process.env.PUBLIC_URL}/images/downColor.png`}
                  alt="야유"
                />
              </button>
            ) : (
              <button onClick={handleBad} className={styles.bademoji}>
                <img
                  src={`${process.env.PUBLIC_URL}/images/down.png`}
                  alt="야유"
                />
              </button>
            )}

            <div className={styles.cnt}>{badCnt}</div>
          </div>
          <img
            className={styles.comment}
            src={`${process.env.PUBLIC_URL}/images/comment.svg`}
            alt="댓글"
          />
        </div>
      </Card>
    </div>
  );
};

export default Article;
