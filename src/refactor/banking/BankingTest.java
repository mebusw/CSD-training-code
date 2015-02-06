package refactor.banking;

import org.junit.Test;

import java.net.ConnectException;

import static org.junit.Assert.assertEquals;

public class BankingTest {
    Account account = Account.openAccount();

    @Test
    public void initial_amount() {
        long initialAmount = 100L;
        account.deposit(initialAmount);
        assertEquals(initialAmount, account.getBalance());
    }

    @Test
    public void withdraw() {
        long initialAmount = 100L;
        account.deposit(initialAmount);
        account.withdraw(initialAmount);
        assertEquals(0L, account.getBalance());
    }

    @Test(expected = InsufficientFundsException.class)
    public void debit_over_withdraw() {
        Account debit = Account.openDebitAccount();

        long initialAmount = 100L;
        debit.deposit(initialAmount);
        debit.withdraw(initialAmount+1);
    }

    @Test
    public void debit_initial_amount() {
        account = Account.openDebitAccount();
        initial_amount();
    }

    @Test
    public void debit_withdraw() {
        account = Account.openDebitAccount();
        withdraw();
    }

    public void service() throws ConnectException {
        System.out.println(new IAssessmentService.AssessmentService().getCredit("foo"));
    }
}
