package com.kennycason.simplecalculator.parser.parslets;

import com.kennycason.simplecalculator.lexer.token.Token;
import com.kennycason.simplecalculator.parser.ExpressionParser;
import com.kennycason.simplecalculator.parser.expression.Expression;

public interface InfixParselet {
    Expression parse(ExpressionParser parser, Expression left, Token token);
    int getPrecedence();
}