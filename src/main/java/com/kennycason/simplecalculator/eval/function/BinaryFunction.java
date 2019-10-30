package com.kennycason.simplecalculator.eval.function;

import java.math.BigDecimal;

public interface BinaryFunction {
    BigDecimal apply(BigDecimal left, BigDecimal right);
}