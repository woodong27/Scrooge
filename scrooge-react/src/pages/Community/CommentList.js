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

  const commentDelete = (targetId) => {
    const deleteData = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };

    fetch(
      `http://day6scrooge.duckdns.org:8081/community/${targetId}/comment`,
      deleteData
    )
      .then((res) => res.text())
      .then((resdata) => {
        setData(data.filter((it) => it.id !== targetId));
      });
  };

  return (
    <div>
      {data.map((it, index) => (
        <Comment key={index} {...it} id={id} commentDelete={commentDelete} />
      ))}
    </div>
  );
};

export default CommentList;
