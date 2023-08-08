import { useState, useEffect } from "react";
import axios from "axios";
import { useSelector } from "react-redux";

import Chips from "../../components/UI/Chips";
import ChallengeItem from "./ChallengeItem";
import styles from "./MyChallenge.module.css";

const MyChallenge = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const headers = { Authorization: globalToken };

  const category = ["시작전", "진행중", "종료된"];
  const [data, setData] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("시작전");
  let idx = 1;

  useEffect(() => {
    if (selectedCategory === "시작전") idx = 1;
    if (selectedCategory === "진행중") idx = 2;
    if (selectedCategory === "종료된") idx = 3;

    axios
      .get(
        `http://day6scrooge.duckdns.org:8081/challenge/${idx}/my-challenge`,
        {
          headers,
        }
      )
      .then((response) => {
        setData(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, [selectedCategory]);

  return (
    <div>
      {category.map((e, i) => (
        <Chips
          selected={selectedCategory}
          setSelect={setSelectedCategory}
          key={i}
        >
          {e}
        </Chips>
      ))}

      <div className={styles.list}>
        {data.map((e) => {
          return (
            <ChallengeItem
              key={e.id}
              id={e.id}
              title={e.title}
              currentParticipants={e.currentParticipants}
              minParticipants={e.minParticipants}
              period={e.period}
              category={e.category}
              text="인증하기"
            ></ChallengeItem>
          );
        })}
      </div>
    </div>
  );
};

export default MyChallenge;
