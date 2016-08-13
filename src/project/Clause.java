/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Vishwa
 */
public class Clause {
    private final int literals = 3;
    public List<Integer> clause;
    
    public Clause(){
        this.clause = new ArrayList<>();
    }

    public List<Integer> getClause() {
        return clause;
    }

    public void setClause(List<Integer> clause) {
        this.clause = clause;
    }
    
    public List<Integer> generateClause(int noOfVariables){
        Random rand = new Random();
        for(int i = 0; i <literals; i++){
            int value = rand.nextInt(2 * noOfVariables) + 1;
            clause.add(value);
        }
//        List<List<Integer>> clauses = new ArrayList<List<Integer>>();
//        clause.add(1);
//        clause.add(2);
//        clause.add(9);
//        clauses.add(clause);
//        clause.add(1);
//        clause.add(3);
//        clause.add(4);
//        clauses.add(clause);
//        clause.add(11);
//        clause.add(8);
//        clause.add(6);
//        clauses.add(clause);
//        clause.add(11);
//        clause.add(3);
//        clause.add(12);
//        clauses.add(clause);
        return clause;
    }
}
