import { useEffect, useState } from "react";
import QuestItem from "./QuestItem";
import styles from "./QuestList.module.css";

const QuestList = ({ props }) => {
  const [data, setData] = useState([]);
  useEffect(() => {
    fetch("http://day6scrooge.duckdns.org:8081/quest/")
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
        console.log(data);
      })
      .catch((error) => console.log(error));
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
