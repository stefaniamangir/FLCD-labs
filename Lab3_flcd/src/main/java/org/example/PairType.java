package org.example;

public class PairType<String, Integer> {
    private String token;
    private Integer st_position;

    PairType(String token, Integer st_position) {
        this.token = token;
        this.st_position = st_position;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String newToken){
        this.token = newToken;
    }

    public Integer getSt_position(){
        return st_position;
    }

    public void setSt_position(Integer newSt_position){
        this.st_position = newSt_position;
    }
}
