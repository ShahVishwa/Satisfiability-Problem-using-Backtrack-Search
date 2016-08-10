/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;

import java.util.HashMap;

/**
 *
 * @author Vishwa
 */
public class Variable {
    private int noOfVariables;
    private int[] variables;
    private HashMap<Integer, Boolean> initialMap;
    
    public Variable(int size){
        this.noOfVariables = size;
        variables = new int[size];
        this.initialMap = new HashMap<Integer, Boolean>();
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

    public HashMap<Integer, Boolean> getInitialMap() {
        return initialMap;
    }

    public void setInitialMap(HashMap<Integer, Boolean> initialMap) {
        this.initialMap = initialMap;
    }
    
    public int[] generateVariables(){
        for(int i = 0; i < noOfVariables; i++){
            variables[i] = i+1;
        }
        return variables;
    }
    
    public HashMap<Integer, Boolean> mapping(){
        initialMap.put(0, Boolean.FALSE);
        initialMap.put(1, Boolean.TRUE);
        return initialMap;
    }

}
