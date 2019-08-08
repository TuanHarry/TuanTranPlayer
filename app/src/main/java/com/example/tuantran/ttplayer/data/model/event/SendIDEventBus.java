package com.example.tuantran.ttplayer.data.model.event;

public class SendIDEventBus {
    String ID;
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public SendIDEventBus(String ID, String key) {
        this.ID = ID;
        this.key = key;
    }
}
