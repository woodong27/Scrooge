import { useState, useEffect } from "react";
import axios from "axios";

import Chips from "../../components/UI/Chips";
import ChallengeItem from "./ChallengeItem";
import styles from "./AllChallenge.module.css";

const AllChallenge = () => {
  const category = ["전체", "식비", "교통", "쇼핑", "기타"];
  const [data, setData] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("전체");

  useEffect(() => {
    axios
      .get("https://day6scrooge.duckdns.org/api/challenge")
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
            return e.status === 1 ? (
              <ChallengeItem
                key={e.id}
                id={e.id}
                title={e.title}
                currentParticipants={e.currentParticipants}
                minParticipants={e.minParticipants}
                period={e.period}
                category={e.category}
                mainImg={e.mainImageAddress}
                text="참여하기"
              ></ChallengeItem>
            ) : (
              ""
            );
          }
        })}
      </div>
    </div>
  );
};

export default AllChallenge;
