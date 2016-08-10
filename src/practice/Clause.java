/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;

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
    
//    public List<Integer> generateClause(){
//        Random rand = new Random();
//        clause = new ArrayList<Integer>();
//        for(int i = 0; i <literals; i++){
//            int value = rand.nextInt(20) + 1;
//            clause.add(value);
//        }
//        return clause;
//    }
    
    public List<Integer> generateClause(int noOfVariables){
        Random rand = new Random();
        for(int i = 0; i <literals; i++){
            int value = rand.nextInt(2 * noOfVariables) + 1;
            clause.add(value);
        }
        return clause;
    }
    
}
