# Simple Calculator

A simple calculator that supports basic arithmetic (+,-,*,/) and parenthesis.

- negation is not supported yet.
- supports infix notation (a + b)
- demonstrates simple lexer/parser usage
- evaluation class which operates on a parsed expression.

# Basic algorithm:

### 1. tokenize expression string to a list of Tokens.
- e.g. "1 + 1" -> [Token{"1", NUMBER}, Token{"+", PLUS}, Token{"1", NUMBER}]
- A token stream is easier for the parser to consume than consuming a raw string

### 2. a parser then consumes the list of tokens.
- list of tokens is also wrapped in a TokenStream class to facilitate use.
- the parser converse a list of tokens to an expression tree.
- the parser is aware of function precedence. i.e. PEMDAS.
- the parse only works with infix functions currently (a + b)

### 3. the evaluator evaluates/reduces the expression as far as possible.
- the evaluator consumes a string and parses it into an expression tree.
- the expression tree is then computed/reduced as far as possible.


