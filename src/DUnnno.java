public class DUnnno {

    public static void main(String[] args) {
        double rate = .11;
        double div = 9.5;
        int years = 11;

        double finalVal = 0;
        for (int i = 0; i < years; i++) {
            finalVal += div / (Math.pow(1 + rate, i + 1));
        }
        System.out.println(finalVal);
    }
}
