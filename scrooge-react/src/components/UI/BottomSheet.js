import { Fragment, useState } from "react";
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

const BottomSheet = (props) => {
  const [touchStartY, setTouchStartY] = useState(null);

  const handleTouchStart = (event) => {
    setTouchStartY(event.touches[0].clientY);
  };

  const handleTouchMove = (event) => {
    if (touchStartY === null) return;

    const deltaY = event.touches[0].clientY - touchStartY;

    if (deltaY > 0) {
      // 아래로 당기는 경우
      if (deltaY > 50) {
        props.onClose();
        setTouchStartY(null);
      }
    } else if (deltaY < 0) {
      // 위로 당기는 경우
      if (deltaY < -50) {
        props.onClose();
        setTouchStartY(null);
      }
    }
  };

  const portalElement = document.getElementById("overlays");
  return (
    <Fragment>
      {ReactDom.createPortal(
        <BackDrop onClick={props.onClose}></BackDrop>,
        portalElement
      )}
      {ReactDom.createPortal(
        <ModalOverlday
          onTouchStart={handleTouchStart}
          onTouchMove={handleTouchMove}
        >
          {props.children}
        </ModalOverlday>,
        portalElement
      )}
    </Fragment>
  );
};

export default BottomSheet;
