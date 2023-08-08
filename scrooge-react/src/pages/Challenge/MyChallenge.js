import { useState, useEffect } from "react";
import axios from "axios";

import Chips from "../../components/UI/Chips";
import ChallengeItem from "./ChallengeItem";
import styles from "./MyChallenge.module.css";

const MyChallenge = () => {
  const category = ["전체", "식비", "교통", "쇼핑", "기타"];
  const [data, setData] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("전체");

  useEffect(() => {
    axios
      .get("http://day6scrooge.duckdns.org:8081/challenge")
      .then((response) => {
        setData(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

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
          if (selectedCategory === "전체" || selectedCategory === e.category) {
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
          }
        })}
      </div>
    </div>
  );
};

export default MyChallenge;
