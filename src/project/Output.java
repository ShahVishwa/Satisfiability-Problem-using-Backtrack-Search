/*
 * To change this license header, choose License Headers in Output Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Vishwa
 */
public class Output {
    
    public static String readFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String currentLine;
            while((currentLine = br.readLine()) != null){
                sb.append(currentLine);
                sb.append("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        } 
        return sb.toString();
    }
    
    public static void assign(String inputFile){
        try (Scanner scan = new Scanner(inputFile).useDelimiter("\n")) {
            String noOfVariables = scan.nextLine();
            String noOfClauses = scan.nextLine();
            String clauses = scan.nextLine();
            
            Variables v = new Variables(Integer.valueOf(noOfVariables));
            int[] variable= v.generateVariables();
            HashMap<Integer, Integer> initialMap = v.mapping();
            Literals l = new Literals(v);
            LinkedHashMap<Integer, Integer> map = l.mapping(v);
            HashMap<Integer, String> statusMap = new HashMap<>();
            Predicate p = new Predicate(Integer.valueOf(noOfClauses));
            
            List<List<Integer>> listOfClause = new ArrayList<List<Integer>>();
            List<Integer> clause = new ArrayList<Integer>();
            int i = 1;
            String[] numbers = clauses.split("\\s+");
            for(String number : numbers){
                if(i == 4){
                    List<Integer> clauses1 = new ArrayList<Integer>(clause);
                    
                    listOfClause.add(clauses1);
                    clause.clear();
                    i = 1;
                } 
                clause.add(Integer.valueOf(number));
                i++;
            }
            List<Integer> clauses1 = new ArrayList<Integer>(clause);
            listOfClause.add(clauses1);
            clause.clear();
            
            p.setClauses(listOfClause);
            
            HashMap<Integer, Integer> set = p.getLiterals(listOfClause);
            p.initialStatus(statusMap, set);
            p.linkedList(set);
            p.setList(set, listOfClause);
            
            boolean result = p.solveFunction(map, initialMap, statusMap, v.getNoOfVariables());
            if(result){
                p.remainingKeys(map);
                System.out.println("True");
            } else {
                System.out.println("False. Predictae is unsatisfied");
            }
            System.out.println("No of Node Traversals: " + p.getNodeCount());
            p.print(map);
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        System.out.println("Input the method of Execution: 1 is Custom test case and 2 is Random generation of clauses");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        if(x == 1){
            String fileName = readFile("../Project/sample_test_case.txt");
            assign(fileName);
        } else if(x == 2) {
            System.out.println("Input the number of variables");
            int noOfVariables = sc.nextInt();
            Variables v = new Variables(noOfVariables);
            int[] variable= v.generateVariables();
            HashMap<Integer, Integer> initialMap = v.mapping();
            Literals l = new Literals(v);
            LinkedHashMap<Integer, Integer> map = l.mapping(v);
            HashMap<Integer, String> statusMap = new HashMap<>();
            System.out.println("Input the number of clauses");
            int noOfClauses = sc.nextInt();
            Predicate p = new Predicate(Integer.valueOf(noOfClauses));
            List<List<Integer>> listOfClause = p.generateClauses(v);
            HashMap<Integer, Integer> set = p.getLiterals(listOfClause);
            p.initialStatus(statusMap, set);
            p.linkedList(set);
            p.setList(set, listOfClause);
            
            boolean result = p.solveFunction(map, initialMap, statusMap, v.getNoOfVariables());
            if(result){
                p.remainingKeys(map);
                System.out.println("True");
            } else {
                System.out.println("False. Predicate is unsatisfied");
            }
            System.out.println("No of Node Traversals: " + p.getNodeCount());
            p.print(map);
        }
        
        
    }
    
}
