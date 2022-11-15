package org.example.Model;

public class Transition {
    private final String action; // how to get to the next state
    private final State nextState;

    public Transition(String action, State nextState) {
        this.action = action;
        this.nextState = nextState;
    }

    // check if the sequence of chars is equal to the action field of the instance
    public boolean isPossible(CharSequence s) {
        return this.action.equalsIgnoreCase(String.valueOf(s));
    }

    public State getNextState() {
        return this.nextState;
    }

    public String getAction() {
        return this.action;
    }


}
