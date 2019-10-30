package com.kennycason.simplecalculator.parser.parslets;

import com.kennycason.simplecalculator.lexer.token.Token;
import com.kennycason.simplecalculator.lexer.token.TokenType;
import com.kennycason.simplecalculator.parser.ExpressionParser;
import com.kennycason.simplecalculator.parser.exception.ParserException;
import com.kennycason.simplecalculator.parser.expression.Expression;

public class ParenthesisGroupParselet implements PrefixParselet {

    @Override
    public Expression parse(final ExpressionParser parser, final Token token) {
        final Expression expression = parser.parseExpression();

        final Token next = parser.next();

        if (next.getType() != TokenType.RIGHT_PAREN) {
            throw new ParserException("Expected to find a ')', found " + next.getType());
        }

        return expression;
    }

}