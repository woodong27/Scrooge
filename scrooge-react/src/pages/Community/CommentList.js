import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import Comment from "./Comment";
import styles from "./CommentList.module.css";

const CommentList = ({ id }) => {
  const globalToken = useSelector((state) => state.globalToken);
  const [data, setData] = useState([]);

  useEffect(() => {
    fetch(`http://day6scrooge.duckdns.org:8081/community/${id}/comment`)
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
        console.log(data);
      })
      .catch((error) => console.log(error));
  }, []);

  useEffect(() => {}, []);
  const commentDelete = (targetId) => {
    const deleteData = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };

    console.log(targetId);
    console.log(data);
    fetch(
      `http://day6scrooge.duckdns.org:8081/community/comment/${targetId}`,
      deleteData
    ).then((res) => {
      if (res.status === 204) {
        setData(data.filter((it) => it.id !== targetId));
        console.log(data);
      }
    });
  };

  return (
    <div>
      {data.map((it) => (
        <Comment key={it.id} {...it} commentDelete={commentDelete} />
      ))}
    </div>
  );
};

export default CommentList;
