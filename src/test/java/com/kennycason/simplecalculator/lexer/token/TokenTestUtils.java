package com.kennycason.simplecalculator.lexer.token;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenTestUtils {

    public static void assertValid(final List<Token> actual, final Token... expected) {
        assertEquals(expected.length, actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected[i].getType(), actual.get(i).getType());
            assertEquals(expected[i].getValue(), actual.get(i).getValue());
        }
    }

    public static void assertValid(final Token actual, final Token expected) {
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getValue(), actual.getValue());
    }
}