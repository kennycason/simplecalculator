package com.kennycason.simplecalculator.eval;

import com.kennycason.simplecalculator.eval.function.*;
import com.kennycason.simplecalculator.lexer.token.Token;
import com.kennycason.simplecalculator.parser.expression.Expression;
import com.kennycason.simplecalculator.parser.expression.InfixFunctionExpression;
import com.kennycason.simplecalculator.parser.expression.NumberExpression;

import java.util.HashMap;
import java.util.Map;

public class ExpressionEvaluator {
    private static final Map<String, BinaryFunction> BINARY_FUNCTIONS = new HashMap<>();
    static {
        BINARY_FUNCTIONS.put("*", new MultiplyFunction());
        BINARY_FUNCTIONS.put("/", new DivideFunction());
        BINARY_FUNCTIONS.put("+", new AddFunction());
        BINARY_FUNCTIONS.put("-", new SubtractFunction());
    }

    public Expression evaluate(final Expression expression) {
        if (expression instanceof NumberExpression) {
            return expression;
        }
        else if (expression instanceof InfixFunctionExpression) {
            return evaluate((InfixFunctionExpression) expression);
        }
        throw new IllegalStateException("Unhandled expression type: " + expression.getClass());
    }

    private Expression evaluate(final InfixFunctionExpression expression) {
        final Expression evaulatedLeftExpression = evaluate(expression.getLeft());
        final Expression evaulatedRightExpression = evaluate(expression.getRight());

        final boolean leftIsNumberExpression = evaulatedLeftExpression instanceof NumberExpression;
        final boolean rightIsNumberExpression = evaulatedRightExpression instanceof NumberExpression;

        if (leftIsNumberExpression && rightIsNumberExpression) {
            return evaluateBinaryInfixFunction(expression.getFunction(), (NumberExpression) evaulatedLeftExpression, (NumberExpression) evaulatedRightExpression);
        }
        // if we can't resolve to number, return unsolved expression
        return new InfixFunctionExpression(evaulatedLeftExpression, expression.getFunction(), evaulatedRightExpression);
    }

    private static NumberExpression evaluateBinaryInfixFunction(final Token function, final NumberExpression left, final NumberExpression right) {
        final BinaryFunction binaryFunction = BINARY_FUNCTIONS.get(function.getValue());
        if (binaryFunction == null) {
            throw new EvaluationException("Function [" + function.getValue() + "] does not exist");
        }
        return new NumberExpression(binaryFunction.apply(left.getValue(), right.getValue()));
    }

}