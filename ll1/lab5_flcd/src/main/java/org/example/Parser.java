package org.example;

import java.util.*;

public class Parser {
    private final Grammar grammar;
    public HashMap<String, Set<String>> firstForNonterminals; // set, elements are added randomly, and only if they not already exist /// otherwise infinite cycle
    private HashMap<String, List<String>> followForNonterminals;


    public Parser(Grammar grammar) {
        this.grammar = grammar;
        this.firstForNonterminals = new HashMap<>();
        this.followForNonterminals = new HashMap<>();

        generateFirstSet();
    }

    public void generateFirstSet() {
        //initialization with terminals if existent /// otherwise empty set
        for (String nonTerminal : grammar.getNonTerminals()) {
            firstForNonterminals.put(nonTerminal, new HashSet<>());
            List<List<String>> productionsForNonTerminal = grammar.getProductionsForNonterminal(nonTerminal);
            for (List<String> production : productionsForNonTerminal) {
                // verify if on the first position is terminal or epsilon and if yes add it
                if (grammar.getTerminals().contains(production.get(0)) || production.get(0).equals("epsilon")) {
                    firstForNonterminals.get(nonTerminal).add(production.get(0));
                }
            }
        }
        System.out.println(firstForNonterminals);
        // rest of iterations
        var isDifferent = true;
        while (isDifferent) {
            isDifferent = false;
            HashMap<String, Set<String>> newIteration = new HashMap<>();

            for (String nonTerminal : grammar.getNonTerminals()) {
                List<List<String>> productionsForNonterminal = grammar.getProductionsForNonterminal(nonTerminal);
                Set<String> concatenatedSet = new HashSet<>(firstForNonterminals.get(nonTerminal));
                for (List<String> production : productionsForNonterminal) {
                    List<String> rightNonTerminals = new ArrayList<>();
                    String rightFoundTerminal = null;
                    for (String item : production)
                        if (this.grammar.getNonTerminals().contains(item)) // if it is non terminal add it
                            rightNonTerminals.add(item);
                        else {
                            rightFoundTerminal = item;
                            break;
                        }
                    concatenatedSet.addAll(concatenationOfLengthOne(rightNonTerminals, rightFoundTerminal));
                }
                // it at least one first is different means that the iteration is different
                if (!concatenatedSet.equals(firstForNonterminals.get(nonTerminal))) {
                    isDifferent = true;
                }
                newIteration.put(nonTerminal, concatenatedSet);
            }
            firstForNonterminals = newIteration;
            System.out.println(newIteration);
        }
    }

    private Set<String> concatenationOfLengthOne(List<String> nonTerminals, String terminal) {
        // no terminals -> return the empty set
        if (nonTerminals.size() == 0)
            return new HashSet<>();
        // just add the first of the non terminal
        if (nonTerminals.size() == 1) {
            return firstForNonterminals.get(nonTerminals.get(0));
        }

        // logic: if in the first nonterminal's FIRST epsilon does not exist, then all the terminals from there will
        // be present in the final FIRST and only them: we do not need to verify the second nonterminal's FIRST

        // if there is an epsilon in the first nonterminal's FIRST that means that it will be combined with
        // all of the terminals form the second nonterminal's FIRST and therefore all the terminals from the
        // second nonterminal's FIRST will pe present in the final first and need to be also added
        Set<String> concatenation = new HashSet<>();
        var i = 0;

        while (i < nonTerminals.size()) {
            boolean isEpsilon = false;
            for (String s : firstForNonterminals.get(nonTerminals.get(i)))
                if (s.equals("epsilon"))
                    isEpsilon = true;
                else
                    concatenation.add(s);
            if (isEpsilon)
                i++;
            else
                break;
        }

        var allNonTerminalsHaveEpsilon = true;
        for (String nonTerminal : nonTerminals) {
            //System.out.println(firstForNonterminals.get(nonTerminal));
            if (!firstForNonterminals.get(nonTerminal).contains("epsilon")) {
                allNonTerminalsHaveEpsilon = false;
            }
        }
        // if there is at least one non terminal production not containing epsilon we do nothing
        // if all non terminal's productions contain an epsilon we do the following:

        // if the production contains a terminal after the epsilon-containing non terminals then (if it was before then we would not have added the non terminals here)
        // --> that terminal must be added, and not the epsilon, as finally, epsilon + terminal will result in
        // --> the terminal and not the epsilon

        // otherwise, if there are only epsilon-containing non terminals then epsilon will be surely present
        // in the final first as there will be at least one epsilon + epsilon which will result in epsilon
        if (allNonTerminalsHaveEpsilon) {
            concatenation.add(Objects.requireNonNullElse(terminal, "epsilon"));
        }

        return concatenation;
    }


}
