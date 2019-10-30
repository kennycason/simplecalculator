package com.kennycason.simplecalculator.lexer.eval;

import com.kennycason.simplecalculator.Calculator;
import com.kennycason.simplecalculator.parser.expression.Expression;
import com.kennycason.simplecalculator.parser.expression.NumberExpression;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpressionEvaluatorTest {

    @Test
    public void basicNumber() {
        final Expression expression = Calculator.evaluate("10");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigDecimal(10), ((NumberExpression) expression).getValue());
    }

    @Test
    public void arithmetic() {
        final Expression expression = Calculator.evaluate("10 / 2");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigDecimal(5), ((NumberExpression) expression).getValue());
    }

    @Test
    public void arithmetic2() {
        final Expression expression = Calculator.evaluate("((10 * 2) / 4)");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigDecimal(5), ((NumberExpression) expression).getValue());
    }

    @Test
    public void arithmetic3() {
        final Expression expression = Calculator.evaluate("(10 * 2) / (5 + 5)");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigDecimal(2), ((NumberExpression) expression).getValue());
    }

    @Test
    public void multiplyVsAddPrecedence() {
        final Expression expression = Calculator.evaluate("10 + 2 * 5");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigDecimal(20), ((NumberExpression) expression).getValue());
    }

}