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
  const [price, setPrice] = useState(amount);
  const localContentInput = useRef();

  const [category, setCategory] = useState();
  const handleCategory = (category) => {
    setCategory(category);
  };

  const handleEdit = () => {
    if (origin.length < 2) {
      localContentInput.current.focus();
      return;
    }
    onEdit(id, origin, usedAt, cardName);
    setPrice(origin);
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
            <h2>{usedAt}</h2>
          </div>
          <div className={styles.amount}>
            <span> ₩ </span>
            <input
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
            <div className={styles.title}>거래 일자</div>
            <div className={styles.content}>
              {`${paidAt.slice(0, 19).split("T")[1]} `}
            </div>
          </div>{" "}
        </div>
        <div className={styles.pickCategory}>
          <h3>어디에 썼나요? {category}</h3>
          <ul>
            <button
              className={styles.category}
              onClick={() => handleCategory("식비")}
            >
              식비
            </button>
            <button
              className={styles.category}
              onClick={() => handleCategory("쇼핑")}
            >
              쇼핑
            </button>
            <button
              className={styles.category}
              onClick={() => handleCategory("문화생활")}
            >
              문화생활
            </button>
            <button
              className={styles.category}
              onClick={() => handleCategory("교통비")}
            >
              교통비
            </button>
            <button
              className={styles.category}
              onClick={() => handleCategory("정기구독")}
            >
              정기구독
            </button>
            <button
              className={styles.category}
              onClick={() => handleCategory("기타")}
            >
              기타
            </button>
          </ul>
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
