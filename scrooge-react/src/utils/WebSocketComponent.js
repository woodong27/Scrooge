import React, { useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";
import { useParams, Link } from "react-router-dom";
import axios from "axios";
import Stomp from "stompjs";

import backImg from "../assets/back.png";
import styles from "./WebSocketComponent.module.css";

const WebSocketComponent = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const msgRef = useRef();
  const params = useParams();
  const socket = useRef(null);
  const stompClient = useRef(null);
  const [data, setData] = useState(null);
  const [msg, setMsg] = useState([]);
  const [txt, setTxt] = useState("");
  const containerRef = useRef(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const resp = await axios.get(
          "https://day6scrooge.duckdns.org/api/member/info",
          {
            headers: {
              Authorization: globalToken,
            },
          }
        );
        setData(resp.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();

    socket.current = new WebSocket("ws:/day6scrooge.duckdns.org/api/ws");
    stompClient.current = Stomp.over(socket.current);

    const onConnect = () => {
      stompClient.current.subscribe(
        `/topic/chat/${params.id}/${params.team}`,
        (data) => {
          const parseData = JSON.parse(data.body);
          setMsg((prev) => [
            ...prev,
            { content: parseData.content, sender: parseData.sender },
          ]);

          setTimeout(() => {
            containerRef.current.scrollTop = containerRef.current.scrollHeight;
          }, 0);
        }
      );
    };

    stompClient.current.connect({}, onConnect);

    return () => {
      stompClient.current.disconnect();
    };
  }, [globalToken, params.id, params.team]);

  const sendMsgHandler = () => {
    if (!socket.current.readyState) return;
    msgRef.current.focus();
    setTxt("");

    stompClient.current.send(
      `/app/chat/${params.id}/${params.team}`,
      {},
      JSON.stringify({ content: msgRef.current.value, sender: data.nickname })
    );
  };

  return (
    <div className={styles.bg}>
      <div className={styles.header}>
        <Link className={styles.back} to={`/challenge/my/${params.id}`}>
          <img src={backImg} alt="" />
        </Link>
        <div className={styles.chat}>우리팀 채팅</div>
      </div>
      <div className={styles.body} ref={containerRef}>
        {msg.map((e, i) => (
          <div
            className={e.sender === data.nickname ? styles.me : styles.you}
            key={i}
          >
            <img
              src={`${process.env.PUBLIC_URL}/images/${
                e.sender === data.nickname ? "you.png" : "me.png"
              }`}
              alt=""
            />
            <div className={styles.nickname}>
              {e.sender === data.nickname ? "나" : e.sender}
            </div>
            <div className={styles.text}>{e.content}</div>
          </div>
        ))}
      </div>
      <div className={styles.input_layout}>
        <div className={styles.input_container}>
          <input
            type="text"
            ref={msgRef}
            id="msg"
            value={txt}
            onChange={(e) => setTxt(e.target.value)}
          />
          <button onClick={sendMsgHandler}>전송</button>
        </div>
      </div>
    </div>
  );
};

export default WebSocketComponent;
