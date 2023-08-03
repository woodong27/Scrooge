import { Fragment } from "react";
import styles from "./Modal.module.css";

const Modal = (props) => {
  return (
    <Fragment>
      <div className={styles.modal_overlay} onClick={props.onCloseModal}></div>
      <div className={styles.body}>
        <div className={styles.content}>
          <div className={styles.handle}> </div>
          <h2>{props.usedAt}</h2>
          {/* <h4>{props.cardName}</h4> */}
          <div>
            <div className={styles.title}>거래 일자</div>
            <div className={styles.content}>
              {`${props.paidAt.split("T")[0]} ${
                props.paidAt.split("T")[1].split(".")[0]
              }`}
            </div>
          </div>

          <p>{props.amount}원</p>
          {/* <button onClick={props.onCloseModal}>닫기</button> */}
        </div>
        <div className={styles.category}>
          <h3>어디에 썼나요?</h3>
          <ul>
            <li>식비</li>
            <li>쇼핑</li>
            <li>문화생활</li>
            <li>교통비</li>
            <li>정기구독</li>
            <li>기타</li>
          </ul>
        </div>
      </div>
    </Fragment>
  );
};

export default Modal;
