import { useEffect, useState } from "react";
import Comment from "./Comment";
import styles from "./CommentList.module.css";

const CommentList = ({ id }) => {
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
  return (
    <div>
      {data.map((it, index) => (
        <Comment key={index} {...it} />
      ))}
    </div>
  );
};

export default CommentList;
