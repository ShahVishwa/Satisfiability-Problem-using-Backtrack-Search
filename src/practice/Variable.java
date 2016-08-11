/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Vishwa
 */
public class Variable {
    private int noOfVariables;
    private int[] variables;
    private HashMap<Integer, ArrayList<Integer>> initialMap;
    
    public Variable(int size){
        this.noOfVariables = size;
        variables = new int[size];
        this.initialMap = new HashMap<Integer, ArrayList<Integer>>();
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

    public HashMap<Integer, ArrayList<Integer>> getInitialMap() {
        return initialMap;
    }

    public void setInitialMap(HashMap<Integer, ArrayList<Integer>> initialMap) {
        this.initialMap = initialMap;
    }
    
    public int[] generateVariables(){
        for(int i = 0; i < noOfVariables; i++){
            variables[i] = i+1;
        }
        return variables;
    }
    
    public int pow(int base, int power){
        if(power == 0){
            return 1;
        }
        return base * pow(base, --power);
    }
    
    public ArrayList<Integer> getArrayList(int value){
        int value1 = pow(2, value);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i <= value1; i++){
            if(i%2 != 0){
                list.add(0);
            } else {
                list.add(1);
            }
        }
        return list;
    }
    
    public HashMap<Integer, ArrayList<Integer>> mapping(){
        for(int i = 0; i < noOfVariables; i++){
            initialMap.put(i+1, getArrayList(i+1));
        }
        return initialMap;
    }

}
