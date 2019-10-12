package com.yk.bike.pojo;

public class ChatRoom {
    private int id;
    private String roomId;
    private String fristId;
    private String secondId;

    public int getId() {
        return id;
    }

    public ChatRoom setId(int id) {
        this.id = id;
        return this;
    }

    public String getRoomId() {
        return roomId;
    }

    public ChatRoom setRoomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    public String getFristId() {
        return fristId;
    }

    public ChatRoom setFristId(String fristId) {
        this.fristId = fristId;
        return this;
    }

    public String getSecondId() {
        return secondId;
    }

    public ChatRoom setSecondId(String secondId) {
        this.secondId = secondId;
        return this;
    }
}
