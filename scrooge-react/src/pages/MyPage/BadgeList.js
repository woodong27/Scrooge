import { useState } from "react";
import { useSelector } from "react-redux";

import styles from "./BadgeList.module.css";
import BottomSheet from "../../components/UI/BottomSheet";

const BadgeList = () => {
  const globalToken = useSelector((state) => state.globalToken);
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
  const [showModal, setShowModal] = useState(false);
  const [id, setId] = useState();
  const [badgeName, setBadgeName] = useState();
  const [badgeDescription, setBadgeDescription] = useState();

  const showModalHandler = (event) => {
    setId(event.target.src.split("/")[4][0]);
    setBadgeName(event.target.name);
    setBadgeDescription(event.target.alt);

    setShowModal(true);
  };
  const hideModalHandler = () => setShowModal(false);

  return (
    <div className={styles.badgeContainer}>
      {showModal && (
        <BottomSheet onClose={hideModalHandler}>
          <img
            className={styles.img}
            src={`${process.env.PUBLIC_URL}/Badge/${id}.png`}
            alt=""
          />
          <div className={styles.title}>{badgeName}</div>
          <div className={styles.body}>{badgeDescription}</div>
        </BottomSheet>
      )}
      {badges.map((e) => (
        <img
          onClick={showModalHandler}
          key={e.id}
          className={styles.badgeImage}
          src={`${process.env.PUBLIC_URL}/Badge/${e.id}.png`}
          alt={e.badgeDescription}
          name={e.badgeName}
        />
      ))}
    </div>
  );
};
export default BadgeList;
