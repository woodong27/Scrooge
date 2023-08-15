import { useState, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";

import Header from "../../components/Header";
import ChallengeToggle from "./ChallengeToggle";
import PlusBtn from "../../components/UI/PlusBtn";
import AllChallenge from "./AllChallenge";
import MyChallenge from "./MyChallenge";
import Toast from "../../components/UI/Toast";

const Challenge = () => {
  const location = useLocation();
  const state = location.state;

  const [isMyChallenge, setIsMyChallnge] = useState(false);
  const [makeToast, setMakeToast] = useState(false);

  const myChallengeHandler = () => {
    setIsMyChallnge(true);
  };
  const allChallengeeHandler = () => {
    setIsMyChallnge(false);
  };

  useEffect(() => {
    if (state === "시작") {
      setMakeToast(true);
    }

    if (state === "생성") {
    }
  }, [state]);

  return (
    <div>
      <Link to="/challenge/create">
        <PlusBtn />
      </Link>

      <Header text="스크루지 파이트">
        <ChallengeToggle
          isMyChallenge={isMyChallenge}
          myChallengeHandler={myChallengeHandler}
          allChallengeeHandler={allChallengeeHandler}
        />
      </Header>

      {isMyChallenge ? <MyChallenge /> : <AllChallenge />}

      {makeToast && (
        <Toast setToast={setMakeToast} text="챌린지가 시작 되었어요!" />
      )}
    </div>
  );
};

export default Challenge;
