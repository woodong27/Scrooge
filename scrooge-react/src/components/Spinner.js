import SpinnerGIF from "../assets/Spinner.gif";
import GreenSpinner from "../assets/GreenSpinner.gif";
import styles from "./Spinner.module.css";

const Spinner = (props) => {
  return (
    <div className={styles.container}>
      <img src={props.isGreen ? GreenSpinner : SpinnerGIF} alt=""></img>
    </div>
  );
};

export default Spinner;
