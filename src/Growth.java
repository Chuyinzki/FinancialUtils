public class Growth {

    static double[] dividends = {2.25, 2.34, 2.41, 2.52, 2.59};

    public static void main(String[] args) {

        System.out.println("Final: " + calculateGeometricMean(calculateGrowthPercentages(dividends)));
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

    static double calculateArithmeticMean(double[] percentages) {
        return addNumbers(percentages) / percentages.length;
    }

    static double calculateGeometricMean(double[] percentages) {
        return Math.pow(addNumbers(percentages), (double)1/percentages.length) - 1;
    }
}
