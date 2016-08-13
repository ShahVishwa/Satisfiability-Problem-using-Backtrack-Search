/*
 * To change this license header, choose License Headers in Final Properties.
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
public class Final {
    
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
//            HashMap<Integer, Integer> finalMap = new HashMap<Integer, Integer>();
            HashMap<Integer, String> statusMap = v.initialStatus(v.getNoOfVariables());
//            HashMap<Integer, Integer> set = new HashMap<Integer, Integer>();
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
            
            System.out.println(p.solveFunction(map, initialMap, statusMap, v.getNoOfVariables()));
            p.remainingKeys(map);
            p.print(map);
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        String fileName = readFile("C:/Algo/Project/sample_test_case.txt");
        assign(fileName);
    }
    
}
