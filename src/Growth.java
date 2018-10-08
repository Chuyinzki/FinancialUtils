public class Growth {

    static double[] dividends = {2.25, 2.34, 2.41, 2.52, 2.59};

    public static void main(String[] args) {

        System.out.println("Final: " + calculateGeometricMean(dividends));
    }

    static double[] calculateGrowthPercentages(double[] dividends) {
        double[] ret = new double[dividends.length - 1];
        for(int i = 0; i < dividends.length - 1; i++)
            ret[i] = (dividends[i + 1] - dividends[i])/ dividends[i];
        return ret;
    }

    static double addNumbers(double[] numbers) {
        double add = 0;
        for(double d : numbers) add += d;
        return add;
    }

    static double multiplyNumbers(double[] numbers) {
        double mult = 1;
        for(double d : numbers) mult *= d;
        return mult;
    }

    static double calculateArithmeticMean(double[] percentages) {
        return addNumbers(percentages) / percentages.length;
    }

    static double calculateGeometricMean(double[] dividends) {
        return Math.pow(dividends[dividends.length - 1]/ dividends[0], (double)1/(dividends.length - 1)) - 1;
//        return Math.pow(multiplyNumbers(percentages), (double)1/percentages.length);
    }
}
