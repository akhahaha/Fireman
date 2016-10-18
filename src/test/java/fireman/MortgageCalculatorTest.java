package fireman;

import fireman.model.Mortgage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MortgageCalculatorTest {
    private double EXACT = 0;
    private double ONE_DOLLAR = 1;
    private Mortgage mortgage1 = Mortgage.newInstance(200000, 30, 3.45);
    private MortgageCalculator calculator = BasicMortgageCalculator.newInstance();

    @Test
    public void calculateMonthlyPayment() throws Exception {
        assertEquals(892.52, calculator.calculateMonthlyPayment(mortgage1).doubleValue(), 0.01);
    }

    @Test
    public void calculateYearlyBalance() throws Exception {
        assertEquals(196129, calculator.calculateYearlyBalance(mortgage1, 1).doubleValue(), ONE_DOLLAR);
        assertEquals(187975, calculator.calculateYearlyBalance(mortgage1, 3).doubleValue(), ONE_DOLLAR);
        assertEquals(179240, calculator.calculateYearlyBalance(mortgage1, 5).doubleValue(), ONE_DOLLAR);
        assertEquals(125278, calculator.calculateYearlyBalance(mortgage1, 15).doubleValue(), ONE_DOLLAR);
        assertEquals(0, calculator.calculateYearlyBalance(mortgage1, 30).doubleValue(), EXACT);
    }

    @Test
    public void calculateYearlyPrincipalPaid() throws Exception {
        assertEquals(3871, calculator.calculateYearlyPrincipalPaid(mortgage1, 1).doubleValue(), ONE_DOLLAR);
        assertEquals(4147, calculator.calculateYearlyPrincipalPaid(mortgage1, 3).doubleValue(), ONE_DOLLAR);
        assertEquals(4443, calculator.calculateYearlyPrincipalPaid(mortgage1, 5).doubleValue(), ONE_DOLLAR);
        assertEquals(6270, calculator.calculateYearlyPrincipalPaid(mortgage1, 15).doubleValue(), ONE_DOLLAR);
        assertEquals(10513, calculator.calculateYearlyPrincipalPaid(mortgage1, 30).doubleValue(), ONE_DOLLAR);
    }

    @Test
    public void calculateYearlyInterestPaid() throws Exception {
        assertEquals(6839, calculator.calculateYearlyInterestPaid(mortgage1, 1).doubleValue(), ONE_DOLLAR);
        assertEquals(6563, calculator.calculateYearlyInterestPaid(mortgage1, 3).doubleValue(), ONE_DOLLAR);
        assertEquals(6267, calculator.calculateYearlyInterestPaid(mortgage1, 5).doubleValue(), ONE_DOLLAR);
        assertEquals(4440, calculator.calculateYearlyInterestPaid(mortgage1, 15).doubleValue(), ONE_DOLLAR);
        assertEquals(197, calculator.calculateYearlyInterestPaid(mortgage1, 30).doubleValue(), ONE_DOLLAR);
    }
}
