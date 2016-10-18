package fireman.model;

import java.math.BigDecimal;

public class Mortgage {
    private final BigDecimal loanAmount;
    private final int loanYears;
    private final BigDecimal annualPercentageRate;

    public static Mortgage newInstance(double loanAmount, int loanYears, double annualPercentageRate) {
        return new Mortgage(BigDecimal.valueOf(loanAmount), loanYears, BigDecimal.valueOf(annualPercentageRate));
    }

    public static Mortgage newInstance(BigDecimal loanAmount, int loanYears, BigDecimal annualPercentageRate) {
        return new Mortgage(loanAmount, loanYears, annualPercentageRate);
    }

    private Mortgage(BigDecimal loanAmount, int loanYears, BigDecimal annualPercentageRate) {
        this.loanAmount = loanAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.loanYears = loanYears;
        this.annualPercentageRate = annualPercentageRate;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public int getLoanYears() {
        return loanYears;
    }

    public BigDecimal getAnnualPercentageRate() {
        return annualPercentageRate;
    }
}
