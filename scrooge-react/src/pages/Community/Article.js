import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

import Card from "../../components/UI/Card";
import styles from "./Article.module.css";

const Article = (props) => {
  // const navigate = useNavigate();
  // const handleDetail = () => {
  //   console.log(props.id);
  //   navigate(`/community/${props.id}`);
  // };

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

    //좋아요 싫어요 개수
    fetch(
      `http://day6scrooge.duckdns.org:8081/community/${props.id}/review-count`
    )
      .then((resp) => resp.json())
      .then((data) => {
        setGoodCnt(data.goodCount);
        setBadCnt(data.badCount);
      })
      .catch((error) => console.log(error));
  }, []);

  //좋아요 여부
  const goodCntData = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: globalToken,
    },
  };
  fetch(
    `http://day6scrooge.duckdns.org:8081/community/${props.id}/good`,
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
  //싫어요 조회
  fetch(
    `http://day6scrooge.duckdns.org:8081/community/${props.id}/bad`,
    BadCntData
  )
    .then((res) => res.json())
    .then((data) => {
      setBad(data.bad);
    });

  const handleGood = () => {
    const goodData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };

    fetch(
      `http://day6scrooge.duckdns.org:8081/community/${props.id}/good`,
      goodData
    )
      .then((res) => res.text())
      .then((data) => {
        console.log(data);
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

    fetch(
      `http://day6scrooge.duckdns.org:8081/community/${props.id}/good`,
      goodData
    )
      .then((res) => res.text())
      .then((data) => {
        setGoodCnt((prev) => prev - 1);
        setGood(false);
      });
  };
  const handleBad = () => {
    const badData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };

    fetch(
      `http://day6scrooge.duckdns.org:8081/community/${props.id}/bad`,
      badData
    )
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

    fetch(
      `http://day6scrooge.duckdns.org:8081/community/${props.id}/bad`,
      badData
    )
      .then((res) => res.text())
      .then((data) => {
        setBadCnt((prev) => prev - 1);
        setBad(false);
      });
  };

  return (
    <div className={styles.box}>
      <Card height={330}>
        <div className={styles.authorInfo}>
          <img
            className={styles.character}
            // memberAvatarAddress
            src={`${process.env.PUBLIC_URL}/Character/0.png`}
            alt="캐릭터"
          />
          <div className={styles.author}>{props.memberNickname}</div>
        </div>
        <Link to={`/community/${props.id}`}>
          <div className={styles.detail}>
            <img
              className={styles.picture}
              // imgAddress
              src={`${process.env.PUBLIC_URL}/images/dummy.png`}
              alt="사진"
            />
            <div className={styles.content}>
              {showContent} <button></button>
            </div>
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
