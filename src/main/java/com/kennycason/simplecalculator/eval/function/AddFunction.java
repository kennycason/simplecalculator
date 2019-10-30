package com.kennycason.simplecalculator.eval.function;

import java.math.BigDecimal;

public class AddFunction implements BinaryFunction {

    @Override
    public BigDecimal apply(final BigDecimal left, final BigDecimal right) {
        return left.add(right);
    }

}