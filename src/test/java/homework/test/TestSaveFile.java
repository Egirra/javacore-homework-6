package homework.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static homework.test.Main.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestSaveFile {

    @Test
    @DisplayName("Путь к файлу указан")
    public void correctPath() {
        String path = "C://Games/savegames/" + "save";
        String expectPath = "C://Games/savegames/" + "save";

        assertThat(path, equalTo(expectPath));
    }

    @Test
    @DisplayName("Список пользователей не пустой")
    public void usersListIsFull() {
        List<GameProgress> users = new ArrayList<>();
        users.add(new GameProgress(94, 10, 5, 294));
        users.add(new GameProgress(96, 6, 3, 256));
        users.add(new GameProgress(74, 3, 2, 201));

        assertThat(users, not(empty()));
    }

    @Test
    @DisplayName("Данные успешно сохранены")
    public void successSaveGame() {
        String path = "C://Games/savegames/" + "save";
        List<GameProgress> users = new ArrayList<>();
        users.add(new GameProgress(94, 10, 5, 294));

        Assertions.assertDoesNotThrow(() -> saveGame(path, users.get(0)));
    }

    @Test
    @DisplayName("Данные успешно заархивированы")
    public void successZip() {
        String zipSave = "C://Games/savegames/" + "save.zip";
        String path = "C://Games/savegames/" + "save";
        String nameFile = "save0.dat";

        Assertions.assertDoesNotThrow(() -> zipFiles(zipSave, path, nameFile));
    }

    @Test
    @DisplayName("Данные успешно удалены")
    public void successDeleteFiles() {
        File file = new File("C://Games/savegames/" + "save.dat");

        Assertions.assertDoesNotThrow(() -> deleteFiles(file));
    }
}