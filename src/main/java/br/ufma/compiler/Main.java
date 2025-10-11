package br.ufma.compiler;

public class Main {
    public static void main(String[] args) {
        String input = "let a = 42 + 5;";
        Parser p = new Parser (input.getBytes());
        p.parse();
    }
}