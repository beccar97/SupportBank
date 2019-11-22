package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class JSONTransaction {

    private static final Logger LOGGER = LogManager.getLogger(JSONTransaction.class);

    private String date;
    private String fromAccount;
    private String toAccount;
    private String narrative;
    private String amount;

    JSONTransaction() {

    }

    String processTransaction() {
        Date dateObj;
        Account from;
        Account to;
        BigDecimal amountDec;

        try {
            dateObj = processDate(date);
        } catch (Exception e){
            LOGGER.error(String.format("Failed to parse JSON due to invalid date.\n %s. \n %s", date, e));
            return String.format("Failed to parse JSON due to invalid date: %s", date);
        }
        try {
            from = AccountRegistrar.findOrCreateAccount(fromAccount);
        } catch (Exception e){
            LOGGER.error(String.format("Failed to parse JSON due to invalid account.\n %s \n %s ", fromAccount,e));
            return String.format("Failed to parse JSON due to invalid account: %s", fromAccount);
        }
        try {
            to = AccountRegistrar.findOrCreateAccount(toAccount);
        } catch (Exception e){
            LOGGER.error(String.format("Failed to parse JSON due to invalid account.\n %s \n%s ", toAccount,e));
            return String.format("Failed to parse JSON due to invalid account: %s", toAccount);
        }
        try {
            amountDec = new BigDecimal(amount);
        } catch (Exception e) {
            LOGGER.error(String.format("Failed to parse line JSON due to issue with transaction amount: %s\n%s", amount, e));
            return String.format("Failed to parse JSON due to invalid transaction amount: %s", amount);
        }

        Transaction transactionObj = new Transaction(dateObj, from, to, narrative, amountDec);
        transactionObj.addToAccounts();
        return "";
    }

    private static Date processDate(String dateString) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    }
}