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
  const [selectedCategory, setSelectedCategory] = useState("진행중");

  useEffect(() => {
    const idx = category.indexOf(selectedCategory) + 1;

    axios
      .get(`https://day6scrooge.duckdns.org/api/challenge/${idx}/member`, {
        headers,
      })
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
        {globalToken === ""
          ? "로그인이 필요해요!"
          : selectedCategory === "종료된"
          ? "참여한 챌린지가 없어요!"
          : selectedCategory === "진행중"
          ? data.length === 0
            ? "참여중인 챌린지가 없어요!"
            : data.map((e) => (
                <ChallengeItem
                  key={e.id}
                  id={e.id}
                  title={e.title}
                  currentParticipants={e.currentParticipants}
                  minParticipants={e.minParticipants}
                  period={e.period}
                  category={e.category}
                  mainImg={e.mainImageAddress}
                  text="인증하기"
                ></ChallengeItem>
              ))
          : data.length === 0
          ? "참여중인 챌린지가 없어요!"
          : data.map((e) => (
              <ChallengeItem
                key={e.id}
                id={e.id}
                title={e.title}
                currentParticipants={e.currentParticipants}
                minParticipants={e.minParticipants}
                period={e.period}
                category={e.category}
                mainImg={e.mainImageAddress}
                text="살펴보기"
              ></ChallengeItem>
            ))}
      </div>
    </div>
  );
};

export default MyChallenge;
