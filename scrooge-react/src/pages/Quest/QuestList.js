import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import QuestItem from "./QuestItem";
import styles from "./QuestList.module.css";

const QuestList = ({ props }) => {
  const globalToken = useSelector((state) => state.globalToken);
  const [data, setData] = useState([]);

  useEffect(() => {
    const postData = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    fetch("https://day6scrooge.duckdns.org/api/quest", postData)
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
        console.log(data);
      })
      .catch((error) => console.log(error));
    console.log(data);
  }, []);
  return (
    <div>
      <div className={styles.title}> 진행 중 퀘스트 </div>
      <div className={styles.title}> 퀘스트 목록 </div>
      <div className={styles.title}> 완료 퀘스트 </div>
      {data.map((it, index) => (
        <QuestItem key={index} {...it} />
      ))}
    </div>
  );
};

export default QuestList;
