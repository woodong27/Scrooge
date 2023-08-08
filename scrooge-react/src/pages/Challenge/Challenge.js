import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

import Header from "../../components/Header";
import Chips from "../../components/UI/Chips";
import ChallengeToggle from "./ChallengeToggle";
import PlusBtn from "../../components/UI/PlusBtn";
import ChallengeItem from "./ChallengeItem";
import styles from "./Challenge.module.css";

const Challenge = () => {
  const [isMyChallenge, setIsMyChallnge] = useState(false);
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

  const myChallengeHandler = () => {
    setIsMyChallnge(true);
  };
  const allChallengeeHandler = () => {
    setIsMyChallnge(false);
  };

  const selectCategoryAll = () => {
    setSelectedCategory("전체");
  };

  const selectCategoryEat = () => {
    setSelectedCategory("식비");
  };

  const selectCategoryTraffic = () => {
    setSelectedCategory("교통");
  };

  const selectCategoryShopping = () => {
    setSelectedCategory("쇼핑");
  };

  const selectCategoryOther = () => {
    setSelectedCategory("기타");
  };

  return (
    <div>
      <Link to="/challenge/create">
        <PlusBtn />
      </Link>
      <Header text="스크루지 파이트">
        <ChallengeToggle
          isMyChallenge={isMyChallenge}
          myChallengeHandler={myChallengeHandler}
          allChallengeeHandler={allChallengeeHandler}
        />
      </Header>

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
              ></ChallengeItem>
            );
          }
        })}
      </div>
    </div>
  );
};

export default Challenge;
