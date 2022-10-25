package org.example;

public class Main {
    public static void main(String[] args) {
        SymbolTable hashT = new SymbolTable(10);
        hashT.addElement("AB");
        hashT.addElement("var");
        hashT.addElement("FG");
        String s = hashT.getElement(2);
        String s1 = hashT.getElement(6);
        Integer i = hashT.getPosition("FG");
        System.out.println(hashT);
        System.out.println(s);
        System.out.println(s1);
        System.out.println(i);
    }
}