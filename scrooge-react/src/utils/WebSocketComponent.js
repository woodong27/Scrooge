import { useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";
import Stomp from "stompjs";

const WebSocketComponent = () => {
  const globalToken = useSelector((state) => state.globalToken);
  const msgRef = useRef();
  const socket = new WebSocket("ws://localhost:8081/ws");
  const stompClient = Stomp.over(socket);
  const [data, setData] = useState();
  const [msg, setMsg] = useState();

  useEffect(() => {
    axios
      .get("https://day6scrooge.duckdns.org/api/member/info", {
        headers: {
          Authorization:
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImxlZWRuajAxMTNAbmF2ZXIuY29tIiwibWVtYmVySWQiOjYsImlhdCI6MTY5MjAwMTM4MCwiZXhwIjoxNjkyNjA2MTgwfQ._18pTvWAWaIG79Eaw32K0vEH67NAsGhwPri68U1E1EA",
        },
      })
      .then((resp) => {
        setData(resp.data);
      })
      .catch((e) => console.log(e));

    const onConnect = () => {
      console.log("Connected to WebSocket");

      stompClient.subscribe("/topic/chat/1/0", (msg) => {
        setMsg((prev) => [...prev, msg.body]);
      });
    };

    stompClient.connect({}, onConnect);

    return () => {
      stompClient.disconnect();
    };
  }, []);

  const sendMsgHandler = () => {
    stompClient.send(
      "/app/chat/1/0",
      {},
      JSON.stringify({ content: msgRef.current.value, sender: data.nickname })
    );
  };

  return (
    <div>
      메시지: <input type="text" ref={msgRef} id="msg" />
      <button onClick={sendMsgHandler}>전송</button>
      {msg}
    </div>
  );
};

export default WebSocketComponent;
