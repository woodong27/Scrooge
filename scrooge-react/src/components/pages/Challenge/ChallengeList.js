import ChallengeItem from "./ChallengeItem";

const ChallengeList = () => {
  const dummy = ["[식비] 배달 일주일에 한번만 시키기", "커피", "핏자", "라면"];
  return (
    <div className="flex flex-col mt-4 gap-6">
      {dummy.map((e) => {
        return <ChallengeItem title={e}></ChallengeItem>;
      })}
    </div>
  );
};

export default ChallengeList;
