package fireman;

import fireman.model.Mortgage;

import java.math.BigDecimal;

public interface MortgageCalculator {
    BigDecimal calculateMonthlyPayment(Mortgage mortgage);

    BigDecimal calculateYearlyBalance(Mortgage mortgage, int year);

    BigDecimal calculateYearlyPrincipalPaid(Mortgage mortgage, int year);

    BigDecimal calculateYearlyInterestPaid(Mortgage mortgage, int year);
}
