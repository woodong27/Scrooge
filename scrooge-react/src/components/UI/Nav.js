import { Link } from "react-router-dom";

const Nav = () => {
  return (
    <ul className="flex items-end justify-evenly border-t-slate-500 fixed bottom-0 w-full z-10 bg-white">
      <li className="h-16">
        <Link to="/">
          <img
            className="m-auto w-7 h-7"
            src={`${process.env.PUBLIC_URL}/images/nav-icons/home.png`}
            alt="홈"
          />
          <p className="mt-2 text-sm">홈</p>
        </Link>
      </li>
      <li className="h-16">
        <Link to="/300">
          <img
            className="m-auto w-7 h-7"
            src={`${process.env.PUBLIC_URL}/images/nav-icons/peoples.png`}
            alt="커뮤니티"
          />
          <p className="mt-2 text-sm">커뮤니티</p>
        </Link>
      </li>
      <li className="h-16">
        <Link to="/challenge">
          <img
            className="m-auto w-7 h-7"
            src={`${process.env.PUBLIC_URL}/images/nav-icons/challenge.png`}
            alt="챌린지"
          />
          <p className="mt-2 text-sm">챌린지</p>
        </Link>
      </li>
      <li className="h-16">
        <img
          className="m-auto w-7 h-7"
          src={`${process.env.PUBLIC_URL}/images/nav-icons/quest.png`}
          alt="퀘스트"
        />
        <p className="mt-2 text-sm">퀘스트</p>
      </li>
      <li className="h-16">
        <img
          className="m-auto w-7 h-7"
          src={`${process.env.PUBLIC_URL}/images/nav-icons/profile.png`}
          alt="프로필"
        />
        <p className="mt-2 text-sm">프로필</p>
      </li>
    </ul>
  );
};

export default Nav;
