package org.example;

public class FiniteAutomata {
    private final State currentState;

    public FiniteAutomata(State initState){
        this.currentState = initState;
    }

    // create a new finite automata with the current state being the "next state" of the last performed transition
    public FiniteAutomata switchState(CharSequence c) throws Exception {
        return new FiniteAutomata(this.currentState.performTransition(c));
    }

    public boolean canStop() {
        return this.currentState.isFinal();
    }
}
