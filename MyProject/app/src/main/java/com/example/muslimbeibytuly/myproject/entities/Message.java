package com.example.muslimbeibytuly.myproject.entities;

import java.util.Date;

/**
 * Created by muslimbeibytuly on 11/26/17.
 */

public class Message {
    private String content, to, from;
    private long time;

    public Message() {
    }

    public Message(String content, String to, String from) {
        this.content = content;
        this.to = to;
        this.from = from;
        time = new Date().getTime();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
