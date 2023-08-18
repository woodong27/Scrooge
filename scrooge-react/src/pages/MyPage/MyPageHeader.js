import { Link } from "react-router-dom";

import styles from "./MyPageHeader.module.css";
import QuestHeader from "../../components/QuestHeader";

const MyPageHeader = () => {
  return (
    <QuestHeader title={"마이 페이지"} titleColor={"#ecd35b"} color={"#fff2af"}>
      <Link to="/mypage/settings">
        <img
          className={styles["header-icon"]}
          src={`${process.env.PUBLIC_URL}/images/setting-icon.png`}
          alt="설정 아이콘"
        />
      </Link>
    </QuestHeader>
  );
};

export default MyPageHeader;
