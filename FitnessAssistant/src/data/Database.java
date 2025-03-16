package data;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Database {

    public static TokenTable load(String filename) {
        TokenTable tokenTable = new TokenTable();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> tokens = TokenTable.convertLineToTokens(line);
                tokenTable.tokens.add(tokens);
            }
        } catch (IOException _) {
        }
        return tokenTable;
    }

    public static void save(String filename, TokenTable tokenTable) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
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
