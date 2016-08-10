/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Vishwa
 */
public class Literals {
    private Variable v;
    private int noOfLiterals;
    private HashMap<Integer, Boolean> map;
    private Clause c;
    
    
    public Literals(){
    }
    
    public Literals(Variable v){
        this.noOfLiterals = 2 * (v.getNoOfVariables());
    }

    public Variable getV() {
        return v;
    }

    public void setV(Variable v) {
        this.v = v;
    }

    public int getNoOfLiterals() {
        return noOfLiterals;
    }

    public void setNoOfLiterals(int noOfLiterals) {
        this.noOfLiterals = noOfLiterals;
    }

//    public HashMap<Integer, Boolean> mapping(Variable v){
//        map = new HashMap<Integer, Boolean>();
//        Random rand = new Random();
//        int[] variables = v.getVariables();
//        for(int i = 0; i < v.getNoOfVariables(); i++){
//            map.put(variables[i], rand.nextBoolean());
//        }
//        return map;
//    }
    
    public HashMap<Integer, Boolean> mapping(Variable v){
        map = new HashMap<Integer, Boolean>();
//        Random rand = new Random();
        int[] variables = v.getVariables();
        for(int i = 0; i <v.getNoOfVariables(); i++){
            map.put(variables[i], true);
        }
        return map;
    }
    
    
}
