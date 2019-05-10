package main.java.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StatementAggregator {
    public static void main(String[] args) throws IOException {
        Double result = 0d;
        InputStream is = StatementAggregator.class.getClassLoader().getResourceAsStream("statement");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
        int counter = 1;
        while(line != null){
            StringTokenizer tokenizer = new StringTokenizer(line);
            while(tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if(token.charAt(0) == '$') {
                    Double toAdd = Double.parseDouble(token.substring(1));
                    System.out.println(String.format("%d: $%.2f", counter++, toAdd));
                    result += toAdd;
                }
            }
            line = buf.readLine();
        }
        System.out.println(String.format("The total amount for this statement is: $%.2f", result));
    }
}
