package training.supportbank;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

class Account {
    private static final Logger LOGGER = LogManager.getLogger(Account.class);

    private String name;
    private Set<Transaction> transactions;
    private BigDecimal balance;

    Account(String accountName) {
        LOGGER.info("Create account for " + accountName);
        name = accountName;
        transactions = new HashSet<>();
        balance = BigDecimal.ZERO;
    }

    String getName() {
        return name;
    }

    String getBalanceString() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.UK);
        return format.format(balance);
    }

    void printTransactions() {
        LOGGER.info("Print transactions for " + getName());
        System.out.println(String.format("Transactions for %s", name));
        System.out.println(String.format("%-10s\t%-15s\t%-15s\t%-35s\t%8s", "Date", "From", "To", "Narrative", "Amount"));
        transactions.forEach(Transaction::printTransaction);
    }

    void addTransaction(Transaction transaction) {
        if (transaction.getFromAccount().getName().equals(getName())) {
            balance = balance.subtract(transaction.getAmount());
            transactions.add(transaction);
        } else if (transaction.getToAccount().getName().equals(getName())) {
            balance = balance.add(transaction.getAmount());
            transactions.add(transaction);
        }
    }
}
