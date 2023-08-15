import { useEffect, useState } from "react";

import styles from "./TeamAuthProcess.module.css";
import axios from "axios";

const TeamAuthProcess = (props) => {
  const [data, setData] = useState([]);
  const [team, setTeam] = useState(0);

  useEffect(() => {
    if (!props.isTeamZero) setTeam(1);

    axios
      .get(`https://day6scrooge.duckdns.org/api/challenge/1/team-auth`, {
        headers: {
          Authorization: props.token,
        },
      })
      .then((resp) => {
        setData(resp.data);
      })
      .catch((e) => console.log(e));
  }, []);

  return (
    <div className={styles.img_container}>
      {data.length > 0 &&
        data[team].memberAuthList.map((e) => (
          <img src={e.authImageAddress} alt=""></img>
        ))}
    </div>
  );
};

export default TeamAuthProcess;
