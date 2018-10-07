public class Stocks {

    static double[] statesOfReturn = {.2, .5, .3};
    static double[] stockA = {.08, .11, .16};
    static double[] stockB = {-.15, .14, .31};

    public static void main(String[] args) throws Exception {
        System.out.println("Expected return = " + calculateStandardDeviation(statesOfReturn, stockB));
    }

    static double calculateExpectedReturn(double[] probOfState, double[] ratesOfReturn) throws Exception {
        if(probOfState.length != ratesOfReturn.length) throw new Exception("Lengths must be the same");
        double expectedReturn = 0;
        for(int i = 0; i < probOfState.length; i++) expectedReturn += probOfState[i] * ratesOfReturn[i];
        return expectedReturn;
    }

    static double calculateStandardDeviation(double[] probOfState, double[] ratesOfReturn) throws Exception {
        double expectedReturn = calculateExpectedReturn(probOfState, ratesOfReturn);
        double sumOfWeightedSquaredVariances = 0;
        for(int i = 0; i < probOfState.length; i++) sumOfWeightedSquaredVariances += Math.pow(ratesOfReturn[i] - expectedReturn, 2) * probOfState[i];
        return Math.sqrt(sumOfWeightedSquaredVariances);
    }

}
