import java.util.ArrayList;

public class MACRS {

    static final Double[] sevenYear = {14.29, 24.49, 17.49, 12.49, 8.93, 8.92, 8.93, 4.46};

    public static void main(String[] args) {
        getAftertaxSalvageValue(690000, 8, 5, 147000, 21);
    }

    static void printSchedule(final double initialValue, Double[] percentages) {
        getValueAtYear(initialValue, percentages, -1);
    }

    static Double getValueAtYear(final double initialValue, Double[] percentages, int year) {
        int i = 1;
        System.out.println("Year\tBVal\tDep\tBVal");
        double bval = initialValue;
        for (double percent : percentages) {
            double dep = Math.round(initialValue * percent/100 * 100.0) / 100.0;
            System.out.println(i++ + "\t" + bval + "\t" + dep + "\t" + (bval = Math.round((bval - dep) * 100.0) / 100.0));
            if(year + 1 == i) return bval;
        }
        return null;
    }

    static double getAftertaxSalvageValue(double initialValue, int depreciationYearsToZero, int yearSold,
                                          double priceSold, double taxRate) {
        double bookVal = getValueAtYear(initialValue, straightLinePercentages(depreciationYearsToZero), yearSold);
        double diff = priceSold - bookVal;
        double ret = Math.round((priceSold - diff*taxRate/100) * 100.0) / 100.0;
        System.out.println("Aftertax salvage value = " + ret);
        return ret;
    }

    static Double[] straightLinePercentages(int years) {
        ArrayList<Double> doubles = new ArrayList<>();
        for(int i = 0; i < years; i++) {
            doubles.add(Math.round(((double)100/years) * 100.0) / 100.0);
        }
        return doubles.toArray(new Double[0]);
    }
}
