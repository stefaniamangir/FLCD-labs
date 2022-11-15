package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        SymbolTable idhashT = new SymbolTable(10);
        SymbolTable consthashT = new SymbolTable(10);
        /*hashT.addElement("AB");
        hashT.addElement("var");
        hashT.addElement("FG");
        String s = hashT.getElement(2);
        String s1 = hashT.getElement(6);
        Integer i = hashT.getPosition("FG");
        System.out.println(hashT);
        System.out.println(s);
        System.out.println(s1);
        System.out.println(i);*/
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(idhashT, consthashT);
        lexicalAnalyzer.scan("input/p2.txt");
        writeSymbolTableToFile(idhashT, "ID_ST.out");
        writeSymbolTableToFile(consthashT, "C_ST.out");
        List<PairType<String, Integer>> programInternalForm = lexicalAnalyzer.getPIF();
        writeProgramInternalFormToFile(programInternalForm, "PIF.out");
    }

    private static void writeProgramInternalFormToFile(List<PairType<String, Integer>> programInternalForm, String filename) {
        Formatter formatter = new Formatter();
        formatter.format("%-32s%-32s\n", "Token:", "ST position:");
        programInternalForm.forEach(pair -> formatter.format("%-32s%-32s\n", pair.getToken(), pair.getSt_position()));

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(formatter.toString());

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e);
        }
    }

    private static void writeSymbolTableToFile(SymbolTable symbolTable, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            String symbolTableDoc = "Symbol Table:\n";
            writer.write(String.format("%s\n%s", symbolTableDoc, symbolTable.toString()));

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e);
        }
    }
}