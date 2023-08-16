import { useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import axios from "axios";
import Stomp from "stompjs";

import styles from "./WebSocketComponent.module.css";

const WebSocketComponent = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const msgRef = useRef();
  const params = useParams();
  const socket = new WebSocket("ws:/day6scrooge.duckdns.org/api/ws");
  const stompClient = Stomp.over(socket);
  const [data, setData] = useState();
  const [msg, setMsg] = useState([]);

  useEffect(() => {
    axios
      .get("https://day6scrooge.duckdns.org/api/member/info", {
        headers: {
          Authorization: globalToken,
        },
      })
      .then((resp) => {
        setData(resp.data);
      })
      .catch((e) => console.log(e));

    const onConnect = () => {
      stompClient.subscribe(
        `/topic/chat/${params.id}/${params.team}`,
        (data) => {
          const parseData = JSON.parse(data.body);
          setMsg((prev) => [
            ...prev,
            { content: parseData.content, sender: parseData.sender },
          ]);
        }
      );
    };

    stompClient.connect({}, onConnect);

    return () => {
      stompClient.disconnect();
    };
  }, []);

  const sendMsgHandler = () => {
    stompClient.send(
      `/app/chat/${params.id}/${params.team}`,
      {},
      JSON.stringify({ content: msgRef.current.value, sender: data.nickname })
    );
  };

  return (
    <div className={styles.bg}>
      <div className={styles.chat}>우리팀 채팅</div>
      <div className={styles.body}>
        {msg.map((e, i) =>
          e.sender === data.nickname ? (
            <div className={styles.me} key={i}>
              <img src={`${process.env.PUBLIC_URL}/images/me.png`} alt=""></img>
              <div className={styles.nickname}>{e.sender}</div>
              <div className={styles.text}>{e.content}</div>
            </div>
          ) : (
            <div className={styles.you} key={i}>
              <img
                src={`${process.env.PUBLIC_URL}/images/you.png`}
                alt=""
              ></img>
              <div className={styles.nickname}>{e.sender}</div>
              <div className={styles.text}>{e.content}</div>
            </div>
          )
        )}
      </div>
      <div className={styles.input_layout}>
        <div className={styles.input_container}>
          <input type="text" ref={msgRef} id="msg" />
          <button onClick={sendMsgHandler}>전송</button>
        </div>
      </div>
    </div>
  );
};

export default WebSocketComponent;
