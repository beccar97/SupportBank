package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class ProcessCSV {
    static void readCSV(AccountRegistrar registrar, String csvFile) throws IOException, ParseException {
        BufferedReader br;
        String line;

        br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        while ((line = br.readLine()) != null) {
            parseLine(line, registrar);
        }
    }

    private static void parseLine(String line, AccountRegistrar registrar) throws ParseException {
        String cvsSplitBy = ",";
        String[] transaction = line.split(cvsSplitBy);
        Date date = processDate(transaction[0]);
        Account from = registrar.findAccount(transaction[1]);
        Account to = registrar.findAccount(transaction[2]);
        String narrative = transaction[3];
        BigDecimal amount = new BigDecimal(transaction[4]);

        Transaction transactionObj = new Transaction(date, from, to, narrative, amount);
        transactionObj.addToAccounts();
    }

    private static Date processDate(String dateString) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyy").parse(dateString);
    }
}