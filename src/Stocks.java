public class Stocks {

    static double[] statesOfReturn = {.65, .35};
    static double[] stockA = {.11, .12};
    static double[] stockB = {.19, .06};
    static double[] stockC = {.37, -.05};
    static double[][] stocks = {stockA, stockB, stockC};

    public static void main(String[] args) throws Exception {
        System.out.println("Expected return = " + calculateExpectedReturnOfEquallyWeightedPortfolio(statesOfReturn, stocks));
    }

    static double calculateExpectedReturnOfEquallyWeightedPortfolio(double[] probOfState, double[][] stocks) throws Exception {
        double ret = 0;
        double[] percentages = getPortfolioReturns(getEqualWeights(stocks), stocks);
        for(int i = 0; i < percentages.length; i++) ret += probOfState[i] * percentages[i];
        return ret;
    }

    static double[] getEqualWeights(double[][] stocks) {
        double[] weights = new double[stocks.length];
        for(int i = 0; i < weights.length; i++) {
            weights[i] = (double)100/weights.length;
        }
        return weights;
    }

    static double[] getPortfolioReturns(double[] investmentWeights, double[][] returnRates) throws Exception {
        double[] portfolioReturns = new double[returnRates[0].length];
        for (int i = 0; i < returnRates[0].length; i++) {
            double[] row = new double[returnRates.length];
            for(int j = 0; j < returnRates.length; j++) row[j] = returnRates[j][i];
            portfolioReturns[i] = calculateReturnIfStateOccurs(investmentWeights, row);
        }
        return portfolioReturns;
    }

    static double calculateReturnIfStateOccurs(double[] investmentWeights, double[] returnRate) throws Exception {
        if (investmentWeights.length != returnRate.length) throw new Exception("Lengths must be the same");
        double ret = 0;
        for(int i = 0; i < investmentWeights.length; i++) ret += investmentWeights[i]*returnRate[i];
        return ret;
    }

    static double calculateExpectedReturn(double[] probOfState, double[] ratesOfReturn) throws Exception {
        if (probOfState.length != ratesOfReturn.length) throw new Exception("Lengths must be the same");
        double expectedReturn = 0;
        for (int i = 0; i < probOfState.length; i++) expectedReturn += probOfState[i] * ratesOfReturn[i];
        return expectedReturn;
    }

    static double calculateStandardDeviation(double[] probOfState, double[] ratesOfReturn) throws Exception {
        double expectedReturn = calculateExpectedReturn(probOfState, ratesOfReturn);
        double sumOfWeightedSquaredVariances = 0;
        for (int i = 0; i < probOfState.length; i++)
            sumOfWeightedSquaredVariances += Math.pow(ratesOfReturn[i] - expectedReturn, 2) * probOfState[i];
        return Math.sqrt(sumOfWeightedSquaredVariances);
    }

}
