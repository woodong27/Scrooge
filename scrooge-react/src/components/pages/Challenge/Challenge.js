import Header from "../../UI/Header";
import ChallengeToggle from "./ChallengeToggle";
import Chips from "../../UI/Chips";
import ChallengeList from "./ChallengeList";

const Challenge = ({ props }) => {
  return (
    <div>
      <Header>
        <ChallengeToggle />
      </Header>
      <Chips chips={["식비", "교통비", "쇼핑", "기타"]} />
      <ChallengeList></ChallengeList>
    </div>
  );
};

export default Challenge;
