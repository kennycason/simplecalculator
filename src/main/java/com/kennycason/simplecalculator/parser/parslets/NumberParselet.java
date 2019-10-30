package com.kennycason.simplecalculator.parser.parslets;

import com.kennycason.simplecalculator.lexer.token.NumberToken;
import com.kennycason.simplecalculator.lexer.token.Token;
import com.kennycason.simplecalculator.parser.ExpressionParser;
import com.kennycason.simplecalculator.parser.expression.Expression;
import com.kennycason.simplecalculator.parser.expression.NumberExpression;

import java.math.BigDecimal;

public class NumberParselet implements PrefixParselet {

    @Override
    public Expression parse(final ExpressionParser parser, final Token token) {
        final NumberToken numberToken = (NumberToken) token;

        return new NumberExpression(new BigDecimal(numberToken.getValue()));
    }

}