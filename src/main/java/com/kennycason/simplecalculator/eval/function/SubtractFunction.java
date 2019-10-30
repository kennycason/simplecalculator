package com.kennycason.simplecalculator.eval.function;

import java.math.BigDecimal;

public class SubtractFunction implements BinaryFunction {

    @Override
    public BigDecimal apply(final BigDecimal left, final BigDecimal right) {
        return left.subtract(right);
    }

}