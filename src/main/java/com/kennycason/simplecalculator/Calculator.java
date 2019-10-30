package com.kennycason.simplecalculator;

import com.kennycason.simplecalculator.eval.ExpressionEvaluator;
import com.kennycason.simplecalculator.lexer.CharacterStream;
import com.kennycason.simplecalculator.lexer.token.Token;
import com.kennycason.simplecalculator.lexer.token.TokenStream;
import com.kennycason.simplecalculator.lexer.tokenizer.ExpressionTokenizer;
import com.kennycason.simplecalculator.parser.ExpressionParser;
import com.kennycason.simplecalculator.parser.expression.Expression;

import java.util.List;

// a simple helper class to make lexing/parsing/evaluating expressions easier
public class Calculator {

    public static List<Token> tokenize(final String expression) {
        return new ExpressionTokenizer().tokenize(new CharacterStream(expression));
    }

    public static Expression parse(final String expression) {
        final ExpressionParser parser = new ExpressionParser(new TokenStream(tokenize(expression)));
        return parser.parseExpression();
    }

    public static Expression evaluate(final String expression) {
        final ExpressionParser parser = new ExpressionParser(new TokenStream(tokenize(expression)));
        return new ExpressionEvaluator().evaluate(parser.parseExpression());
    }

}
