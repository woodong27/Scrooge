import { Link } from "react-router-dom";
import styles from "./NavBar.module.css";

const NavBar = () => {
  return (
    <ul className={styles.list}>
      <li>
        <Link to="/">
          <img
            src={`${process.env.PUBLIC_URL}/images/nav-icons/home.png`}
            alt="홈"
          />
          <p>홈</p>
        </Link>
      </li>
      <li>
        <Link to="/300">
          <img
            src={`${process.env.PUBLIC_URL}/images/nav-icons/peoples.png`}
            alt="커뮤니티"
          />
          <p>커뮤니티</p>
        </Link>
      </li>
      <li>
        <img
          src={`${process.env.PUBLIC_URL}/images/nav-icons/challenge.png`}
          alt="챌린지"
        />
        <p>챌린지</p>
      </li>
      <li>
        <img
          src={`${process.env.PUBLIC_URL}/images/nav-icons/quest.png`}
          alt="퀘스트"
        />
        <p>퀘스트</p>
      </li>
      <li>
        <img
          src={`${process.env.PUBLIC_URL}/images/nav-icons/profile.png`}
          alt="프로필"
        />
        <p>프로필</p>
      </li>
    </ul>
  );
};

export default NavBar;
