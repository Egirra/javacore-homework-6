package homework.test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static String path = "C://Games/savegames/" + "Save";

    public static void main(String[] args) {
        List<GameProgress> users = Arrays.asList(
                new GameProgress(94, 10, 5, 294),
                new GameProgress(96, 6, 3, 256),
                new GameProgress(74, 3, 2, 201)
        );

        List<String> savePaths = new ArrayList<>();
        for (int x = 0; x < users.size(); x++) {
            savePaths.add(path + (x) + ".dat");
        }

        for (int i = 0; i < users.size(); i++) {
            saveGame(savePaths.get(i), users.get(i));
            zipFiles(path + (i) + ".zip", savePaths.get(i), "save" + (i) + ".dat");
            deleteFiles(new File(savePaths.get(i)));
        }

    }

    public static void saveGame(String path, GameProgress user) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(user);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipSave, String path, String nameFile) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipSave));
             FileInputStream fis = new FileInputStream(path)) {
            ZipEntry entry = new ZipEntry(nameFile);
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteFiles(File file) {
        try {
            file.delete();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}