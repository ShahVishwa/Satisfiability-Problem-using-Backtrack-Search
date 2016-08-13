/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vishwa
 */
public class Predicate {
    private Clause c;
    private int noOfClauses;
    private List<List<Integer>> clauses;
    HashMap<Integer, Integer> finalMap = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> set = new HashMap<Integer, Integer>();
    
    public Predicate(int noOfclauses){
        this.noOfClauses = noOfclauses;
        this.clauses = new ArrayList<List<Integer>>();
    }
    
    public int getNoOfClauses() {
        return noOfClauses;
    }

    public void setNoOfClauses(int noOfClauses) {
        this.noOfClauses = noOfClauses;
    }

    public Clause getC() {
        return c;
    }

    public void setC(Clause c) {
        this.c = c;
    }

    public List<List<Integer>> getClauses() {
        return clauses;
    }

    public void setClauses(List<List<Integer>> clauses) {
        this.clauses = clauses;
    }
    
//    public List<List<Integer>> generateClauses(Variables v){
//        for(int i = 0; i < noOfClauses; i++){
//            c = new Clause();
//            List<Integer> n = c.generateClause(v.getNoOfVariables());
//            clauses.add(n);
//            System.out.println(n);
//        }
//        return clauses;
//    }
    
//    public void initialStatus(int noOfVariables, HashMap<Integer, String> statusMap){
//        for(int i = 0; i < 2 * noOfVariables; i++){
//            statusMap.put(i+1, "UNKNOWN");
//        }
//    }
//    
//    public void preprocess(List<List<Integer>> clauses, int noOfVariables, HashMap<Integer, String> statusMap, HashMap<Integer, Integer> map){
//        initialStatus(noOfVariables, statusMap);
//        List<Integer> clause = new ArrayList<Integer>();
//        for(int i = 0; i < clauses.size(); i++){
//            clause = clauses.get(i);
//            for(int j = 0; j < 3; j++){
//                if(finalMap.containsKey(clause.get(j))){
//                    finalMap.put(clause.get(j), finalMap.get(clause.get(j)) + 1);
//                } else {
//                    finalMap.put(clause.get(j), 1);
//                }
//            }
//        }
//        
//        Iterator it = finalMap.entrySet().iterator();
//        while(it.hasNext()){
//            Map.Entry<Integer, Integer> pair = (Map.Entry)it.next();
//            int value = pair.getKey();
//            if(value > noOfVariables && pair.getValue() == 1 && !finalMap.containsKey(pair.getKey() - noOfVariables)){
//                statusMap.put(value, "STAISFIABLE");
//                value = value - noOfVariables;
//                map.put(value, 0);
//                statusMap.put(value, "STAISFIABLE");
//            } else if(value <= noOfVariables && pair.getValue() == 1 && !finalMap.containsKey(pair.getKey() + noOfVariables)){
//                map.put(value, 1);
//            } 
//        }
//    }
    
    public void getLiterals(List<List<Integer>> clauses){
        List<Integer> clause = new ArrayList<Integer>();
        int k = 0;
        for(int i = 0; i < clauses.size(); i++){
            clause = clauses.get(i);
            for(int j = 0; j < clause.size(); j++){
                if(set.containsValue(clause.get(j))){
                    continue;
                } else {
                    set.put(k, clause.get(j));
                    k++;
                }
            }
        }
    }
    
    public boolean getReverse(int value, HashMap<Integer, Integer> map){
        if(value == -1){
            return false;
        }
        int i = map.get(value);
        if(i == 0){
            return true;
        } else {
            return false;
        }
    }
    
    public int getReverseInt(int value){
        if(value == 0){
            return 1;
        } else {
            return 0;
        }
    }
    
    public boolean getValue(int value, int noOfVariables, HashMap<Integer, Integer> map){
        if(value > noOfVariables && value <= 2 * noOfVariables){
            value = value - noOfVariables;
            return getReverse(value, map);
        } 
        else if(value >0 && value <= noOfVariables){
            int j = map.get(value);
            if(j == -1 || j == 1){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
    
    public boolean solveFunction(LinkedHashMap<Integer, Integer> map, HashMap<Integer, Integer> initialMap, HashMap<Integer, String> statusMap, int noOfVariables){
        getLiterals(clauses);
        return solveFunctionUtil(map, initialMap, statusMap, set, noOfClauses, 0, clauses, noOfVariables);
    }
    
    public boolean solveFunctionUtil(LinkedHashMap<Integer, Integer> map, HashMap<Integer, Integer> initialMap, HashMap<Integer, String> statusMap, HashMap<Integer, Integer> set, int noOfClauses, int present, List<List<Integer>> clauses, int noOfVariables){
        if(present == set.size()){
            return true;
        }
//        preprocess(clauses, noOfVariables, statusMap, map);
            int value = set.get(present);
//            if(value > noOfVariables && value <= 2 * noOfVariables){
//                int value1 = value - noOfVariables; 
//                if(map.get(value1) != -1 && statusMap.get(value1).equals("SATISFIABLE")){
//                    present++;
//                    if(solveFunctionUtil(map, initialMap, statusMap, set, noOfClauses, present, clauses, noOfVariables)){
//                        return true;
//                    }
//                }
//            } else if (value < noOfVariables){
//                if(map.get(value) != -1 && statusMap.get(value).equals("SATISFIABLE")){
//                    present++;
//                    if(solveFunctionUtil(map, initialMap, statusMap, set, noOfClauses, present, clauses, noOfVariables)){
//                        return true;
//                    }
//                }
//            }
            for(int j = 0; j < initialMap.size(); j++){
                boolean variablefoundSafe = true;
                if(value > noOfVariables && value <= 2 * noOfVariables){
                    map.put(value - noOfVariables, getReverseInt(initialMap.get(j)));
                } else if(value > 2 * noOfVariables){
                    map.put(value, 1);
                    present++;
                    if(solveFunctionUtil(map, initialMap, statusMap, set, noOfClauses, present, clauses, noOfVariables)){
                        return true;
                    }
                }
                else {
                    map.put(value, initialMap.get(j));
                }
                List<Integer> clause = new ArrayList<Integer>();
                for(int k = 0; k< noOfClauses; k++){
                    clause = clauses.get(k);
                    if(!(getValue(clause.get(0), noOfVariables, map) || getValue(clause.get(1), noOfVariables, map) || getValue(clause.get(2), noOfVariables, map))){  
//                        if(present == 0 && j == 1){
//                            variablefoundSafe = true;
//                            break;
//                        }
                        variablefoundSafe = false;
                        break;
                    } 
                }
                if(variablefoundSafe){
                    present++;
                    if(solveFunctionUtil(map, initialMap, statusMap, set, noOfClauses, present, clauses, noOfVariables)){
                        return true;
                    }
                }
            } 
        
        return false;
    }
    
    public void remainingKeys(LinkedHashMap<Integer, Integer> map){
        if(map.containsValue(-1)){
            for(int i = 0; i < map.size(); i++){
                if(map.containsKey(i+1)){
                    int value = map.get(i+1);
                    if(value == -1){
                        map.put(i+1, 1);
                    }
                }
            }
        }
    }
    
    public void print(HashMap<Integer, Integer> map){
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry x = (Map.Entry)it.next();
            System.out.println(x.getKey() + " " + x.getValue());
        }
    }
}
