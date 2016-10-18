package fireman;

import fireman.model.Mortgage;

import java.math.BigDecimal;

public class Fireman {
    private static final String usage = "usage: Fireman loanAmount loanPeriod apr";
    private static final String invalidInput = "Invalid inputs.";

    public static void main(String... args) {
        if (args[0].equals("-h") || args[0].equals("--help") || args.length != 3) {
            printHelp();
            return;
        }

        try {
            Double loanAmount = Double.parseDouble(args[0]);
            Integer loanPeriod = Integer.parseInt(args[1]);
            Double apr = Double.parseDouble(args[2]);

            // Construct mortgage
            Mortgage mortgage = Mortgage.newInstance(loanAmount, loanPeriod, apr);

            // Print table
            for (String[] row : buildMortgageTable(mortgage)) {
                System.out.format("%15s%15s%15s%15s%15s\n", row);
            }
        } catch (NumberFormatException e) {
            printInvalidInput();
            return;
        }
    }

    private static String[][] buildMortgageTable(Mortgage mortgage) {
        MortgageCalculator calculator = BasicMortgageCalculator.newInstance();
        String[][] table = new String[mortgage.getLoanYears() + 1][5];
        table[0] = new String[]{"Year", "Monthly Payment", "Balance", "Principal Paid", "Interest Paid"};
        BigDecimal monthlyPayment = calculator.calculateMonthlyPayment(mortgage);
        for (int year = 1; year <= mortgage.getLoanYears(); year++) {
            table[year] = new String[]{
                    String.valueOf(year),
                    monthlyPayment.toString(),
                    calculator.calculateYearlyBalance(mortgage, year).toString(),
                    calculator.calculateYearlyPrincipalPaid(mortgage, year).toString(),
                    calculator.calculateYearlyInterestPaid(mortgage, year).toString()
            };
        }

        return table;
    }

    private static void printHelp() {
        System.out.println(usage);
    }

    private static void printInvalidInput() {
        System.out.println(invalidInput);
    }
}
