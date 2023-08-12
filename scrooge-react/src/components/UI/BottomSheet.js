import { Fragment } from "react";
import ReactDom from "react-dom";

import styles from "./BottomSheet.module.css";

const BackDrop = (props) => {
  return <div className={styles.backdrop} onClick={props.onClick}></div>;
};

const ModalOverlday = (props) => {
  return (
    <div className={styles.modal}>
      <div className={styles.content}>{props.children}</div>
    </div>
  );
};

const portalElement = document.getElementById("overlays");
const BottomSheet = (props) => {
  return (
    <Fragment>
      {ReactDom.createPortal(
        <BackDrop onClick={props.onClose}></BackDrop>,
        portalElement
      )}
      {ReactDom.createPortal(
        <ModalOverlday>{props.children}</ModalOverlday>,
        portalElement
      )}
    </Fragment>
  );
};

export default BottomSheet;
