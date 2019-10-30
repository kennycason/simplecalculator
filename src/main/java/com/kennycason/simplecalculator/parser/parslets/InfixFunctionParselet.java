package com.kennycason.simplecalculator.parser.parslets;

import com.kennycason.simplecalculator.lexer.token.Token;
import com.kennycason.simplecalculator.parser.ExpressionParser;
import com.kennycason.simplecalculator.parser.expression.Expression;
import com.kennycason.simplecalculator.parser.expression.InfixFunctionExpression;

/**
 * Created by kenny on 3/3/16.
 *
 * handle left-associative binary infix functions
 * 1 + 1
 * 1 - 1
 * 1 * 1
 * 1 / 1
 *
 * // not handled, right associative functions, eg:
 * a % 2
 * a ^ 4
 */
public class InfixFunctionParselet implements InfixParselet {

    private final int precedence;

    public InfixFunctionParselet(final int precedence) {
        this.precedence = precedence;
    }

    @Override
    public Expression parse(final ExpressionParser parser, final Expression left, final Token token) {
        final Expression right = parser.parseExpression();
        return new InfixFunctionExpression(left, token, right);
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }

}