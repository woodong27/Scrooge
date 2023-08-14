import { Fragment, useEffect, useState } from "react";

import styles from "./MyAuthProcess.module.css";
import AuthProgress from "./AuthProgress";
import axios from "axios";

const MyAuthProcess = (props) => {
  const [data, setData] = useState();

  useEffect(() => {
    axios
      .get(
        `https://day6scrooge.duckdns.org/api/challenge/1/my-challenge/my-auth`,
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
    <Fragment>
      {data && <AuthProgress per={data.currentCompletionRate}></AuthProgress>}
      <div className={styles.my_auth_container}>
        {data &&
          data.challengeAuthList.map((e) => {
            return (
              <div className={styles.my_auth_middle}>
                <div className={styles.idx}>{e.id}</div>
                <div>
                  {new Date(e.createdAt).getMonth() + 1}/
                  {new Date(e.createdAt).getDate()}
                </div>
                <div className={styles.succes}>
                  {e.isSuccess ? "성공" : "실패"}
                </div>
                <img src={e.imageAddress} alt="" />
              </div>
            );
          })}
      </div>
    </Fragment>
  );
};

export default MyAuthProcess;
