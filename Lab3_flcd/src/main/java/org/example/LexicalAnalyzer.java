package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LexicalAnalyzer {
    private SymbolTable identifiersSymbolTable;
    private SymbolTable constantsSymbolTable;

    private List<PairType<String, Integer>> pif = new ArrayList<>();
    private boolean lexical_error;

    private static final List<String> RESERVED_WORDS = Arrays.asList("if", "else", "for", "while", "const", "number",
            "string", "arr", "input", "print", "number");
    private static final List<String> OPERATORS = Arrays.asList("+", "-", "*", "=", "==", "<", ">","<=", ">=",
            "%", "//", "!=", "!", "and", "or");
    private static final List<String> SEPARATORS = Arrays.asList("{", "}", "[", "]", "(", ")", "'''",
             ".", ",", ":", ";");

    public LexicalAnalyzer(SymbolTable identifiersSymbolTable, SymbolTable constantsSymbolTable){
        this.identifiersSymbolTable = identifiersSymbolTable;
        this.constantsSymbolTable = constantsSymbolTable;
        this.lexical_error = false;
    }

    public void addToPIF(String token, Integer st_position){
        this.pif.add(new PairType<>(token, st_position));
    }

    public List<PairType<String, Integer>> getPIF(){
        return this.pif;
    }
    private boolean isIdentifier(String token) {
        String regex = "^([a-zA-Z]+[a-zA-Z0-9_]*)$";
        return token.matches(regex);
    }

    private boolean isConstant(String token) {
        return isNumberConstant(token) || isStringConstant(token);
    }

    private boolean isNumberConstant(String token) {
        String regex = "^([+-]?[1-9][0-9]*)|(0+)$";
        return token.matches(regex);
    }

    private boolean isStringConstant(String token) {
        return isStringConst(token) || isCharConst(token);
    }

    private boolean isStringConst(String token){
        String regex = "^(\'{3}.+\'{3})$";
        return token.matches(regex);
    }

    private boolean isCharConst(String token){
        String regex = "^('[a-zA-Z0-9]')$";
        return token.matches(regex);
    }

    public void scan(String filepath) {
        try {
            File myFile = new File(filepath);
            Scanner myReader = new Scanner(myFile);
            int lineNumber = 0;
            while (myReader.hasNextLine()) {
                lineNumber++;
                String line = myReader.nextLine();
                processLine(line, lineNumber);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + filepath + " not found!");
            e.printStackTrace();
        }
        if (!lexical_error) {
            System.out.println("No errors found!");
        }
    }

    private void processLine(String line, int lineNumber) {
        String[] tokens = line.trim().split("((?=\\(|\\))|(?<=\\(|\\)))");
        //System.out.println(Arrays.toString(tokens));
        for (String token : tokens) {
            if (!processToken(token)){
                String[] tokens1 = token.split(" ");
                for(String token1 : tokens1) {
                    token1 = token1.trim();
                    if(!processToken(token1)){
                        //System.out.println(token1);
                        String[] tokens2 = token1.split("((?=(<=)|(>=)|(==)|(!=)|=|<|>|,|;)|(?<=(<=)|(>=)|(==)|(!=)|=|<|>|,|;))");
                        for(String token2 : tokens2){
                            if(!processToken(token2)){
                                String[] tokens3 = token2.split("((?=\\+|-|\\*|//|%|!|\\[|]|\\.)|(?<=\\+|-|\\*|//|%|!|\\[|]|\\.))");
                                for(String token3 : tokens3) {
                                    if(!processToken(token3)){
                                        System.out.println("Lexical error on line " + lineNumber + ", token: " + token3);
                                        this.lexical_error = true;
                                        // code to delete what of a line with a lexical error was wrongly added in constants and identifiers symbol table
                                        /*for(String token33 : tokens2){
                                            //System.out.println(token33);
                                            int idPos = identifiersSymbolTable.getPosition(token33);
                                            //System.out.println(idPos);
                                            int cPos = constantsSymbolTable.getPosition(token33);
                                            if(idPos != -1) {
                                                identifiersSymbolTable.remove(idPos);
                                            }
                                            if(cPos != -1){
                                                constantsSymbolTable.remove(cPos);
                                            }
                                        }*/
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean processToken(String token) {
        if (token.length() == 0) {
            return true;
        }
        if (RESERVED_WORDS.contains(token) || OPERATORS.contains(token) || SEPARATORS.contains(token)) {
            addToPIF(token, -1);
            return true;
        } else if (isIdentifier(token)) {
            int index = identifiersSymbolTable.addElement(token);
            addToPIF("id", index);
            return true;
        } else if (isConstant(token)) {
            int index = constantsSymbolTable.addElement(token);
            addToPIF("const", index);
            return true;
        }
        return false;
    }
}










