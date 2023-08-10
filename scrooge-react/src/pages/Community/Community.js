import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

import PlusBtn from "../../components/UI/PlusBtn";
import QuestHeader from "../../components/QuestHeader";
import ArticleList from "./ArticleList";
import styles from "./Community.module.css";
import HeaderToggle from "../../components/HeaderToggle";

const Community = ({}) => {
  const memberId = useSelector((state) => state.memberId);
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
  }, []);

  const myHandler = () => {
    setIsMine(true);
  };
  const allHandler = () => {
    setIsMine(false);
  };

  return (
    <div className={styles.box}>
      <Link to="/community/create">
        <PlusBtn />
      </Link>
      <QuestHeader
        title={"스크루지 빌리지"}
        titleColor={"#5B911F"}
        color={"#A2D660"}>
        <HeaderToggle
          isMine={isMine}
          myHandler={myHandler}
          allHandler={allHandler}
        />
      </QuestHeader>
      <div className={styles.frame}>
        <ArticleList data={isMine ? mine : data} />
      </div>
    </div>
  );
};

export default Community;
