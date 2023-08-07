import { useState } from "react";
import { Link } from "react-router-dom";

import Header from "../../components/Header";
import Chips from "../../components/UI/Chips";
import ChallengeToggle from "./ChallengeToggle";
import ChallengeList from "./ChallengeList";
import PlusBtn from "../../components/UI/PlusBtn";

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

      <Chips chips={["식비", "교통비", "쇼핑", "기타"]} />
      <ChallengeList></ChallengeList>
    </div>
  );
};

export default Challenge;
