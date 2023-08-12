import { useEffect } from "react";
import Stomp from "stompjs";
import SockJs from "sockjs-client";

const WebSocket = () => {
  const sock = new SockJs("http://localhost:8080/api/chat");

  useEffect(() => {
    // WebSocket 연결 설정
    const socket = new WebSocket("ws://day6");

    // STOMP 프로토콜 설정
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {
      console.log("Connected:", frame);

      // STOMP 프로토콜을 사용한 구독
      stompClient.subscribe("/topic/someTopic", (message) => {
        console.log("Received message:", message.body);
      });
    });

    return () => {
      stompClient.disconnect(); // 컴포넌트 언마운트 시 연결 종료
    };
  }, []);

  return <div>{/* 컴포넌트 렌더링 */}</div>;
};

export default WebSocket;
