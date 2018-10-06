import java.util.ArrayList;

public class MACRS {

    static final Double[] sevenYear = {14.29, 24.49, 17.49, 12.49, 8.93, 8.92, 8.93, 4.46};
    static final String BOOK_VALUE = "BOOK_VALUE";
    static final String DEPRECIATION_VALUE = "DEPRECIATION_VALUE";

    public static void main(String[] args) {
        getOCF(1755000, 665000, getDepreciationValue(2370000, straightLinePercentages(3), 3),24);
    }

    static void printSchedule(final double initialValue, Double[] percentages) {
        getBookValue(initialValue, percentages, -1);
    }

    static double getDepreciationValue(double initialValue, Double[] percentages, int year) {
        return getValueAtYear(initialValue, percentages, year, DEPRECIATION_VALUE);
    }

    static double getBookValue(double initialValue, Double[] percentages, int year){
        return getValueAtYear(initialValue, percentages, year, BOOK_VALUE);
    }

    static Double getValueAtYear(final double initialValue, Double[] percentages, int year, String retVal) {
        int i = 1;
        System.out.println("Year\tBVal\tDep\tBVal");
        double bval = initialValue;
        for (double percent : percentages) {
            double dep = initialValue * percent / 100;
            System.out.println(i++ + "\t" + bval + "\t" + dep + "\t" + (bval = bval - dep));
            if (year + 1 == i) {
                if(BOOK_VALUE.equals(retVal))
                    return roundTwoDecimalPlaces(bval);
                else if(DEPRECIATION_VALUE.equals(retVal))
                    return roundTwoDecimalPlaces(dep);
                else return null;
            }
        }
        return null;
    }

    static double roundTwoDecimalPlaces(double val) {
        return roundToDecimals(val, 2);
    }

    static double roundToDecimals(double val, int places) {
        double adjustor = Math.pow(10, places);
        return Math.round(val * adjustor) / adjustor;
    }

    static double getAftertaxSalvageValue(double initialValue, int depreciationYearsToZero, int yearSold,
                                          double priceSold, double taxRate) {
        double bookVal = getBookValue(initialValue, straightLinePercentages(depreciationYearsToZero), yearSold);
        double diff = priceSold - bookVal;
        double ret = Math.round((priceSold - diff * taxRate / 100) * 100.0) / 100.0;
        System.out.println("Aftertax salvage value = " + ret);
        return ret;
    }

    static Double[] straightLinePercentages(int years) {
        ArrayList<Double> doubles = new ArrayList<>();
        for (int i = 0; i < years; i++) {
            doubles.add((double) 100 / years);
        }
        return doubles.toArray(new Double[0]);
    }

    static double getOCF(double sales, double cost, double dep, double taxRate) {
        double ebit = getEBIT(sales, cost, dep);
        double ret = Math.round((ebit + dep - getTaxDue(ebit, taxRate)) * 100.0) / 100.0;
        System.out.println("OCF = " + ret);
        return ret;
    }

    static double getTaxDue(double ebit, double taxRate) {
        return ebit * taxRate / 100;
    }

    static double getEBIT(double sales, double cost, double dep) {
        return sales - cost - dep;
    }
}
