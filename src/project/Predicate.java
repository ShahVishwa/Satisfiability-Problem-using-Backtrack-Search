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
import java.util.LinkedList;
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
//    HashMap<Integer, Integer> finalMap = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> set;
    private Map<Integer, List<Integer>> list;
    private int nodeCount;
    
    public Predicate(int noOfclauses){
        this.noOfClauses = noOfclauses;
        this.clauses = new ArrayList<List<Integer>>();
        this.list = new HashMap<>();
        this.set = new HashMap<>();
    }
    
    public void linkedList(HashMap<Integer, Integer> set){
        for(int i = 0; i< set.size(); i++){
            list.put(set.get(i), new LinkedList<Integer>());
        }
    }
    
    public void setEdge(int source, int destination){
        List<Integer> sList = list.get(source);
        if(!sList.contains(destination)){
            sList.add(destination);
        }
        List<Integer> dList = list.get(destination);
        if(!dList.contains(source)){
            dList.add(source);
        }
    }
    
    public List<Integer> getEdge(int source){
        if(source > list.size()){
            System.out.println("The vertex entered is not present");
            return null;
        }
        return list.get(source);
    }
    
    public void setList(HashMap<Integer, Integer> set, List<List<Integer>> clauses){
        for(int i = 0; i < set.size(); i++){
            int value = set.get(i);
            for(int j = 0; j < clauses.size(); j++){
                List<Integer> clause = clauses.get(j);
                if(clause.contains(value)){
                    for(int k = 0; k < clause.size(); k++){
                        if(clause.get(k) != value){
                            setEdge(value, clause.get(k));
                        }
                    }
                }
            }
        }
    }
    
    public List<Integer> getList(int value){
        List<Integer> result = new ArrayList<>();
        result = list.get(value);
        return result;
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

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }
    
    public List<List<Integer>> generateClauses(Variables v){
        for(int i = 0; i < noOfClauses; i++){
            c = new Clause();
            List<Integer> n = c.generateClause(v.getNoOfVariables());
            clauses.add(n);
            System.out.println(n);
        }
        return clauses;
    }
    
    public void initialStatus(HashMap<Integer, String> statusMap, HashMap<Integer, Integer> set){
        for(int i = 0; i < set.size(); i++){
            statusMap.put(set.get(i), "UNKNOWN");
        }
    }
    
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
    
    public HashMap<Integer, Integer> getLiterals(List<List<Integer>> clauses){
        List<Integer> clause = new ArrayList<>();
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
        return set;
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
    
    //Method - 1
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
                    nodeCount++;
                } else if(value > 2 * noOfVariables){
                    map.put(value, 1);
                    present++;
                    if(solveFunctionUtil(map, initialMap, statusMap, set, noOfClauses, present, clauses, noOfVariables)){
                        return true;
                    }
                }
                else {
                    map.put(value, initialMap.get(j));
                    nodeCount++;
                }
                List<Integer> clause = new ArrayList<Integer>();
                for(int k = 0; k< noOfClauses; k++){
                    clause = clauses.get(k);
                    if(!(getValue(clause.get(0), noOfVariables, map) || getValue(clause.get(1), noOfVariables, map) || getValue(clause.get(2), noOfVariables, map))){  
                        nodeCount++;
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
    
    
    //Method - 2
//    public boolean solveFunctionUtil(LinkedHashMap<Integer, Integer> map, HashMap<Integer, Integer> initialMap, HashMap<Integer, String> statusMap, HashMap<Integer, Integer> set, int noOfClauses, int present, List<List<Integer>> clauses, int noOfVariables){
//        if(present == set.size()){
//            return true;
//        }
//        int value = set.get(present);
//        List<Integer> list = getList(value);
//        for(int j = 0; j < initialMap.size(); j++){
//            boolean sourcefoundSafe = true;
//            if(value > noOfVariables && value <= 2 * noOfVariables){
//                map.put(value - noOfVariables, getReverseInt(initialMap.get(j)));
//                nodeCount++;
//            } else if(value > 2 * noOfVariables){
//                map.put(value, 1);
//                present++;
//                if(solveFunctionUtil(map, initialMap, statusMap, set, noOfClauses, present, clauses, noOfVariables)){
//                    return true;
//                }
//            }
//            else {
//                map.put(value, initialMap.get(j));
//                nodeCount++;
//            }
//            if(solveFunctionUtil1(map, initialMap, noOfVariables, statusMap, value, list, 0)){
//                sourcefoundSafe = true;
//                for(int a = 0; a < list.size(); a++){
//                    int x = list.get(a);
//                    if(!statusMap.get(x).equals("SATISFIABLE")){
//                        statusMap.put(x, "SATISFIABLE");
//                    }
//                }
//            } else {
//                sourcefoundSafe = false;
//                nodeCount++;
//            }
//            if(sourcefoundSafe){
//                present++;
//                if(solveFunctionUtil(map, initialMap, statusMap, set, noOfClauses, present, clauses, noOfVariables)){
//                    return true;
//                }
//            }
//        } 
//        return false;
//    }
//    
//    public boolean solveFunctionUtil1(LinkedHashMap<Integer, Integer> map, HashMap<Integer, Integer> initialMap, int noOfVariables, HashMap<Integer, String> statusMap, int value, List<Integer> list, int present){
//        if(present == list.size()){
//            return true;
//        }
//        int value1 = list.get(present);
//        if(statusMap.get(value1).equals("UNKNOWN") || statusMap.get(value1).equals("UNSTAISFIABLE")){
//            for(int b = 0; b < initialMap.size(); b++){
//                boolean variablefoundSafe = true;
//                if(value1 > noOfVariables && value1 <= 2 * noOfVariables){
//                    map.put(value1 - noOfVariables, getReverseInt(initialMap.get(b)));
//                    nodeCount++;
//                } else {
//                    map.put(value1, initialMap.get(b));
//                    nodeCount++;
//                }
//                List<Integer> clause = new ArrayList<Integer>();
//                for(int k = 0; k< noOfClauses; k++){
//                    clause = clauses.get(k);
//                    if(!(getValue(clause.get(0), noOfVariables, map) || getValue(clause.get(1), noOfVariables, map) || getValue(clause.get(2), noOfVariables, map))){  
//                        nodeCount++;
//                        statusMap.put(value1, "UNSTAISFIABLE");
//                        variablefoundSafe = false;
//                        break;
//                    } 
//                }
//                if(variablefoundSafe){
//                    present++;
//                    if(solveFunctionUtil1(map, initialMap, noOfVariables, statusMap, value, list, present)){
//                        return true;
//                    }
//                }
//            }
//        } else {
//            present++;
//            if(solveFunctionUtil1(map, initialMap, noOfVariables, statusMap, value, list, present)){
//                return true;
//            }
//        }   
//        return false;
//    }
    
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
