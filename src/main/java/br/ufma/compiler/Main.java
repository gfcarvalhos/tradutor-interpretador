package br.ufma.compiler;

public class Main {
    public static void main(String[] args) {
        String input = "89+508-7+99*10/2";
        Parser p = new Parser (input.getBytes());
        p.parse();
    }
}