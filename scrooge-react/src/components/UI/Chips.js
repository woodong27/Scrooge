const Chips = ({ chips }) => {
  return (
    <div className="flex flex-wrap ml-5 mr-5 mt-5 justify-between">
      {chips.map((chip, index) => (
        <div
          key={index}
          className="inline-flex items-center bg-gray-300 rounded-full px-4 py-2 text-sm text-gray-800 mr-2 mb-2"
        >
          {chip}
        </div>
      ))}
    </div>
  );
};

export default Chips;
