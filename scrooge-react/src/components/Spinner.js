import SpinnerGIF from "../assets/Spinner.gif";
import styles from "./Spinner.module.css";

const Spinner = () => {
  return (
    <div className={styles.container}>
      <img src={SpinnerGIF} alt=""></img>
    </div>
  );
};

export default Spinner;
