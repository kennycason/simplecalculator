package com.kennycason.simplecalculator.parser.expression;

import com.kennycason.simplecalculator.lexer.token.Token;

public class InfixFunctionExpression implements Expression {

    private final Expression left;
    private final Token function;
    private final Expression right;

    public InfixFunctionExpression(final Expression left,
                                   final Token function,
                                   final Expression right) {
        this.left = left;
        this.function = function;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Token getFunction() {
        return function;
    }

    public Expression getRight() {
        return right;
    }

}