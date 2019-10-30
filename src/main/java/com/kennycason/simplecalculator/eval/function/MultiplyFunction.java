package com.kennycason.simplecalculator.eval.function;

import java.math.BigDecimal;

public class MultiplyFunction implements BinaryFunction {

    @Override
    public BigDecimal apply(final BigDecimal left, final BigDecimal right) {
        return left.multiply(right);
    }

}