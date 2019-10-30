package com.kennycason.simplecalculator.lexer.token;

import com.kennycason.simplecalculator.lexer.expression.EndOfStreamException;

import java.util.ArrayList;
import java.util.List;

/**
 * After we have parses characters into tokens,
 * this class helps us easily process through our tokens.
 */
public class TokenStream {

    private final List<Token> tokens = new ArrayList<>();

    private int currentToken;

    public TokenStream(final List<Token> tokens) {
        this.tokens.addAll(tokens);
        currentToken = 0;
    }

    public Token next() {
        final Token next = peek();
        currentToken++;
        return next;
    }

    public Token peek() {
        if (currentToken == tokens.size()) {
            throw new EndOfStreamException();
        }
        return tokens.get(currentToken);
    }

    public boolean hasNext() {
        return currentToken < tokens.size();
    }

    public int getPosition() {
        return currentToken;
    }

    public void reset() {
        currentToken = 0;
    }

}