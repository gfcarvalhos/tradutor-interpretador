package br.ufma.compiler;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final Scanner scan;
    private Token currentToken;
    private final List<String> instructions = new ArrayList<>();

    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken();
    }

    private void nextToken() {
        currentToken = scan.nextToken();
    }

    private void emit(String instr) {
        instructions.add(instr);
    }

    public List<String> output() {
        return instructions;
    }

    void letStatement() {
        match(TokenType.LET);
        var id = currentToken.lexeme;
        match(TokenType.IDENT);
        match(TokenType.EQ);
        expr();
        emit("pop " + id);
        match(TokenType.SEMICOLON);
    }

    void printStatement() {
        match(TokenType.PRINT);
        expr();
        emit("print");
        match(TokenType.SEMICOLON);
    }

    void statement() {
        if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else if (currentToken.type == TokenType.LET) {
            letStatement();
        } else {
            throw new Error("syntax error");
        }
    }

    void statements() {
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    public void parse() {
        statements();
    }

    private void match(TokenType t) {
        if (currentToken.type == t) {
            nextToken();
        } else {
            throw new Error("syntax error");
        }
    }

    void expr() {
        term();
        oper();
    }

    void number() {
        emit("push " + currentToken.lexeme);
        match(TokenType.NUMBER);
    }

    void term() {
        if (currentToken.type == TokenType.NUMBER) number();
        else if (currentToken.type == TokenType.IDENT) {
            emit("push " + currentToken.lexeme);
            match(TokenType.IDENT);
        } else {
            throw new Error("syntax error");
        }
    }

    void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            term();
            emit("add");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term();
            emit("sub");
            oper();
        } else if (currentToken.type == TokenType.MULT) {
            match(TokenType.MULT);
            term();
            emit("mult");
            oper();
        } else if (currentToken.type == TokenType.DIV) {
            match(TokenType.DIV);
            term();
            emit("div");
            oper();
        }
    }
}
