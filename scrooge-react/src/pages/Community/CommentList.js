import React from "react";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import Comment from "./Comment";
import styles from "./CommentList.module.css";

const CommentList = React.memo(({ id, comments }) => {
  const globalToken = useSelector((state) => state.globalToken);
  const [data, setData] = useState([]);

  useEffect(() => {
    setData(comments);
  }, [comments]);

  const commentDelete = (targetId) => {
    const deleteData = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };

    fetch(
      `https://day6scrooge.duckdns.org/api/community/comment/${targetId}`,
      deleteData
    ).then((res) => {
      if (res.status === 204) {
        setData(data.filter((it) => it.id !== targetId));
        console.log(data);
      }
    });
  };

  return (
    <div className={styles.container}>
      {data.map((it) => (
        <Comment key={it.id} {...it} commentDelete={commentDelete} />
      ))}
    </div>
  );
});

export default CommentList;
