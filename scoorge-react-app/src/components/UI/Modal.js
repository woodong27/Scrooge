import { Fragment } from "react";
import "./Modal.css";

// const Backdrop = (props) => {
//   return <div className="backdrop"></div>;
// };

const Modal = (props) => {
  return (
    <Fragment>
      {/* <Backdrop onClick={props.onCloseModal} /> */}
      <div className="modal-overlay" onClick={props.onCloseModal}></div>
      <div className="modal-content">
        <h2>{props.name}</h2>
        <p>{props.amount}</p>
        <button onClick={props.onCloseModal}>닫기</button>
        <div>어디에 썼나요?</div>
        <ul className="list">
          <li>식비</li>
          <li>쇼핑</li>
          <li>교통비</li>
          <li>문화생활</li>
          <li>유흥</li>
          <li>기타</li>
        </ul>
      </div>
    </Fragment>
  );
};

export default Modal;
