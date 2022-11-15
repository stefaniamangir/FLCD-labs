package org.example;

import java.util.ArrayList;
import java.util.List;

public class State {

    private List<Transition> transitions;
    private boolean isFinal;

    public State(boolean isFinal){
        this.transitions = new ArrayList<>();
        this.isFinal = isFinal;
    }

    public State() {
        this(false);
    }

    public State addTransition(Transition transition) {
        this.transitions.add(transition);
        return this;
    }

    // if a possible transition exists we move to the next state; return the transition with the next state which will be initial
    // otherwise an exception will be thrown with the invalid character, meaning that there is no trans available for the char from the current state
    public State performTransition(CharSequence s) throws Exception {
        return transitions.stream()
                .filter(t -> t.isPossible(s))
                .map(Transition::getNextState)
                .findAny()
                .orElseThrow(() -> new Exception(String.format("Input not accepted: %s", s)));
    }

    public List<Transition> getTransitions() {
        return this.transitions;
    }

    public boolean isFinal() {
        return this.isFinal;
    }

    public void setFinal(boolean value) {
        this.isFinal = value;
    }
}
