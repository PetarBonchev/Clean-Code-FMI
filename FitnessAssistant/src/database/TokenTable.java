package org.example.database;

import java.util.ArrayList;

public final class TokenTable {
    private final ArrayList<ArrayList<String>> tokens;

    public ArrayList<ArrayList<String>> getTokens() {
        return tokens;
    }

    public void addTokens(ArrayList<String> newTokens) {
        this.tokens.add(newTokens);
    }

    public static final char TOKEN_SIZE_CONTENT_SEPARATOR = ':';

    public TokenTable() {
        this.tokens = new ArrayList<>();
    }

    public static ArrayList<String> convertLineToTokens(String line) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder countSymbols = new StringBuilder();
        int i = 0;
        while (i < line.length()) {
            char currentChar = line.charAt(i);
            if (currentChar == TOKEN_SIZE_CONTENT_SEPARATOR) {
                int symbolsInToken = Integer.parseInt(countSymbols.toString());
                countSymbols.setLength(0);
                i++;
                if (i + symbolsInToken > line.length() || symbolsInToken < 0) {
                    throw new IllegalArgumentException("Token size mismatch.");
                }
                String token = line.substring(i, i + symbolsInToken);
                tokens.add(token);
                i += symbolsInToken;
            } else {
                countSymbols.append(currentChar);
            }
            i++;
        }
        if (!countSymbols.isEmpty()) {
            throw new IllegalArgumentException(
                    "Detected symbols outside the tokens.");
        }
        return tokens;
    }

    public ArrayList<String> convertToLines() {
        ArrayList<String> lines = new ArrayList<>();

        for (ArrayList<String> tokenLine : tokens) {
            StringBuilder line = new StringBuilder();
            for (String token : tokenLine) {
                line.append(token.length()).
                        append(TOKEN_SIZE_CONTENT_SEPARATOR).
                        append(token).append(" ");
            }
            lines.add(line.toString());
        }

        return lines;
    }
}
