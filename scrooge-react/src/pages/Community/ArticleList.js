import Article from "./Article";

const ArticleList = ({ data, status }) => {
  return (
    <div>
      {data.map((it, index) => (
        <Article
          key={index}
          status={status}
          content={it.content}
          id={it.id}
          memberId={it.memberId}
          memberNickname={it.memberNickname}
          imgAdress={it.imgAdress}
          memberAvatarAddress={it.memberAvatarAddress}
        />
      ))}
    </div>
  );
};

export default ArticleList;
