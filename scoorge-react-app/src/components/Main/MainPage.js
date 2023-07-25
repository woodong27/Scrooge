import { Fragment } from "react";
import { Link } from "react-router-dom";
import ProgressBar from "./ProgressBar";
import styles from "./MainPage.module.css";

const MainPage = () => {
  return (
    <Fragment>
      <ProgressBar />
      <div className={styles.header34}>
        <p>Lv. 3</p>
        <p>돈그만써</p>
      </div>
      <Link to="/300">
        <button className={styles.btn}></button>
      </Link>
    </Fragment>
  );
};

export default MainPage;
