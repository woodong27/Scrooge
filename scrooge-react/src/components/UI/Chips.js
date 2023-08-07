import styles from "./Chips.module.css";

const Chips = (props) => {
  return (
    <div
      className={
        props.selectedCategory === props.children
          ? styles.selected
          : styles.chip
      }
      onClick={props.onClick}
    >
      {props.children}
    </div>
  );
};

export default Chips;
