import React, { useState } from "react";
import styles from "./ReportTab.module.css";

export default function ReportTab() {
  const [currentTab, setCurrentTab] = useState();

  const tabs = [
    {
      id: 1,
      tabTitle: '주간',
      title: 'title a',
      content: 'a'
    },
    {
      id: 2,
      tabTitle: '월간',
      title: 'title b',
      content: 'b'
    }
  ]

  const handleTabClick = (e) => {
    setCurrentTab(e.target.id)
  }
  return (
    <div className={styles.tabContainer}>
      <div className={styles.tabs}>
        {tabs.map((tab, i) => 
          <button
            key={i}
            id={tab.id}
            disabled={currentTab === `${tab.id}`}
            onClick={(handleTabClick)}
          >
            {tab.tabTitle}
          </button>
        )}
      </div>
      <div className={styles.content}>
        {tabs.map((tab, i) =>
          <div key={i}>
            {currentTab === `${tab.id}` &&
              <div>
                <p>{tab.title}</p>
                <p>{tab.content}</p>
              </div>
            }
          </div>
        )}
      </div>
    </div>
  )
}