import { Link } from "react-router-dom";

import styles from "./Footer.module.css";

const Footer = () => {
  return (
    <ul className={styles.list}>
      <li>
        <Link to="/">
          <img
            src={`${process.env.PUBLIC_URL}/images/nav-icons/homeNew.png`}
            alt="홈"
          />
          <div>홈</div>
        </Link>
      </li>
      <li>
        <Link to="/community">
          <img
            src={`${process.env.PUBLIC_URL}/images/nav-icons/peoplesNew.png`}
            alt="커뮤니티"
          />
          <div>빌리지</div>
        </Link>
      </li>
      <li>
        <Link to="/challenge">
          <img
            src={`${process.env.PUBLIC_URL}/images/nav-icons/challengeNew.png`}
            alt="챌린지"
          />
          <div>파이트</div>
        </Link>
      </li>
      <li>
        <Link to="/quest">
          <img
            src={`${process.env.PUBLIC_URL}/images/nav-icons/questNew.png`}
            alt="퀘스트"
          />
          <div>드림</div>
        </Link>
      </li>
      <li>
        <Link to="/mypage">
          <img
            src={`${process.env.PUBLIC_URL}/images/nav-icons/profileNew.png`}
            alt="프로필"
          />
          <div>프로필</div>
        </Link>
      </li>
    </ul>
  );
};

export default Footer;
