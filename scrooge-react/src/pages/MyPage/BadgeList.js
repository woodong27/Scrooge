import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";

import styles from "./BadgeList.module.css";
import BottomSheet from "../../components/UI/BottomSheet";
import SettingModal from "../../components/UI/SettingModal";

const BadgeList = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const headers = { Authorization: globalToken };

  const [modal, setModal] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [id, setId] = useState();
  const [badgeName, setBadgeName] = useState();
  const [badgeDescription, setBadgeDescription] = useState();
  const [badges, setBadges] = useState(Array(9));

  const handleModalOpen = (idx) => {
    setModal(true);
  };

  const handleModalClose = () => {
    setModal(false);
  };

  const showModalHandler = (event) => {
    setId(event.target.src.split("/")[4][0]);
    setBadgeName(event.target.name);
    setBadgeDescription(event.target.alt);

    setShowModal(true);
  };
  const hideModalHandler = () => {
    setShowModal(false);
    setModal(false);
  };
  const setMainBadgeHandler = () => {
    axios
      .put(`https://day6scrooge.duckdns.org/api/badge/${id - 1}`, "", {
        headers,
      })
      .then(() => setModal(false))
      .catch((e) => console.log(e));
  };

  useEffect(() => {
    axios
      .get("https://day6scrooge.duckdns.org/api/badge/member", {
        headers,
      })
      .then((resp) => {
        console.log(resp.data);
        const arr = [...badges];
        resp.data.map((e) => (arr[e.id] = e));

        setBadges(arr);
      })
      .catch((e) => console.log(e));
  }, []);

  return (
    <div className={styles.badgeContainer}>
      {showModal && (
        <BottomSheet onClose={hideModalHandler}>
          <div className={styles.container} onClick={handleModalOpen}>
            <img
              className={styles.img}
              src={`${process.env.PUBLIC_URL}/Badge/${id}.png`}
              alt=""
            />
            <div className={styles.title}>{badgeName}</div>
            <div className={styles.body}>{badgeDescription}</div>
          </div>

          {modal && (
            <SettingModal
              message="메인 뱃지를 변경하겠습니까?"
              onConfirm={setMainBadgeHandler}
              onCancel={handleModalClose}
            />
          )}
        </BottomSheet>
      )}
      {badges.map((e, i) => (
        <img
          onClick={e ? showModalHandler : undefined}
          key={i}
          className={e ? styles.badgeImage : styles.noBadgeImage}
          src={`${process.env.PUBLIC_URL}/Badge/${i + 1}.png`}
          alt={e ? e.badgeDescription : ""}
          name={e ? e.badgeName : ""}
        />
      ))}
    </div>
  );
};
export default BadgeList;
