// import QuestList from "./QuestList";
import PlusBtn from "../../components/UI/PlusBtn";
// http://localhost:8080/
const Quest = ({ props }) => {
  fetch("http://day6scrooge.duckdns.org:8081/quest").then((response) =>
    console.log(response)
  );
  return (
    <div>
      <PlusBtn></PlusBtn>

      {/* <QuestList></QuestList> */}
    </div>
  );
};

export default Quest;
