package com.kennycason.simplecalculator.parser;

import com.kennycason.simplecalculator.lexer.expression.EndOfStreamException;
import com.kennycason.simplecalculator.lexer.token.Token;
import com.kennycason.simplecalculator.lexer.token.TokenStream;
import com.kennycason.simplecalculator.lexer.token.TokenType;
import com.kennycason.simplecalculator.parser.exception.ParserException;
import com.kennycason.simplecalculator.parser.expression.Expression;
import com.kennycason.simplecalculator.parser.parslets.*;

import java.util.HashMap;
import java.util.Map;

public class ExpressionParser {
    private final Map<TokenType, PrefixParselet> prefixParselets = new HashMap<>();
    private final Map<TokenType, InfixParselet> infixParselets = new HashMap<>();

    private final TokenStream tokenStream;

    public ExpressionParser(final TokenStream tokenStream) {
        this.tokenStream = tokenStream;

        // prefix functions like (...) and simple numbers
        register(TokenType.NUMBER, new NumberParselet());
        register(TokenType.LEFT_PAREN, new ParenthesisGroupParselet());

        // infix binary operators
        register(TokenType.PLUS, new InfixFunctionParselet(Precedence.PLUS));
        register(TokenType.MINUS, new InfixFunctionParselet(Precedence.PLUS));
        register(TokenType.MULTIPLY, new InfixFunctionParselet(Precedence.MULTIPLY));
        register(TokenType.DIVIDE, new InfixFunctionParselet(Precedence.MULTIPLY));
    }

    public Expression parseExpression() {
        return parseExpression(0);
    }

    public Expression parseExpression(final int precedence) {
        if (!tokenStream.hasNext()) {
            throw new EndOfStreamException();
        }

        final Token function = tokenStream.next();

        if (!prefixParselets.containsKey(function.getType())) {
            throw new ParserException("Unable to find prefix parselet for: " + function.getType());
        }
        final PrefixParselet prefixParselet = prefixParselets.get(function.getType());
        Expression expression = prefixParselet.parse(this, function);

        while (tokenStream.hasNext()
                && precedence < getPrecedence(tokenStream.peek())) {
            final Token token = tokenStream.next();
            if (!infixParselets.containsKey(token.getType())) {
                throw new ParserException("Unable to find infix parselet for: " + function.getType());
            }

            final InfixParselet infixParselet = infixParselets.get(token.getType());
            expression = infixParselet.parse(this, expression, token);
        }

        return expression;
    }

    public Integer getPrecedence(final Token token) {
        final InfixParselet infixParselet = infixParselets.get(token.getType());
        if (infixParselet == null) {
            return 0;
        }
        return infixParselet.getPrecedence();
    }

    public Token next() {
        return tokenStream.next();
    }

    public Token peek() { return tokenStream.peek(); }

    public void register(final TokenType tokenType, final PrefixParselet prefixParselet) {
        prefixParselets.put(tokenType, prefixParselet);
    }

    public void register(final TokenType tokenType, final InfixParselet infixParselet) {
        infixParselets.put(tokenType, infixParselet);
    }

}