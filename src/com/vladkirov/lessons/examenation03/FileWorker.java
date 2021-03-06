package com.vladkirov.lessons.examenation03;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Realization Strategy "Download/uplod save with files"
 */
public class FileWorker implements IOWorker {
    /**
     * Path to saves from local project directory
     */
    private final String path;
    /**
     * Extensions for load file saves
     */
    private static final String extension = ".svfa";

    public FileWorker() {
        this.path = "sources/saves/";
    }

    @Override
    public List<SaveGame> getListSaves(String nickName) {
        try {
            return Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(extension))
                    .map(Path::toFile)
                    .map((this::loadObject)).filter(Objects::nonNull)
                    .filter((saveGame -> saveGame.getNickName().equals(nickName)))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveGame(SaveGame saveGame) {
        File file = new File(path + saveGame.getName() + extension);
        try (ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectStream.writeObject(saveGame);
            System.out.println("Save <" + saveGame.getName() + "> saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load concrete object in Stream
     * @param file File object
     * @return Save game object
     */
    private SaveGame loadObject(File file) {
        try (ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(file))) {
            return (SaveGame) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
