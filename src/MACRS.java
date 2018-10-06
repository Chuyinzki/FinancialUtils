public class MACRS {

    static final double[] sevenYear = {14.29, 24.49, 17.49, 12.49, 8.93, 8.92, 8.93, 4.46};

    public static void main(String[] args) {
        printSchedule(972000, sevenYear);
    }

    static void printSchedule(final double initialValue, double[] percentages) {
        int i = 1;
        System.out.println("Year\tBVal\tDep\tBVal");
        double bval = initialValue;
        for (double percent : percentages) {
            double dep = Math.round(initialValue * percent/100 * 100.0) / 100.0;
            System.out.println(i++ + "\t" + bval + "\t" + dep + "\t" + (bval = Math.round((bval - dep) * 100.0) / 100.0));
        }
    }
}
