import { Fragment, useState, useRef } from "react";
import styles from "./Modal.module.css";

const Modal = ({
  id,
  usedAt,
  amount,
  paidAt,
  cardName,
  onEdit,
  onCloseModal,
}) => {
  const [origin, setOrigin] = useState(amount);
  const [place, setPlace] = useState(usedAt);
  const [category, setCategory] = useState(" ");
  const localContentInput = useRef();
  const localPlaceInput = useRef();

  const buttons = [
    { id: 1, label: "식비" },
    { id: 2, label: "교통비" },
    { id: 3, label: "쇼핑" },
    { id: 4, label: "문화생활" },
    { id: 5, label: "정기구독" },
    { id: 6, label: "기타" },
  ];
  const handleCategory = (category) => {
    setCategory(category);
  };

  const handleEdit = () => {
    if (place.length < 2) {
      localPlaceInput.current.focus();
      return;
    }
    if (origin.length < 2) {
      localContentInput.current.focus();
      return;
    }
    if (category.length < 2) {
      localContentInput.current.focus();
      return;
    }
    onEdit(id, origin, place, cardName, category);
  };

  // 초기화 용도로 남겨두자..
  const handleQuitEdit = () => {
    setOrigin(amount);
  };

  return (
    <Fragment>
      <div className={styles.modal_overlay} onClick={onCloseModal}></div>
      <div className={styles.body}>
        <div className={styles.modal}>
          <div className={styles.handle}> </div>
          <div className={styles.head}>
            <span>결제 내역 </span>
            <input
              className={styles.input}
              ref={localPlaceInput}
              value={place}
              onChange={(e) => setPlace(e.target.value)}
            />
          </div>
          <div className={styles.head}>
            <span>결제 금액 </span>
            <input
              className={styles.input}
              ref={localContentInput}
              value={origin}
              onChange={(e) => setOrigin(e.target.value)}
            />
          </div>
          <div className={styles.line}>
            <div className={styles.title}>결제 카드</div>
            <div className={styles.content}>{cardName}</div>
          </div>
          <div className={styles.line}>
            <div className={styles.title}>결제 일자</div>
            <div className={styles.content}>
              {`${paidAt.slice(0, 19).split("T")[1]} `}
            </div>
          </div>{" "}
        </div>
        <p className={styles.where}>어디에 썼나요?</p>
        <div className={styles.pickCategory}>
          {buttons.map((button) => (
            <button
              key={button.id}
              className={
                category === button.label ? styles.selected : styles.category
              }
              onClick={() => handleCategory(button.label)}
            >
              {button.label}
            </button>
          ))}
        </div>
        <div className={styles.underBtn}>
          <button onClick={handleQuitEdit}>초기화</button>
          <button onClick={handleEdit}>완료</button>
        </div>
      </div>
    </Fragment>
  );
};

export default Modal;
