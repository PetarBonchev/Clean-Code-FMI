package org.example.database;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TokenTableTest {

    @Test
    void testConvertLineToTokensSingleToken() {
        String line = "5:Awake";
        ArrayList<String> expectedTokens = new ArrayList<>(List.of(
                "Awake"));
        assertEquals(expectedTokens, TokenTable.convertLineToTokens(line));
    }

    @Test
    void testConvertLineToTokensMultipleTokens() {
        String line = "5:Awake 4:Kite";
        ArrayList<String> expectedTokens = new ArrayList<>(List.of(
                "Awake", "Kite"));
        assertEquals(expectedTokens, TokenTable.convertLineToTokens(line));
    }

    @Test
    void testConvertLineToTokensNoNumber() {
        String line = "Awake";
        assertThrows(IllegalArgumentException.class, () ->
                TokenTable.convertLineToTokens(line));
    }

    @Test
    void testConvertLineToTokensMoreSymbols() {
        String line = "5:Awake 4:Kiteee";
        assertThrows(IllegalArgumentException.class, () ->
                TokenTable.convertLineToTokens(line));
    }

    @Test
    void testConvertLineToTokensLessSymbols() {
        String line = "5:Awae 4:Kit";
        assertThrows(IllegalArgumentException.class, () ->
                TokenTable.convertLineToTokens(line));
    }

    @Test
    void testConvertLineToTokensEmptyLine() {
        String line = "";
        ArrayList<String> expectedTokens = new ArrayList<>();
        assertEquals(expectedTokens, TokenTable.convertLineToTokens(line));
    }

    @Test
    void testConvertLineToTokensMissingTokenAfterLength() {
        String line = "5:";
        assertThrows(IllegalArgumentException.class, () ->
                TokenTable.convertLineToTokens(line));
    }

    @Test
    void testConvertLineToTokensNegativeTokenLength() {
        String line = "-5:Hello";
        assertThrows(IllegalArgumentException.class, () ->
                TokenTable.convertLineToTokens(line));
    }

    @Test
    void testConvertLineToTokensNonNumericTokenLength() {
        String line = "abc:Hello";
        assertThrows(IllegalArgumentException.class, () ->
                TokenTable.convertLineToTokens(line));
    }

    @Test
    void testConvertLineToTokensIncompleteTokenAtEnd() {
        String line = "5:Hello10";
        assertThrows(IllegalArgumentException.class, () ->
                TokenTable.convertLineToTokens(line));
    }

    @Test
    void testConvertLineToTokensMultipleSeparators() {
        String line = "5:Hello::World";
        assertThrows(IllegalArgumentException.class, () ->
                TokenTable.convertLineToTokens(line));
    }

    @Test
    void testConvertToLinesSingleToken() {
        TokenTable tokenTable = new TokenTable();
        tokenTable.addTokens(new ArrayList<>(List.of("Hello")));
        ArrayList<String> expectedLines = new ArrayList<>(List.of(
                "5:Hello "));
        assertEquals(expectedLines, tokenTable.convertToLines());
    }

    @Test
    void testConvertToLinesMultipleTokens() {
        TokenTable tokenTable = new TokenTable();
        tokenTable.addTokens(new ArrayList<>(List.of("Hello", "World")));
        ArrayList<String> expectedLines = new ArrayList<>(List.of(
                "5:Hello 5:World "));
        assertEquals(expectedLines, tokenTable.convertToLines());
    }

    @Test
    void testConvertToLinesMultipleLines() {
        TokenTable tokenTable = new TokenTable();
        tokenTable.addTokens(new ArrayList<>(List.of("Hello", "World")));
        tokenTable.addTokens(new ArrayList<>(List.of("foo", "bar")));
        ArrayList<String> expectedLines = new ArrayList<>(List.of(
                "5:Hello 5:World ", "3:foo 3:bar "));
        assertEquals(expectedLines, tokenTable.convertToLines());
    }

    @Test
    void testConvertToLinesEmptyTokenTable() {
        TokenTable tokenTable = new TokenTable();
        ArrayList<String> expectedLines = new ArrayList<>();
        assertEquals(expectedLines, tokenTable.convertToLines());
    }

    @Test
    void testConvertToLinesTokensWithSpaces() {
        TokenTable tokenTable = new TokenTable();
        tokenTable.addTokens(new ArrayList<>(List.of("Hello World", "foo")));
        ArrayList<String> expectedLines = new ArrayList<>(List.of(
                "11:Hello World 3:foo "));
        assertEquals(expectedLines, tokenTable.convertToLines());
    }

    @Test
    void testConvertToLinesEmptyTokens() {
        TokenTable tokenTable = new TokenTable();
        tokenTable.addTokens(new ArrayList<>(List.of("", "")));
        ArrayList<String> expectedLines = new ArrayList<>(List.of(
                "0: 0: "));
        assertEquals(expectedLines, tokenTable.convertToLines());
    }
}
