import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import Comment from "./Comment";

const CommentList = ({ id, comments }) => {
  const globalToken = useSelector((state) => state.globalToken);
  const [data, setData] = useState(comments);

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
    <div>
      {data.map((it) => (
        <Comment key={it.id} {...it} commentDelete={commentDelete} />
      ))}
    </div>
  );
};

export default CommentList;
