package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class StatementAggregator {
    public static void main(String[] args) throws IOException {
        Double result = 0d;
        InputStream is = StatementAggregator.class.getClassLoader().getResourceAsStream("statement");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
        int counter = 1;
        List<Double> charges = new ArrayList<>();
        while (line != null) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if (token.charAt(0) == '$') {
                    Double toAdd = Double.parseDouble(token.substring(1));
                    charges.add(toAdd);
                    System.out.println(String.format("%d: $%.2f", counter++, toAdd));
                    result += toAdd;
                }
            }
            line = buf.readLine();
        }

        Collections.sort(charges);
        int counter2 = 1;
        System.out.println("Here they are sorted:");

        for (Double doubly : charges)
            System.out.println(String.format("%d: $%.2f", counter2++, doubly));


        System.out.println(String.format("The total amount for this statement is: $%.2f", result));
    }
}
