import { useEffect, useState } from "react";

import styles from "./TeamAuthProcess.module.css";
import axios from "axios";

const TeamAuthProcess = (props) => {
  const [data, setData] = useState([]);
  const [team, setTeam] = useState(0);

  useEffect(() => {
    if (!props.isTeamZero) setTeam(1);

    axios
      .get(
        `https://day6scrooge.duckdns.org/api/challenge/${props.id}/team-auth`,
        {
          headers: {
            Authorization: props.token,
          },
        }
      )
      .then((resp) => {
        setData(resp.data);
      })
      .catch((e) => console.log(e));
  }, []);

  return (
    <div className={styles.img_container}>
      {data.length > 0 &&
        data.map((e) => {
          return e.memberAuthList.length > 0
            ? e.memberAuthList.map(
                (el, index) =>
                  el.success && (
                    <img key={index} src={el.authImageAddress} alt="" />
                  )
              )
            : null;
        })}
    </div>
  );
};

export default TeamAuthProcess;
