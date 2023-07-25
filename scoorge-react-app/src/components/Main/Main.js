import { Fragment } from "react";
import { Link } from "react-router-dom";
import ProgressBar from "./ProgressBar";
import styles from "./Main.module.css";

const Main = () => {
  return (
    <Fragment>
      <div className={styles.card}>
        <ProgressBar />
        <div className={styles.header34}>
          <p>Lv. 3</p>
          <p>돈그만써</p>
        </div>
        <div className={styles.box}>
          <img
            className={styles.img}
            src={`${process.env.PUBLIC_URL}/Character/${
              ~~(Math.random() * 6) + 1
            }.png`}
            alt="캐릭터"
          />
        </div>
        <Link to="/300">
          <button className={styles.btn}>일일정산</button>
        </Link>
      </div>
    </Fragment>
  );
};

export default Main;
