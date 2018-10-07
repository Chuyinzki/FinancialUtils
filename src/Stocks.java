public class Stocks {

    static double[] statesOfReturn = {.1, .5, .35, .05};
    static double[] stockA = {.3, .15, -.02, -.10};
    static double[] stockB = {.4, .11, -.05, -.15};
    static double[] stockC = {.2, .09, -.03, -.07};
    static double[][] stocks = {stockA, stockB, stockC};
    static double[] weights = {.32, .36, .32};

    public static void main(String[] args) throws Exception {
        summarize(statesOfReturn, weights, stocks);
    }

    static void summarize(double[] statesOfReturn, double[] weights, double[][] stocks) throws Exception {
        calculateStandardDeviation(statesOfReturn, getPortfolioReturns(weights, stocks));
    }

    static double calculateExpectedReturnOfWeightedPortfolio(double[] probOfState, double[][] stocks, double[] weigths) throws Exception {
        double ret = 0;
        double[] percentages = getPortfolioReturns(weigths, stocks);
        for (int i = 0; i < percentages.length; i++) ret += probOfState[i] * percentages[i];
        return ret;
    }

    static double calculateExpectedReturnOfEquallyWeightedPortfolio(double[] probOfState, double[][] stocks) throws Exception {
        return calculateExpectedReturnOfWeightedPortfolio(probOfState, stocks, getEqualWeights(stocks));
    }

    static double[] getEqualWeights(double[][] stocks) {
        double[] weights = new double[stocks.length];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (double) 1 / weights.length;
        }
        return weights;
    }

    static double[] getPortfolioReturns(double[] investmentWeights, double[][] returnRates) throws Exception {
        double[] portfolioReturns = new double[returnRates[0].length];
        for (int i = 0; i < returnRates[0].length; i++) {
            double[] row = new double[returnRates.length];
            for (int j = 0; j < returnRates.length; j++) row[j] = returnRates[j][i];
            portfolioReturns[i] = calculateReturnIfStateOccurs(investmentWeights, row);
        }
        return portfolioReturns;
    }

    static double calculateReturnIfStateOccurs(double[] investmentWeights, double[] returnRate) throws Exception {
        if (investmentWeights.length != returnRate.length) throw new Exception("Lengths must be the same");
        double ret = 0;
        for (int i = 0; i < investmentWeights.length; i++) ret += investmentWeights[i] * returnRate[i];
        return ret;
    }

    static double calculateExpectedReturn(double[] probOfState, double[] ratesOfReturn) throws Exception {
        if (probOfState.length != ratesOfReturn.length) throw new Exception("Lengths must be the same");
        double expectedReturn = 0;
        for (int i = 0; i < probOfState.length; i++) expectedReturn += probOfState[i] * ratesOfReturn[i];
        System.out.println(String.format("Expected rate of return: %.2f%%", expectedReturn * 100));
        return expectedReturn;
    }

    static double calculateVariance(double[] probOfState, double[] ratesOfReturn) throws Exception {
        double expectedReturn = calculateExpectedReturn(probOfState, ratesOfReturn);
        double sumOfWeightedSquaredDeviation = 0;
        for (int i = 0; i < probOfState.length; i++)
            sumOfWeightedSquaredDeviation += Math.pow(ratesOfReturn[i] - expectedReturn, 2) * probOfState[i];
        System.out.println(String.format("Variance: %.7f", sumOfWeightedSquaredDeviation));
        return sumOfWeightedSquaredDeviation;
    }

    static double calculateStandardDeviation(double[] probOfState, double[] ratesOfReturn) throws Exception {
        double ret = Math.sqrt(calculateVariance(probOfState, ratesOfReturn));
        System.out.println(String.format("Standard deviation: %.2f%%", ret * 100));
        return ret;
    }

}
