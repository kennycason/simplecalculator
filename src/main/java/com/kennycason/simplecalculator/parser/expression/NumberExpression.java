package com.kennycason.simplecalculator.parser.expression;

import java.math.BigDecimal;

public class NumberExpression implements Expression {

    private final BigDecimal number;

    public NumberExpression(final BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getValue() {
        return number;
    }

}