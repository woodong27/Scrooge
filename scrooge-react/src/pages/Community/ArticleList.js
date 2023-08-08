import { useEffect, useState } from "react";
import Article from "./Article";
import styles from "./ArticleList.module.css";

const ArticleList = ({ props }) => {
  const [data, setData] = useState([]);
  useEffect(() => {
    fetch("http://day6scrooge.duckdns.org:8081/community")
      .then((resp) => resp.json())
      .then((data) => {
        console.log(data);
        // setData(data);
        setData([
          {
            id: 1,
            content:
              "게시글1 내용입니다. 너무 길어지면 어떻게 들어가는지 궁금합니다만깔깔깔깔깔...",
            imgAddress:
              "src\\main\\resources\\static\\assets\\community\\aeaaa1a8-3406-4ece-9d1a-4454ad1c0148_test_image.jpg",
            createdAt: "2023-08-07T11:35:39.106",
            memberId: 1,
            memberNickname: "돈그만써",
            memberAvatarAddress: "assets/avatars/0.png",
          },
        ]);
        console.log(data);
      })
      .catch((error) => console.log(error));
  }, []);
  return (
    <div>
      {data.map((it, index) => (
        <Article key={index} {...it} />
      ))}
    </div>
  );
};

export default ArticleList;
