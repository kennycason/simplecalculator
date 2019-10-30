package com.kennycason.simplecalculator.lexer;

import com.kennycason.simplecalculator.lexer.expression.EndOfStreamException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterStreamTest {

    @Test
    public void basicTest() {
        final CharacterStream tokenStream = new CharacterStream("abc");
        assertTrue(tokenStream.hasNext());
        assertEquals('a', tokenStream.peek());
        assertEquals('a', tokenStream.peek()); // peeking does not increment pointer
        assertEquals('a', tokenStream.next()); // get next token and increment internal pointer
        assertEquals('b', tokenStream.peek()); // verify pointer was incremented
        assertEquals('b', tokenStream.next()); // get next
        assertEquals('c', tokenStream.next()); // get next
        assertFalse(tokenStream.hasNext());    // stream should be consumed
    }

    @Test
    public void endOfStream() {
        final CharacterStream tokenStream = new CharacterStream("a");
        tokenStream.next();
        assertFalse(tokenStream.hasNext());
        assertThrows(EndOfStreamException.class, () -> {
            tokenStream.next(); // should throw exception
        });
    }

    @Test
    public void reset() {
        final CharacterStream tokenStream = new CharacterStream("a");
        tokenStream.next();
        assertFalse(tokenStream.hasNext());
        tokenStream.reset();
        assertTrue(tokenStream.hasNext());
        assertEquals('a', tokenStream.peek());
    }

}