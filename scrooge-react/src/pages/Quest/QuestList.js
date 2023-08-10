import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import QuestItem from "./QuestItem";
import styles from "./QuestList.module.css";

const QuestList = ({ props }) => {
  const globalToken = useSelector((state) => state.globalToken);
  const [ing, setIng] = useState([]);
  const [list, setList] = useState([]);
  const [finish, setFinish] = useState([]);

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
        setIng(data.filter((it) => it.selected && !it.done));
        setList(data.filter((it) => !it.selected));
        setFinish(data.filter((it) => it.done));
      })
      .catch((error) => console.log(error));
  }, []);

  const handleAdd = (id) => {
    questAdd(id);
  };

  const questAdd = (id) => {
    const postData = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: globalToken,
      },
    };
    fetch(`https://day6scrooge.duckdns.org/api/quest/${id}/select`, postData)
      .then((resp) => {
        resp.text();
      })
      .then((data) => {
        const selectedItem = list.find((item) => item.quest.id === id);
        if (selectedItem) {
          setIng((prevIng) => [...prevIng, selectedItem]);
          setList((prevList) => prevList.filter((item) => item.id !== id));
        }
      })
      .catch((error) => console.log(error));
  };

  return (
    <div>
      {ing.length > 0 && <div className={styles.title}> 진행 중 퀘스트 </div>}

      {ing.map((it, index) => (
        <QuestItem key={index} {...it} id={it.id} title={it.quest.title} />
      ))}
      <div className={styles.title}> 퀘스트 목록 </div>
      {list.map((it, index) => (
        <QuestItem
          className={styles.item}
          key={index}
          {...it}
          handleAdd={handleAdd}
          id={it.quest.id}
          title={it.quest.title}
          show={list.length > 3 ? "true" : "false"}
        />
      ))}
      {finish.length > 0 && <div className={styles.title}> 완료 퀘스트 </div>}
      {finish.map((it, index) => (
        <QuestItem key={index} {...it} id={it.id} title={it.quest.title} />
      ))}
    </div>
  );
};

export default QuestList;
