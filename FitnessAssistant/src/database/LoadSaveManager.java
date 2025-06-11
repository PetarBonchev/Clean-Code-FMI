package org.example.database;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public final class LoadSaveManager {

    public LoadSaveManager() {
    }

    public TokenTable load(String filename) {
        TokenTable tokenTable = new TokenTable();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> tokens = TokenTable.convertLineToTokens(line);
                tokenTable.addTokens(tokens);
            }
        } catch (IOException e) {
        }
        return tokenTable;
    }

    public void save(String filename, TokenTable tokenTable) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(filename))) {
            ArrayList<String> lines = tokenTable.convertToLines();
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
