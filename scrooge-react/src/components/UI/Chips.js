import styles from "./Chips.module.css";

const Chips = (props) => {
  return (
    <div
      className={
        props.selected === props.children ? styles.selected : styles.chip
      }
      onClick={() => props.setSelect(props.children)}
    >
      {props.children}
    </div>
  );
};

export default Chips;
