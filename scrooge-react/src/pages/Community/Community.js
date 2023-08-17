import { useState, useEffect } from "react";
import { Link, useSearchParams } from "react-router-dom";
import { useSelector } from "react-redux";

import PlusBtn from "../../components/UI/PlusBtn";
import QuestHeader from "../../components/QuestHeader";
import ArticleList from "./ArticleList";
import styles from "./Community.module.css";
import HeaderToggle from "../../components/HeaderToggle";

const Community = () => {
  const memberId = parseInt(useSelector((state) => state.memberId));
  const [searchParams] = useSearchParams();
  const deleteId = searchParams.get("delete");
  const EditId = searchParams.get("edit");

  const [isMine, setIsMine] = useState(false);
  const [data, setData] = useState([]);
  const [mine, setMine] = useState([]);

  useEffect(() => {
    fetch("https://day6scrooge.duckdns.org/api/community")
      .then((resp) => resp.json())
      .then((data) => {
        setData(data);
        setMine(data.filter((item) => item.memberId === memberId));
      })
      .catch((error) => console.log(error));
  }, [EditId, memberId]);

  const myHandler = () => {
    setIsMine(true);
  };
  const allHandler = () => {
    setIsMine(false);
  };

  useEffect(() => {
    setData(data.filter((it) => it.id !== deleteId));
    setMine(mine.filter((it) => it.id !== deleteId));
  }, [deleteId]);

  return (
    <div className={styles.box}>
      <Link to="/community/create">
        <PlusBtn />
      </Link>
      <QuestHeader
        title={"스크루지 빌리지"}
        titleColor={"#5B911F"}
        color={"#A2D660"}
      ></QuestHeader>
      <HeaderToggle
        isMine={isMine}
        myHandler={myHandler}
        allHandler={allHandler}
        color={"#A2D660"}
      />
      <div className={styles.frame}>
        <ArticleList data={isMine ? mine : data} status={isMine} />
        <div className={styles.empty}></div>
      </div>
    </div>
  );
};

export default Community;
