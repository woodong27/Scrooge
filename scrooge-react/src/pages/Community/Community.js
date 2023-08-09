import { Link } from "react-router-dom";

import PlusBtn from "../../components/UI/PlusBtn";
import QuestHeader from "../../components/QuestHeader";
import ArticleList from "./ArticleList";

const Community = ({}) => {
  return (
    <div>
      <Link to="/community/create">
        <PlusBtn />
      </Link>
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
