package com.kennycason.simplecalculator.lexer.tokenizer;

import com.kennycason.simplecalculator.lexer.CharacterStream;
import com.kennycason.simplecalculator.lexer.expression.LexerException;
import com.kennycason.simplecalculator.lexer.token.Token;
import com.kennycason.simplecalculator.lexer.token.TokenType;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTokenizer {
    private final NumberTokenizer numberTokenizer = new NumberTokenizer();

    public List<Token> tokenize(final CharacterStream tokenStream) {
        final List<Token> tokens = new ArrayList<>();

        while (tokenStream.hasNext()) {
            final char token = Character.toLowerCase(tokenStream.peek());

            switch (token) {
                case '(':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.LEFT_PAREN));
                    continue;
                case ')':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.RIGHT_PAREN));
                    continue;
                case '+':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.PLUS));
                    continue;
                case '-':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.MINUS));
                    continue;
                case '*':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.MULTIPLY));
                    continue;
                case '/':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.DIVIDE));
                    continue;
                case ' ':
                    tokenStream.next(); // ignore token
                    continue;
            }
            if (Character.isDigit(token)
                        || token == '.') {
                tokens.add(numberTokenizer.tokenize(tokenStream));
            }
            else {
                throw new LexerException("Found unrecognized character [" + token + "]");
            }
        }

        return tokens;
    }


}