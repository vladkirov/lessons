package com.vladkirov.lessons.examenation03;

import java.util.List;

/**
 * Pattern Strategy for download/upload saves
 */
public interface IOWorker {
    /**
     * Get array list objects, that can be loaded in game
     * @param nickName Will load saves only for this nickname
     * @return Array list objects "SaveGame"
     */
    List<SaveGame> getListSaves(String nickName);

    /**
     * Save current game on now() time
     * @param game Save-object with state of current game
     */
    void saveGame(SaveGame game);
}
