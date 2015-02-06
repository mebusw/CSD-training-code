package refactor.banking;

public class Account {
    private static IAssessmentService service = new IAssessmentService.AssessmentService();

    private final boolean isDebit;
    private long balance;

    protected Account(boolean isDebit) {
        this.isDebit = isDebit;
    }

    public static Account openDebitAccount() {
        return new Account(true);
    }

    public static Account openAccount() {
        return new Account(false);
    }

    public long getBalance() {
        return balance;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public void withdraw(long amount) {
        boolean insufficientFunds = getBalance() < amount;
        balance -= amount;
        if (isDebit && insufficientFunds) {
            throw new InsufficientFundsException();
        }
    }
}
