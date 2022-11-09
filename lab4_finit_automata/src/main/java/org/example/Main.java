package org.example;


import org.example.Model.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static Map<String, State> stateMap = new HashMap<>(); // state name and the state itself
    static List<String> alphabet = new ArrayList<>();
    static String initialState;

    public static void main(String[] args) {
        readFiniteAutomata("");
        printMenu();
    }

    public static void  printMenu() throws IOException {
        String menu = "1. Display the set of states.\n2. Display the alphabet.\n3. Display all the transitions.\n4. Display the set of final states.\n5. Verify if the sequence is accepted by DFA.\n6. Display the initial state.";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(menu);
        while(true) {
            String userInput = br.readLine();
            StringBuilder output = new StringBuilder();
            switch(userInput) {
                case "1" -> {
                    output.append("Set of states is: ");
                    for (String state: stateMap.keySet()){
                        output.append(state).append(" ");
                    }
                }
                case "2" -> {
                    output.append("The alphabet is: ");
                    for(String element: alphabet) {
                        output.append(element).append(" ");
                    }
                }
                case "3" -> {
                    output.append("Transitions are:\n");
                    stateMap.forEach((stateName, state) -> state.getTransitions().forEach(transition -> output.append(stateName, transition.getAction())));

                }
                case "4" -> {
                    output.append("Final states are: ");
                    stateMap.forEach((key, value) -> {
                        if(value.isFinal()){
                            output.append(key).append(" ").
                        }
                    });
                }
                case "5" -> {

                }
                case "6" -> output.append("Initial state is: ").append(initialState);
                default -> output.append("Invalid choice.");
            }
        }
    }
}