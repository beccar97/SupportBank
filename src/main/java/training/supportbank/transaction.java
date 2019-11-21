package training.supportbank;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class Transaction {
    private Date date;
    private Account from;
    private Account to;
    private String narrative;
    private BigDecimal amount;

    Transaction(Date transDate, Account transFrom, Account transTo, String transNarrative, BigDecimal transAmount) {
        date = transDate;
        from = transFrom;
        to = transTo;
        narrative = transNarrative;
        amount = transAmount;
    }

    void addToAccounts() {
        from.addTransaction(this);
        to.addTransaction(this);
    }

    Account getFrom() {
        return from;
    }

    Account getTo() {
        return to;
    }

    BigDecimal getAmount() {
        return amount;
    }

    private String stringDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

}