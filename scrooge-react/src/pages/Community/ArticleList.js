import Article from "./Article";

const ArticleList = ({ data }) => {
  return (
    <div>
      {data.map((it, index) => (
        <Article key={index} {...it} />
      ))}
    </div>
  );
};

export default ArticleList;
