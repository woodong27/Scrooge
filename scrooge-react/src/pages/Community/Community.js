import PlusBtn from "../../components/UI/PlusBtn";
import QuestHeader from "../../components/QuestHeader";
import ArticleList from "./ArticleList";

const Community = ({}) => {
  return (
    <div>
      <PlusBtn></PlusBtn>
      <QuestHeader
        title={"스크루지 빌리지"}
        titleColor={"#5B911F"}
        color={"#A2D660"}
      />
      <ArticleList />
    </div>
  );
};

export default Community;
