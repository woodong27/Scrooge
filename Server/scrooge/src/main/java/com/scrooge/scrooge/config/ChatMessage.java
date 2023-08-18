package com.scrooge.scrooge.config;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {

    private String content;
    private String sender;
    private MessageType type;

    @Builder
    public ChatMessage(String content, String sender, MessageType type) {
        this.content = content;
        this.sender = sender;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
