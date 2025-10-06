package br.ufma.compiler;

public class Main {
    public static void main(String[] args) {
        //String input = "89 +508-7+99*10/2";
        //Parser p = new Parser (input.getBytes());
        //p.parse();

        String input = "45  + preco - 876";
        Scanner scan = new Scanner (input.getBytes());
        for (Token tk = scan.nextToken(); tk.type != TokenType.EOF; tk = scan.nextToken()) {
            System.out.println(tk);
        }

    }
}