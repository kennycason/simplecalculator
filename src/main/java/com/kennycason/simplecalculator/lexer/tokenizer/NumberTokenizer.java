package com.kennycason.simplecalculator.lexer.tokenizer;

import com.kennycason.simplecalculator.lexer.CharacterStream;
import com.kennycason.simplecalculator.lexer.token.NumberToken;
import com.kennycason.simplecalculator.lexer.token.Token;

public class NumberTokenizer {

    public Token tokenize(final CharacterStream tokenStream) {
        final StringBuilder stringBuilder = new StringBuilder();

        final char firstChar = tokenStream.next();

        stringBuilder.append(firstChar);
        while (tokenStream.hasNext()) {
            switch (tokenStream.peek()) {
                case '0': case '1': case '2': case '3': case '4':
                case '5': case '6': case '7': case '8': case '9':
                case '.':
                    stringBuilder.append(tokenStream.next());
                    break;

                default:
                    return new NumberToken(stringBuilder.toString(), NumberToken.Base.TEN);
            }
        }
        return new NumberToken(stringBuilder.toString(), NumberToken.Base.TEN);
    }

}