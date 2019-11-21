package training.supportbank;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

class Account {
    private String name;
    private Set<Transaction> transactions;
    private BigDecimal balance;

    Account(String accountName) {
        name = accountName;
        transactions = new HashSet<>();
        balance = BigDecimal.ZERO;
    }

    String getName() {
        return name;
    }

    BigDecimal getBalance() {
        return balance;
    }

    String getBalanceString() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.UK);
        return format.format(balance);
    }

    void printTransactions() {
        System.out.println(transactions);
    }

    void addTransaction(Transaction transaction) {
        if (transaction.getFrom() == this) {
            balance = balance.subtract(transaction.getAmount());
            transactions.add(transaction);
        } else if (transaction.getTo() == this) {
            balance = balance.add(transaction.getAmount());
            transactions.add(transaction);
        }
    }
}
