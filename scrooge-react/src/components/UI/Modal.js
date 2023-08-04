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
  const [isEdit, setIsEdit] = useState(false);
  const toggleIsEdit = () => setIsEdit(!isEdit);

  const [origin, setOrigin] = useState(amount);
  const [price, setPrice] = useState(amount);
  const localContentInput = useRef();

  const handleEdit = () => {
    if (origin.length < 2) {
      localContentInput.current.focus();
      return;
    }
    onEdit(id, origin, usedAt, cardName);
    setPrice(origin);
    toggleIsEdit();
  };

  const handleQuitEdit = () => {
    setIsEdit(false);
    setOrigin(amount);
  };

  return (
    <Fragment>
      <div className={styles.modal_overlay} onClick={onCloseModal}></div>
      <div className={styles.body}>
        <div className={styles.modal}>
          <div className={styles.handle}> </div>
          <h2>{usedAt}</h2>
          <div>
            {isEdit ? (
              <>
                <input
                  ref={localContentInput}
                  value={origin}
                  onChange={(e) => setOrigin(e.target.value)}
                />
              </>
            ) : (
              <>
                <p>{price}원</p>
              </>
            )}
            {isEdit ? (
              <>
                <button onClick={handleQuitEdit}>취소</button>
                <button onClick={handleEdit}>완료</button>
              </>
            ) : (
              <>
                <button onClick={toggleIsEdit}>수정</button>
              </>
            )}
          </div>
          <div className={styles.line}>
            <div className={styles.title}>거래 일자</div>
            <div className={styles.content}>
              {`${paidAt.split("T")[0]} ${paidAt.split("T")[1].split(".")[0]}`}
            </div>
          </div>{" "}
          <div className={styles.line}>
            <div className={styles.title}>결제 카드</div>
            <div className={styles.content}>{cardName}</div>
          </div>
        </div>
        <div className={styles.pickCategory}>
          <h3>어디에 썼나요?</h3>
          <ul>
            <button className={styles.category}>식비</button>
            <button className={styles.category}>쇼핑</button>
            <button className={styles.category}>문화생활</button>
            <button className={styles.category}>교통비</button>
            <button className={styles.category}>정기구독</button>
            <button className={styles.category}>기타</button>
          </ul>
        </div>
      </div>
    </Fragment>
  );
};

export default Modal;
