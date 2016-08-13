/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author Vishwa
 */
public class Literals {
    private Variables v;
    private int noOfLiterals;
    private LinkedHashMap<Integer, Integer> map;
    private Clause c;
    
    
    public Literals(){
        this.map = new LinkedHashMap<Integer, Integer>();
    }
    
    public Literals(Variables v){
        this.noOfLiterals = 2 * (v.getNoOfVariables());
        this.map = new LinkedHashMap<Integer, Integer>();
    }

    public Variables getV() {
        return v;
    }

    public void setV(Variables v) {
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
    
    public LinkedHashMap<Integer, Integer> mapping(Variables v){
//        Random rand = new Random();
        int[] variables = v.getVariables();
        for(int i = 0; i < v.getNoOfVariables(); i++){
            map.put(variables[i], -1);
        }
        return map;
    }
}
