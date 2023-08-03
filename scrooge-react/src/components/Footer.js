import { Link } from "react-router-dom";

import styles from "./Footer.module.css";

const Footer = () => {
  return (
    <ul className={styles.list}>
      <li>
        <Link to="/">
          <img
            src={`${process.env.PUBLIC_URL}/images/nav-icons/home.png`}
            alt="홈"
          />
          <div>홈</div>
        </Link>
      </li>
      <li>
        <Link to="/300">
          <img
            src={`${process.env.PUBLIC_URL}/images/nav-icons/peoples.png`}
            alt="커뮤니티"
          />
          <div>커뮤니티</div>
        </Link>
      </li>
      <li>
        <Link to="/challenge">
          <img
            src={`${process.env.PUBLIC_URL}/images/nav-icons/challenge.png`}
            alt="챌린지"
          />
          <div>챌린지</div>
        </Link>
      </li>
      <li>
        <img
          src={`${process.env.PUBLIC_URL}/images/nav-icons/quest.png`}
          alt="퀘스트"
        />
        <div>퀘스트</div>
      </li>
      <li>
        <Link to="/mypage">
          <img
            src={`${process.env.PUBLIC_URL}/images/nav-icons/profile.png`}
            alt="프로필"
            />
          <div>프로필</div>
        </Link>
      </li>
    </ul>
  );
};

export default Footer;
