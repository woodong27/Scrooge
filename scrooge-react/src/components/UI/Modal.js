import { Fragment } from "react";
import styles from "./Modal.module.css";

const Modal = (props) => {
  return (
    <Fragment>
      <div className={styles.modal_overlay} onClick={props.onCloseModal}></div>
      <div className={styles.body}>
        <div className={styles.modal}>
          <div className={styles.handle}> </div>
          <h2>{props.usedAt}</h2>
          <p>{props.amount}원</p>
          <div className={styles.line}>
            <div className={styles.title}>거래 일자</div>
            <div className={styles.content}>
              {`${props.paidAt.split("T")[0]} ${
                props.paidAt.split("T")[1].split(".")[0]
              }`}
            </div>
          </div>{" "}
          <div className={styles.line}>
            <div className={styles.title}>결제 카드</div>
            <div className={styles.content}>{props.cardName}</div>
          </div>
          {/* <button onClick={props.onCloseModal}>닫기</button> */}
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
