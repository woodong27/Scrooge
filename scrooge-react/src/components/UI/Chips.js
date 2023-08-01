import styles from "./Chips.module.css"; // 모듈 CSS 파일 import

const Chips = ({ chips }) => {
  return (
    <div className={`${styles.chipsContainer}`}>
      {" "}
      {/* 모듈 CSS 클래스 적용 */}
      {chips.map((chip, index) => (
        <div key={index} className={`${styles.chip}`}>
          {" "}
          {/* 모듈 CSS 클래스 적용 */}
          {chip}
        </div>
      ))}
    </div>
  );
};

export default Chips;
