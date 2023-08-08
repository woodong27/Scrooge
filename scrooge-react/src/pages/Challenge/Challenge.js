import { useState } from "react";
import { Link } from "react-router-dom";

import Header from "../../components/Header";
import ChallengeToggle from "./ChallengeToggle";
import PlusBtn from "../../components/UI/PlusBtn";
import AllChallenge from "./AllChallenge";
import MyChallenge from "./MyChallenge";

const Challenge = () => {
  const [isMyChallenge, setIsMyChallnge] = useState(false);

  const myChallengeHandler = () => {
    setIsMyChallnge(true);
  };
  const allChallengeeHandler = () => {
    setIsMyChallnge(false);
  };

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
    </div>
  );
};

export default Challenge;
