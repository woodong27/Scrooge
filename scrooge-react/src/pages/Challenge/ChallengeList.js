import axios from "axios";
import { useState, useEffect } from "react";

import ChallengeItem from "./ChallengeItem";
import styles from "./ChallengeList.module.css";

const ChallengeList = () => {
  const [data, setData] = useState([]);

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
          ></ChallengeItem>
        );
      })}
    </div>
  );
};

export default ChallengeList;
