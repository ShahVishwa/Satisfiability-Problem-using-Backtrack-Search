/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    Variable v = new Variable(40);
    int[] variable= v.generateVariables();
    HashMap<Integer, Boolean> initialMap = v.mapping();
    Literals l = new Literals(v);
    HashMap<Integer, Boolean> map = l.mapping(v);
    private int nodes;
    
    public Predicate(){
        this.clauses = new ArrayList<List<Integer>>();
        this.nodes = 0;
    }
    
    public Predicate(int clauses){
        this.noOfClauses = clauses;
        this.clauses = new ArrayList<List<Integer>>();
        this.nodes = 0;
    }

    public int getNoOfClauses() {
        return noOfClauses;
    }

    public void setNoOfClauses(int noOfClauses) {
        this.noOfClauses = noOfClauses;
    }
    
    public List<List<Integer>> generateClauses(){
        for(int i = 0; i < noOfClauses; i++){
            c = new Clause();
            List<Integer> n = c.generateClause(v.getNoOfVariables());
            clauses.add(n);
            System.out.println(n);
        }
        return clauses;
    }
    
    public boolean solveFunction(){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter the number of Variables: ");
//        int x1 = sc.nextInt();
//        System.out.println("Enter the number of Clauses: ");
//        int x2 = sc.nextInt();
        Predicate p = new Predicate(40);
        clauses = p.generateClauses();
        return solveFunctionUtil(map, initialMap, p.getNoOfClauses(), 1, clauses, v.getNoOfVariables());
    }
    
    public boolean getValue(int value, int noOfVariables, HashMap<Integer, Boolean> map){
        if(value > 2 * noOfVariables){
            return true;
        }
        else if(value > noOfVariables && value <= 2 * noOfVariables){
                value = value - noOfVariables;
                return !map.get(value);
        } 
        return map.get(value);
    }
    
    public boolean solveFunctionUtil(HashMap<Integer, Boolean> map, HashMap<Integer, Boolean> initialMap, int noOfClauses, int present, List<List<Integer>> clauses, int noOfVariables){
        if(present == noOfVariables+1){
            return true;
        }
        int i;
        for(i = 0; i < initialMap.size(); i++){
            map.put(present, initialMap.get(i));
            boolean foundSafe = true;
            List<Integer> clause = new ArrayList<Integer>();
            for(int j = 0; j < noOfClauses; j++){
                clause = clauses.get(j);
                if(!(getValue(clause.get(0), noOfVariables, map) || getValue(clause.get(1), noOfVariables, map) || getValue(clause.get(2), noOfVariables, map))){                 
                    nodes++;
                    if(i == 1){
                        foundSafe = true;
                        break;
                    }
                    foundSafe = false;
                    break;
                } 
            }
            if(foundSafe){
                present += 1;
                if(solveFunctionUtil(map, initialMap, noOfClauses, present, clauses, noOfVariables)){
                    return true;
                }
            }
        }  
        return false;
    }
    
    public void print(HashMap<Integer, Boolean> map){
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry x = (Map.Entry)it.next();
            System.out.println(x.getKey() + " " + x.getValue());
        }
    }
    
//    public boolean solveFunction(){
////        Scanner sc = new Scanner(System.in);
////        System.out.println("Enter the number of Variables: ");
////        int x1 = sc.nextInt();
//        Variable v = new Variable(10);
//        v.generateVariables();
//        Literals l = new Literals(v);
//        HashMap<Integer, Boolean> map = l.mapping(v);
////        System.out.println("Enter the number of Clauses: ");
////        int x2 = sc.nextInt();
//        Predicate p = new Predicate(10000);
//        clauses = p.generateClauses();
//        return solveFunctionUtil(map, p.getNoOfClauses(), 0, clauses, v.getNoOfVariables());
//    }
//    
//    public int getValue(int value, int noOfVariables){
//        if(value > noOfVariables && value <= 2 * noOfVariables){
//                value = value - noOfVariables;
//                return value;
//        } 
//        return value;
//    }
//    
//    public boolean solveFunctionUtil(HashMap<Integer, Boolean> map, int noOfClauses, int present, List<List<Integer>> clauses, int noOfVariables){
//        if(present == noOfClauses){
//            return true;
//        }
//        int i;
//        List<Integer> clause = new ArrayList<>();
//        clause = clauses.get(present);
//        for(i = 0; i < 3; i++){
//            System.out.println(clause.get(i));
//            if(map.get(getValue(clause.get(i), noOfVariables))){
//                present += 1;
//                solveFunctionUtil(map, noOfClauses, present, clauses, noOfVariables);
//                return true;
//            }
//        }
//        if(i == 3){
//            for(int j = 0; j < 3; j++){
//                boolean reverse = map.get(getValue(clause.get(j), noOfVariables));
//                map.put(getValue(clause.get(j), noOfVariables), !reverse);
//                if(solveFunctionUtil(map, noOfClauses, 0, clauses, noOfVariables)){
//                    return true;
//                }
//            }
//        }
//        return false;
//        }
     
    public static void main(String[] args) {
        Predicate p = new Predicate();
        System.out.println(p.solveFunction());
        p.print(p.map);
        System.out.println(p.nodes);
    }
}
