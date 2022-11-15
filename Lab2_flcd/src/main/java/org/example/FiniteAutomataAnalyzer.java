package org.example;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FiniteAutomataAnalyzer {

    private Map<String, State> stateMap; // state name and the state itself
    private List<String> alphabetL;
    private String initialState;

    public void run(String file) throws IOException {
        readFiniteAutomata(file);
    }

    public FiniteAutomataAnalyzer(String file){
        stateMap =  new HashMap<>();
        alphabetL = new ArrayList<>();
        readFiniteAutomata(file);
    }
    public void readFiniteAutomata(String filename) {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            //read the file line by line and process each line
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine().trim();
                processLine(line);
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void processLine(String line) throws Exception {
        String[] tokens = line.split("=");
        if (tokens.length != 2) {
            throw new Exception("Invalid input!");
        }
        switch (tokens[0].trim()) {
            // add the states enumerated on the first line Q = q1, ...
            case "Q" -> {
                String[] stateTokens = getTokens(tokens[1].trim(), ",");
                for (String state : stateTokens) {
                   stateMap.put(state.trim(), new State());
                }
            }
            // add the elements of the alphabet E = 0, ..
            case "E" -> {
                String[] alphabet = getTokens(tokens[1].trim(), ",");
                for (String element : alphabet) {
                    alphabetL.add(element.trim());
                }
            }
            // set the initial state q0 = q0
            case "q0" -> {
                String[] initialStateTokens = getTokens(tokens[1].trim(), ",");
                if (initialStateTokens.length != 1) {
                    throw new Exception("Invalid initial state!");
                }
                initialState = initialStateTokens[0];
            }
            // set which states are final F = q1
            case "F" -> {
                String[] finalStates = getTokens(tokens[1].trim(), ",");
                for (String state : finalStates) {
                    stateMap.get(state).setFinal(true);
                }
            }
            // add transitions to each corresponding state S = q0,1 -> q1;...
            case "S" -> {
                String[] transitions = getTokens(tokens[1].trim(), ";");
                for (String transitionToken : transitions) {
                    String[] transitionElements = transitionToken.trim().split("->");
                    String transition = transitionElements[0].trim();
                    String resultState = transitionElements[1].trim();
                    String firstState = transition.split(",")[0].trim();
                    String action = transition.split(",")[1].trim();
                    // add to the current state the transition with the action and the result state
                    stateMap.get(firstState).addTransition(new Transition(action, stateMap.get(resultState)));
                }
            }
            default -> throw new Exception("Invalid input file!");
        }
    }

    private static String[] getTokens(String tokenString, String separator) {
        String[] result = tokenString.trim().split(separator);
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }
        return result;
    }

    public boolean isAccepted(String sequence) {
        boolean accepted = true;
        FiniteAutomata finiteAutomata = new FiniteAutomata(stateMap.get(initialState));

        for (int i = 0; i < sequence.length(); i++) {
            try {
                finiteAutomata = finiteAutomata.switchState(String.valueOf(sequence.charAt(i)));
            } catch (Exception e) {
               accepted = false;
            }
        }
        return accepted;
    }
}
