package com.vladkirov.lessons.examenation03;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveGame implements Serializable {
    private final String nickName;
    private final String state;
    LocalDateTime dateTimeSave;

    public SaveGame(String nickName, String state, LocalDateTime dateTimeSave) {
        this.nickName = nickName;
        this.state = state;
        this.dateTimeSave = dateTimeSave;
    }

    public String getName() {
        return nickName + "_" + dateTimeSave.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }

    public String getState() {
        return state;
    }

    public LocalDateTime getDateTimeSave() {
        return dateTimeSave;
    }

    public String getNickName() {
        return nickName;
    }
}
