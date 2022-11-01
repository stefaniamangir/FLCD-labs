package org.example;
import java.util.Formatter;

public class SymbolTable {
    private String[] hashTable;
    private final int size;

    SymbolTable(int size){
        this.size = size;
        this.hashTable = new String[size];
    }

    /* - input key: the string to hash
       - the function computes and returns the hash value for the given key as the sum of ascii chars modulo size
    */
    private int hash(String key){
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum = sum + key.charAt(i);
        }
        return sum % size;
    }

    // open addresing + linear probing
    /* - if the element that needs to be add does not already belong to the symbol table, the function will add it and then
    * it will return its position; otherwise, the function will only return the position of the already existing element
    * - collisions are handled using open addressing together with linear probing: */
    public int addElement(String key){
        int hash = hash(key);
        while(hashTable[hash] != null) {
            if(hashTable[hash].equals(key)){
                return hash;
            }
            hash = (hash + 1) % size;
        }
        hashTable[hash] = key;
        return hash;

    }

    public Integer getPosition(String key){
        int hash = hash(key);
        while(hashTable[hash] != null) {
            if(hashTable[hash].equals(key)){
                return hash;
            }
            hash = (hash + 1) % size;
        }
        return -1;
    }

    public void remove(Integer pos){
        String[] copy = new String[size];
        for(int i = 0; i < size; i++){
            if(hashTable[i] != null)
                copy[i] = hashTable[i];
            System.out.println(copy[i]);
        }
        hashTable = new String[size];
        for(int i = 0; i< size; i++){

            if(i!=pos && copy[i] != null){
                addElement(copy[i]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder hashTString = new StringBuilder();
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                hashTString.append("Position: " + i + " " + " -->  " + "Symbol: " + hashTable[i] + "\n");
            }
        }
        return hashTString.toString();
    }
}
