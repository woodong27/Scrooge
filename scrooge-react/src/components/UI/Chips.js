import styles from "./Chips.module.css";

const Chips = ({ chips }) => {
  return (
    <div className={`${styles.chipsContainer}`}>
      {chips.map((chip, index) => (
        <div key={index} className={`${styles.chip}`}>
          {chip}
        </div>
      ))}
    </div>
  );
};

export default Chips;
