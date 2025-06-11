package org.example.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class LoadSaveManagerTest {

    private LoadSaveManager loadSaveManager;

    @TempDir
    private Path tempDir;

    @BeforeEach
    void setUp() {
        loadSaveManager = new LoadSaveManager();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void loadEmptyFile() throws IOException {
        File emptyFile = tempDir.resolve("empty.txt").toFile();
        assertTrue(emptyFile.createNewFile());
        TokenTable result = loadSaveManager.load(emptyFile.getAbsolutePath());
        assertNotNull(result);
    }

    @Test
    void loadNonExistentFile() {
        File nonExistentFile = tempDir.resolve("doesNotExist.txt").toFile();
        TokenTable result =
                loadSaveManager.load(nonExistentFile.getAbsolutePath());
        assertNotNull(result);
    }

    @Test
    void saveEmptyTokenTable() throws IOException {
        TokenTable emptyTable = new TokenTable();
        File outputFile = tempDir.resolve("empty_output.txt").toFile();
        loadSaveManager.save(outputFile.getAbsolutePath(), emptyTable);
        assertTrue(outputFile.exists());
        List<String> lines = Files.readAllLines(outputFile.toPath());
        assertTrue(lines.isEmpty());
    }

    @Test
    void saveValidTokenTable() throws IOException {
        TokenTable tokenTable = new TokenTable();
        tokenTable.addTokens(
                new ArrayList<>(Arrays.asList("col1", "col2", "col3")));
        tokenTable.addTokens(
                new ArrayList<>(Arrays.asList("val1", "val2", "val3")));
        tokenTable.addTokens(
                new ArrayList<>(Arrays.asList("val4", "val5", "val6")));
        File outputFile = tempDir.resolve("valid_output.txt").toFile();
        loadSaveManager.save(outputFile.getAbsolutePath(), tokenTable);
        assertTrue(outputFile.exists());
        List<String> lines = Files.readAllLines(outputFile.toPath());
        assertEquals(3, lines.size());
    }

    @Test
    void saveToInvalidDirectory() {
        TokenTable tokenTable = new TokenTable();
        tokenTable.addTokens(new ArrayList<>(List.of("test")));
        assertDoesNotThrow(() -> {
            loadSaveManager.save("/nonexistent/directory/file.txt", tokenTable);
        });
    }
}
