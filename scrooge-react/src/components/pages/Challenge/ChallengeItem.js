const ChallengeItem = (props) => {
  return (
    <div className="w-80 h-44 m-auto border border-black rounded-xl">
      <div className="flex items-center h-32">
        <div className="w-1/2">
          <img
            className="w-20 h-20 rounded-full m-auto"
            src={`${process.env.PUBLIC_URL}/images/dummy.png`}
            alt="더미"
          />
        </div>
        <div className="mr-1">
          <p className="m-0 text-left">{props.title}</p>
          <p className="text-sm text-slate-500 m-0 text-left">
            참여인원: 6 / 10명
          </p>
          <p className="text-sm text-slate-500 m-0 text-left">#일주일</p>
        </div>
      </div>
      <div className="bg-blue rounded-xl w-2/3 h-8 m-auto flex justify-center items-center">
        참여하기
      </div>
    </div>
  );
};

export default ChallengeItem;
