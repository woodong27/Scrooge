import PlusBtn from "../../components/UI/PlusBtn";
import QuestHeader from "../../components/QuestHeader";
import QuestList from "./QuestList";

const Quest = ({}) => {
  return (
    <div>
      <QuestHeader
        title={"스크루지 드림"}
        titleColor={"#ecd35b"}
        color={"#fff2af"}
      />
      <QuestList />
    </div>
  );
};

export default Quest;
