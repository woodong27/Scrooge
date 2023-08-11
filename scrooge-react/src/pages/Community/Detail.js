import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { useParams, useNavigate } from "react-router-dom";

import styles from "./Detail.module.css";
import CommentList from "./CommentList";
import QuestHeader from "../../components/QuestHeader";

const Detail = () => {
  const navigate = useNavigate();
  const params = useParams();
  const globalToken = useSelector((state) => state.globalToken);
  const memberId = useSelector((state) => state.memberId);

  const [data, setData] = useState(null); //게시글 상세조회
  //게시글에 좋아요 싫어요 개수
  const [goodCnt, setGoodCnt] = useState(0);
  const [badCnt, setBadCnt] = useState(0);
  //유저가 게시글에 좋아요 싫어요 눌렀는지
  const [good, setGood] = useState(false);
  const [bad, setBad] = useState(false);
  const [comments, setComments] = useState([]);
  const [comment, setComment] = useState("");

  const [isOption, setIsOption] = useState(false);
  const [isEdit, setIsEdit] = useState(false);
  const [content, setContent] = useState("");

  //댓글
  useEffect(() => {
    fetch(`https://day6scrooge.duckdns.org/api/community/${params.id}/comment`)
      .then((resp) => resp.json())
      .then((data) => {
        setComments(data);
      })
      .catch((error) => console.log(error));
  }, []);

  const handleOpen = () => {
    setIsOption(true);
  };

  const handleEdit = () => {
    setIsEdit(true);
  };

  const handleCancle = () => {
    setIsOption(false);
    setIsEdit(false);
    setContent(data.content);
  };
  const handleSend = () => {
    const obj = {
      content: content,
    };
    const postData = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        // Authorization: globalToken,
      },
      body: JSON.stringify(obj),
    };

    fetch(
      `https://day6scrooge.duckdns.org/api/community/${params.id}`,
      postData
    )
      .then((res) => res.text())
      .then((data) => {
        navigate(`/community?edit=${params.id}`);
      });
    setContent("");
  };

  const handleDelete = () => {
    const postData = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        // Authorization: globalToken,
      },
    };

    fetch(
      `https://day6scrooge.duckdns.org/api/community/${params.id}`,
      postData
    )
      .then((res) => res.text())
      .then((data) => {
        navigate(`/community?delete=${params.id}`);
      });
  };

  const handleCommentSend = () => {
    if (comment < 0) {
      return;
    }
    const obj = {
      id: params.id,
      content: comment,
    };
    const postData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
      body: JSON.stringify(obj),
    };

    fetch(
      `https://day6scrooge.duckdns.org/api/community/${params.id}/comment`,
      postData
    )
      .then((res) => res.json())
      .then((data) => {
        const newComment = {
          id: data.id,
          content: data.content,
          memberId: data.memberId,
          memberNickname: data.memberNickname,
          memberAvatarAddress: data.memberAvatarAddress,
        };
        setComments([newComment, ...comments]);
        setComment("");
        console.log(comments);
      });
  };

  //댓글 끝

  useEffect(() => {
    fetch(`https://day6scrooge.duckdns.org/api/community/${params.id}`)
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
        setContent(data.content);
      })
      .catch((error) => console.log(error));

    //좋아요 싫어요 개수
    fetch(
      `https://day6scrooge.duckdns.org/api/community/${params.id}/review-count`
    )
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
      `https://day6scrooge.duckdns.org/api/community/${params.id}/good`,
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
      `https://day6scrooge.duckdns.org/api/community/${params.id}/bad`,
      BadCntData
    )
      .then((res) => res.json())
      .then((data) => {
        setBad(data.bad);
      });
  }, []);

  const handleCommentChange = (event) => {
    setComment(event.target.value);
  };

  const handleGood = () => {
    const goodData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };

    fetch(
      `https://day6scrooge.duckdns.org/api/community/${params.id}/good`,
      goodData
    )
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

    fetch(
      `https://day6scrooge.duckdns.org/api/community/${params.id}/good`,
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
      `https://day6scrooge.duckdns.org/api/community/${params.id}/bad`,
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
      `https://day6scrooge.duckdns.org/api/community/${params.id}/bad`,
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
      <QuestHeader
        title={"스크루지 빌리지"}
        titleColor={"#5B911F"}
        color={"#A2D660"}
        show={"true"}
      ></QuestHeader>
      {data ? (
        <div className={styles.frame}>
          <div className={styles.authorInfo}>
            <img
              className={styles.character}
              src={`https://storage.googleapis.com/scroogestorage/avatars/${data.memberAvatarAddress}-1.png`}
              alt="캐릭터"
            />
            <div>
              <div className={styles.author}>{data.memberNickname}</div>
              <div className={styles.line}>
                <div className={styles.createdAt}>
                  {data.createdAt.split("T")[0]}{" "}
                  {data.createdAt.split("T")[1].split(".")[0]}
                </div>
                {memberId === data.memberId ? (
                  <div>
                    {isOption ? (
                      <>
                        {isEdit ? (
                          <div className={styles.btns}>
                            <div className={styles.edit} onClick={handleCancle}>
                              취소
                            </div>
                            <div className={styles.delete} onClick={handleSend}>
                              완료
                            </div>
                          </div>
                        ) : (
                          <div className={styles.btns}>
                            <div className={styles.edit} onClick={handleEdit}>
                              수정
                            </div>
                            <div
                              className={styles.delete}
                              onClick={handleDelete}
                            >
                              삭제
                            </div>
                          </div>
                        )}
                      </>
                    ) : (
                      <div onClick={handleOpen} className={styles.option}>
                        <img
                          src={`${process.env.PUBLIC_URL}/images/option.png`}
                          alt="더보기"
                        />
                      </div>
                    )}
                  </div>
                ) : (
                  ""
                )}
              </div>
            </div>
          </div>
          <div className={styles.detail}>
            {isEdit ? (
              <textarea
                className={styles.editContent}
                value={content}
                onChange={(e) => setContent(e.target.value)}
              />
            ) : (
              <>
                <img
                  className={styles.picture}
                  src={`${data.imgAdress}`}
                  alt="사진"
                />
                <div className={styles.content}>{data.content}</div>
              </>
            )}
          </div>
          {isEdit ? (
            ""
          ) : (
            <>
              <div className={styles.reaction}>
                <div className={styles.left}>
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
                </div>
                <div className={styles.right}>
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
              </div>
              <CommentList id={data.id} comments={comments} />
            </>
          )}
        </div>
      ) : (
        <p>Loading...</p>
      )}
      {isEdit ? (
        ""
      ) : (
        <div className={styles.addFrame}>
          <input
            className={styles.add}
            type="text"
            value={comment}
            onChange={handleCommentChange}
            placeholder="새로운 댓글을 작성해주세요."
          />
          <button className={styles.sendBtn} onClick={handleCommentSend}>
            전송
          </button>
        </div>
      )}
    </div>
  );
};

export default Detail;
