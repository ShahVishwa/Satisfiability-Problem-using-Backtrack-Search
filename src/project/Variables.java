/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Vishwa
 */
public class Variables {
    private int noOfVariables;
    private int[] variables;
    private HashMap<Integer, Integer> initialMap;
    
    public Variables(int size){
        this.noOfVariables = size;
        variables = new int[size];
        this.initialMap = new HashMap<>();
    }

    public int getNoOfVariables() {
        return noOfVariables;
    }

    public void setNoOfVariables(int noOfVariables) {
        this.noOfVariables = noOfVariables;
    }

    public int[] getVariables() {
        return variables;
    }

    public void setVariables(int[] variables) {
        this.variables = variables;
    }

    public HashMap<Integer, Integer> getInitialMap() {
        return initialMap;
    }

    public void setInitialMap(HashMap<Integer, Integer> initialMap) {
        this.initialMap = initialMap;
    }
    
    public int[] generateVariables(){
        for(int i = 0; i < noOfVariables; i++){
            variables[i] = i+1;
        }
        return variables;
    }

    public HashMap<Integer, Integer> mapping(){
        initialMap.put(0, 0);
        initialMap.put(1, 1);
        return initialMap;
    }
    
    
}
