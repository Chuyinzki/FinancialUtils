import java.util.ArrayList;

public class MACRS {

    static final Double[] sevenYear = {14.29, 24.49, 17.49, 12.49, 8.93, 8.92, 8.93, 4.46};
    static final Double[] threeYear = {33.33, 44.45, 14.81, 7.41};
    static final Double[] bonus100 = {100.0, 0d, 0d, 0d};
    static final String BOOK_VALUE = "BOOK_VALUE";
    static final String DEPRECIATION_VALUE = "DEPRECIATION_VALUE";

    public static void main(String[] args) {
//        System.out.println(getOCF(661400, 422900, 100100, 22));
//        System.out.println(getOCF(1675000, 645000, getDepreciationValue(2370000, straightLinePercentages(3), 3), 21));
//        System.out.println(getAftertaxSalvageValue(getBookValue(705000, straightLinePercentages(8), 5), 153000, 24));
        System.out.println(String.format("NPV = %.2f",
                getNPV(32500, 520000, straightLinePercentages(5), 154000, 0, 83000, 23, 11)));
//        printSchedule(982000, sevenYear);
    }

    static double getNPV(double nwc, double capSpending, Double[] depreciationSchedule, double sales, double cost,
                         double priceSold, double taxRate, double discountRate) {
        double ret = 0;
        for(int i = 0; i < depreciationSchedule.length; i++)
            ret += getOcfAtYear(nwc, capSpending, depreciationSchedule, sales, cost, priceSold, taxRate, i) / Math.pow(1 + discountRate / 100, i);
        return ret;
    }

    static double getOcfAtYear(double nwc, double capSpending, Double[] depreciationSchedule, double sales, double cost,
                               double priceSold, double taxRate, int year) {
        double ret;
        if(year == 0) {
            ret =  (nwc + capSpending) * -1;
        } else if (year < depreciationSchedule.length - 1) {
            ret =  getOCF(sales, cost, getDepreciationValue(capSpending, depreciationSchedule, year), taxRate);
        } else {
            ret =  getOCF(sales, cost, getDepreciationValue(capSpending, depreciationSchedule, year), taxRate) +
                    getAftertaxSalvageValue(getBookValue(capSpending, depreciationSchedule, year), priceSold, taxRate) +
                    nwc;
        }
        System.out.println(String.format("OCF at year %d is %.2f", year, ret));
        return ret;
    }

    static void printSchedule(final double initialValue, Double[] percentages) {
        getBookValue(initialValue, percentages, -1);
    }

    static Double getDepreciationValue(double initialValue, Double[] percentages, int year) {
        return getValueAtYear(initialValue, percentages, year, DEPRECIATION_VALUE);
    }

    static Double getBookValue(double initialValue, Double[] percentages, int year){
        return getValueAtYear(initialValue, percentages, year, BOOK_VALUE);
    }

    static Double getValueAtYear(final double initialValue, Double[] percentages, int year, String retVal) {
        int i = 1;
//        System.out.println("Year\tBVal\tDep\tBVal");
        double bval = initialValue;
        for (double percent : percentages) {
            double dep = initialValue * percent / 100;
            double bvalFinal = bval - dep;
//            System.out.println(String.format("%d\t%.2f\t%.2f\t%.2f", i, bval, dep, bvalFinal));
            bval = bvalFinal;
            i++;
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

    static double getStraightLineAftertaxSalvageValue(double initialValue, int depreciationYearsToZero, int yearSold,
                                                      double priceSold, double taxRate) {
        return getAftertaxSalvageValue(getBookValue(initialValue, straightLinePercentages(depreciationYearsToZero), yearSold),
                priceSold, taxRate);
    }

    static double getAftertaxSalvageValue(double bookValue, double priceSold, double taxRate) {
        double diff = priceSold - bookValue;
        double ret = Math.round((priceSold - diff * taxRate / 100) * 100.0) / 100.0;
        System.out.println("Aftertax salvage value = " + ret);
        return ret;
    }

    static Double[] straightLinePercentages(int years) {
        ArrayList<Double> doubles = new ArrayList<>();
        for (int i = 0; i < years + 1; i++) {
            doubles.add((double) 100 / years);
        }
        return doubles.toArray(new Double[0]);
    }

    static double getOCF(double sales, double cost, double dep, double taxRate) {
        double ebit = getEBIT(sales, cost, dep);
        return Math.round((ebit + dep - getTaxDue(ebit, taxRate)) * 100.0) / 100.0;
    }

    static double getTaxDue(double ebit, double taxRate) {
        return ebit * taxRate / 100;
    }

    static double getEBIT(double sales, double cost, double dep) {
        return sales - cost - dep;
    }
}
