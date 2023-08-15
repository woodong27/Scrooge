import React, { useState } from "react";
import styles from "./ReportTab.module.css";
import CharList from "./CharList";
import BadgeList from "./BadgeList";

export default function ItemTab({ setModal, setItem, handleModalOpen }) {
  const [currentTab, setCurrentTab] = useState(1);

  const tabs = [
    {
      id: 1,
      tabTitle: "캐릭터",
      content: (
        <CharList
          setModal={setModal}
          setItem={setItem}
          handleModalOpen={handleModalOpen}
        />
      ),
    },
    {
      id: 2,
      tabTitle: "뱃지",
      content: <BadgeList />,
    },
  ];

  const handleTabClick = (e) => {
    setCurrentTab(Number(e.target.id));
  };
  return (
    <div className={styles.tabContainer}>
      {/* 탭 버튼 */}
      <div className={styles.tabs}>
        {tabs.map((tab, i) => (
          <button
            key={i}
            id={tab.id}
            disabled={currentTab === tab.id}
            onClick={handleTabClick}
          >
            {tab.tabTitle}
          </button>
        ))}
      </div>
      {/* 해당 내용 */}
      <div className={styles.content}>
        {tabs.map((tab, i) => (
          <div key={i}>
            {currentTab === tab.id && (
              <div>
                <div>{tab.content}</div>
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}
