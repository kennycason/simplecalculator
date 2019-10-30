package com.kennycason.simplecalculator.lexer.token;

import com.kennycason.simplecalculator.Calculator;
import com.kennycason.simplecalculator.lexer.expression.LexerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionTokenizerTest {

    @Test
    public void basic() {
        TokenTestUtils.assertValid(Calculator.tokenize("1 + 1"),
                new Token("1", TokenType.NUMBER),
                new Token("+", TokenType.PLUS),
                new Token("1", TokenType.NUMBER)
        );

        TokenTestUtils.assertValid(Calculator.tokenize("1 + 1 / 30"),
                new Token("1", TokenType.NUMBER),
                new Token("+", TokenType.PLUS),
                new Token("1", TokenType.NUMBER),
                new Token("/", TokenType.DIVIDE),
                new Token("30", TokenType.NUMBER)
        );

        TokenTestUtils.assertValid(Calculator.tokenize("(1 * (2 + 3))"),
                new Token("(", TokenType.LEFT_PAREN),
                new Token("1", TokenType.NUMBER),
                new Token("*", TokenType.MULTIPLY),
                new Token("(", TokenType.LEFT_PAREN),
                new Token("2", TokenType.NUMBER),
                new Token("+", TokenType.PLUS),
                new Token("3", TokenType.NUMBER),
                new Token(")", TokenType.RIGHT_PAREN),
                new Token(")", TokenType.RIGHT_PAREN)
        );
    }

    // if unknown should throw exception
    @Test
    public void unknownPostfix() {
        assertThrows(LexerException.class, () -> {
            Calculator.tokenize("n#");
        });
    }

}