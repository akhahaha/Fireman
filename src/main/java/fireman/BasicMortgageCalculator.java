package fireman;

import fireman.model.Mortgage;

import java.math.BigDecimal;

public class BasicMortgageCalculator implements MortgageCalculator {
    public static MortgageCalculator newInstance() {
        return new BasicMortgageCalculator();
    }

    private BasicMortgageCalculator() {
    }

    @Override
    public BigDecimal calculateMonthlyPayment(Mortgage mortgage) {
        // P = L[c(1 + c)^n]/[(1 + c)^n - 1]
        BigDecimal L = mortgage.getLoanAmount();
        BigDecimal c = mortgage.getAnnualPercentageRate().divide(BigDecimal.valueOf(1200), 8, BigDecimal.ROUND_HALF_UP);
        int n = mortgage.getLoanYears() * 12;

        BigDecimal x = BigDecimal.ONE.add(c);
        BigDecimal xN = BigDecimal.valueOf(Math.pow(x.doubleValue(), n));
        return L.multiply(c).multiply(xN).divide(xN.subtract(BigDecimal.ONE), 2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal calculateYearlyBalance(Mortgage mortgage, int year) {
        // B = L[(1 + c)^n - (1 + c)^p] / [(1 + c)^n - 1]
        BigDecimal L = mortgage.getLoanAmount();
        BigDecimal c = mortgage.getAnnualPercentageRate().divide(BigDecimal.valueOf(1200), 8, BigDecimal.ROUND_HALF_UP);
        int n = mortgage.getLoanYears() * 12;
        int p = year * 12;

        BigDecimal x = BigDecimal.ONE.add(c);
        BigDecimal xN = BigDecimal.valueOf(Math.pow(x.doubleValue(), n));
        BigDecimal xP = BigDecimal.valueOf(Math.pow(x.doubleValue(), p));
        return L.multiply(xN.subtract(xP)).divide(xN.subtract(BigDecimal.ONE), 2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal calculateYearlyPrincipalPaid(Mortgage mortgage, int year) {
        // payment - interest paid
        return calculateMonthlyPayment(mortgage).multiply(BigDecimal.valueOf(12))
                .subtract(calculateYearlyInterestPaid(mortgage, year));
    }

    @Override
    public BigDecimal calculateYearlyInterestPaid(Mortgage mortgage, int year) {
        // end balance + payment - start balance
        return calculateYearlyBalance(mortgage, year)
                .add(calculateMonthlyPayment(mortgage).multiply(BigDecimal.valueOf(12)))
                .subtract(calculateYearlyBalance(mortgage, year - 1));
    }
}
