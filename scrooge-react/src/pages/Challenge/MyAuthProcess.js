import { Fragment, useEffect, useState } from "react";

import styles from "./MyAuthProcess.module.css";
import AuthProgress from "./AuthProgress";
import axios from "axios";

const MyAuthProcess = (props) => {
  const [data, setData] = useState();
  const day = new Date().getDate();

  useEffect(() => {
    axios
      .get(
        `https://day6scrooge.duckdns.org/api/challenge/${props.id}/my-challenge/my-auth`,
        {
          headers: {
            Authorization: props.token,
          },
        }
      )
      .then((resp) => {
        resp.data.challengeAuthList.map((e) => {
          if (e.isSuccess && day === new Date(e.createdAt).getDate()) {
            props.setTodayAuth();
          }
        });
        setData(resp.data);
      })
      .catch((e) => console.log(e));
  }, []);

  return (
    <Fragment>
      {data && <AuthProgress per={data.currentCompletionRate}></AuthProgress>}
      <div className={styles.my_auth_container}>
        {data &&
          data.challengeAuthList.map((e, i) => {
            return (
              <div className={styles.my_auth_middle} key={i}>
                <div className={styles.idx}>{i + 1}</div>
                <div>
                  {new Date(e.createdAt).getMonth() + 1}/
                  {new Date(e.createdAt).getDate()}
                </div>
                <div className={e.isSuccess ? styles.success : styles.fail}>
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
