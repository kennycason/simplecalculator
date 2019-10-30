package com.kennycason.simplecalculator.lexer.parser;

import com.kennycason.simplecalculator.lexer.CharacterStream;
import com.kennycason.simplecalculator.lexer.token.Token;
import com.kennycason.simplecalculator.lexer.token.TokenStream;
import com.kennycason.simplecalculator.lexer.token.TokenType;
import com.kennycason.simplecalculator.lexer.tokenizer.ExpressionTokenizer;
import com.kennycason.simplecalculator.parser.ExpressionParser;
import com.kennycason.simplecalculator.parser.expression.Expression;
import com.kennycason.simplecalculator.parser.expression.InfixFunctionExpression;
import com.kennycason.simplecalculator.parser.expression.NumberExpression;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpressionParserTest {

    @Test
    public void infixFunction() {
        final List<Token> tokens = new ExpressionTokenizer().tokenize(new CharacterStream("1 + 1"));
        final ExpressionParser parser = new ExpressionParser(new TokenStream(tokens));
        final Expression expression = parser.parseExpression();

        final InfixFunctionExpression infixFunctionExpression = (InfixFunctionExpression) expression;

        assertEquals(new BigDecimal(1), ((NumberExpression) infixFunctionExpression.getLeft()).getValue());

        assertEquals(TokenType.PLUS, infixFunctionExpression.getFunction().getType());
        assertEquals("+", infixFunctionExpression.getFunction().getValue());

        assertEquals(new BigDecimal(1), ((NumberExpression) infixFunctionExpression.getRight()).getValue());
    }

    @Test
    public void nestedInfixFunctions() {
        final List<Token> tokens = new ExpressionTokenizer().tokenize(new CharacterStream("(1 + (2 * 3))"));
        final ExpressionParser parser = new ExpressionParser(new TokenStream(tokens));
        final Expression expression = parser.parseExpression();

        final InfixFunctionExpression infixFunctionExpression = (InfixFunctionExpression) expression;
        assertEquals(new BigDecimal(1), ((NumberExpression) infixFunctionExpression.getLeft()).getValue());

        assertEquals(TokenType.PLUS, infixFunctionExpression.getFunction().getType());
        assertEquals("+", infixFunctionExpression.getFunction().getValue());

        assertTrue(infixFunctionExpression.getRight() instanceof InfixFunctionExpression);

        final InfixFunctionExpression rightInfixFunctionExpression = (InfixFunctionExpression) ((InfixFunctionExpression) expression).getRight();

        assertEquals(new BigDecimal(2), ((NumberExpression) rightInfixFunctionExpression.getLeft()).getValue());

        assertEquals(TokenType.MULTIPLY, rightInfixFunctionExpression.getFunction().getType());
        assertEquals("*", rightInfixFunctionExpression.getFunction().getValue());

        assertEquals(new BigDecimal(3), ((NumberExpression) rightInfixFunctionExpression.getRight()).getValue());
    }

}
