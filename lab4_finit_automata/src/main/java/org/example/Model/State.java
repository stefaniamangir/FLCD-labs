package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class State {

    private List<Transition> transitions;
    private boolean isFinal;

    public State(boolean isFinal){
        this.transitions = new ArrayList<>();
        this.isFinal = isFinal;
    }

    public State addTransition(Transition transition) {
        this.transitions.add(transition);
        return this;
    }

    // of a possible transition exists we move to the next state
    public State performTransition(CharSequence s){

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
