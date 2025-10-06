package br.ufma.compiler;

public class Main {
    public static void main(String[] args) {
        //String input = "8+5-7+9/7*2";
        //Parser p = new Parser (input.getBytes());
        //p.parse();
        String input = "289-85+0+69";
        Scanner scan = new Scanner (input.getBytes());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
    }
}