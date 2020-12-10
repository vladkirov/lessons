package com.vladkirov.lessons.examenation03;

import java.util.List;

public interface IOWorker {
    List<SaveGame> getListSaves(String nickName);

    void saveGame(SaveGame game);
}
