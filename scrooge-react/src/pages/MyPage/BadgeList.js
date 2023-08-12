import { useSelector } from "react-redux";

import styles from "./BadgeList.module.css";

const BadgeList = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const badgeCount = 9;
  const badges = [
    {
      id: 1,
      badgeName: "출첵1",
      badgeDescription: "첫번째 일일 정산 완료",
    },
    {
      id: 2,
      badgeName: "출첵2",
      badgeDescription: "일일 정산 7일 완료",
    },
    {
      id: 3,
      badgeName: "출첵3",
      badgeDescription: "일일 정산 한달 완료",
    },
    {
      id: 4,
      badgeName: "챌린지 우승1",
      badgeDescription: "첫번째 챌린지 우승",
    },
    {
      id: 5,
      badgeName: "퀘스트 성공1",
      badgeDescription: "첫번째모든 퀘스트 완료",
    },
    {
      id: 6,
      badgeName: "게시글 리뷰1",
      badgeDescription: "첫번째 게시글 평가",
    },
    {
      id: 7,
      badgeName: "첫 뱃지",
      badgeDescription: "첫번째 뱃지 획득을 기념하는 뱃지",
    },
    {
      id: 8,
      badgeName: "미구현",
      badgeDescription: "미구현",
    },
    {
      id: 9,
      badgeName: "미구현",
      badgeDescription: "미구현",
    },
  ];

  badges.map((e) => console.log(e.id));

  // for (let i = 1; i <= badgeCount; i++) {
  //   const badgePath = `${process.env.PUBLIC_URL}/Badge/${i}.png`;
  //   badges.push(
  //     <img
  //       key={i}
  //       className={styles.badgeImage}
  //       src={badgePath}
  //       alt={`뱃지 사진 ${i}`}
  //     />
  //   );
  // }

  return (
    <div className={styles.badgeContainer}>
      {badges.map((e) => (
        <img
          className={styles.badgeImage}
          src={`${process.env.PUBLIC_URL}/Badge/${e.id}.png`}
          alt=""
        />
      ))}
    </div>
  );
};
export default BadgeList;
