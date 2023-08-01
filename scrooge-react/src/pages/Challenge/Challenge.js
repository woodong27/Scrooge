import Header from "../../components/Header";
import Chips from "../../components/UI/Chips";
import ChallengeToggle from "./ChallengeToggle";
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
