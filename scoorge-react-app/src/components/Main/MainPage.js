import { Fragment } from "react";
import ProgressBar from "./ProgressBar";
import Button from "../UI/Button";

const MainPage = () => {
  return (
    <Fragment>
      <ProgressBar />
      <div className="">
        <p>Lv. 3</p>
        <p>돈그만써</p>
      </div>
      <Button name="정산하기"></Button>
    </Fragment>
  );
};

export default MainPage;
