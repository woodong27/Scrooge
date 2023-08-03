import { useState } from "react";

import Header from "../../components/Header";
import Chips from "../../components/UI/Chips";
import ChallengeToggle from "./ChallengeToggle";
import ChallengeList from "./ChallengeList";
import PlusBtn from "../../components/UI/PlusBtn";

const Challenge = ({ props }) => {
  const [isMyChallenge, setIsMyChallnge] = useState(false);
  const myChallengeHandler = () => {
    setIsMyChallnge(true);
  };
  const allChallengeeHandler = () => {
    setIsMyChallnge(false);
  };

  return (
    <div>
      <PlusBtn></PlusBtn>
      <Header>
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
