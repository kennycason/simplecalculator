package com.kennycason.simplecalculator.eval.function;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivideFunction implements BinaryFunction {

    @Override
    public BigDecimal apply(final BigDecimal left, final BigDecimal right) {
        if (right.equals(BigDecimal.ZERO)) {
            throw new ArithmeticException("Can not divide by zero");
        }
        return left.divide(right, RoundingMode.HALF_UP);
    }

}