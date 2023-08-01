const Header = (props) => {
  return (
    <div
      className="w-full h-36 flex justify-center"
      style={{
        background:
          "linear-gradient(180deg, #A9D9F4 0%, rgba(169, 217, 244, 0.00) 100%)",
      }}
    >
      <div
        className="h-24 text-2xl text-white shadow-[2px 2px 0 #298DC5] flex items-center"
        style={{
          textShadow: "2px 2px 0 #298DC5",
        }}
      >
        스크루지 파이트
      </div>
    </div>
  );
};

export default Header;
