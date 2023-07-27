import { Link } from "react-router-dom";
import styles from "./NavBar.module.css";
import home from "../../assets/NavLogo/Home.png";
import community from "../../assets/NavLogo/Community.png";
import challenge from "../../assets/NavLogo/Challenge.png";
import quest from "../../assets/NavLogo/Quest.png";
import profile from "../../assets/NavLogo/Profile.png";

const NavBar = () => {
  return (
    <ul className={styles.list}>
      <li>
        <Link to="/">
          <img src={home} alt="홈" />
          <p>홈</p>
        </Link>
      </li>
      <li>
        <Link to="/300">
          <img src={community} alt="커뮤니티" />
          <p>커뮤니티</p>
        </Link>
      </li>
      <li>
        <img src={challenge} alt="챌린지" />
        <p>챌린지</p>
      </li>
      <li>
        <img src={quest} alt="퀘스트" />
        <p>퀘스트</p>
      </li>
      <li>
        <img src={profile} alt="프로필" />
        <p>프로필</p>
      </li>
    </ul>
  );
};

export default NavBar;
