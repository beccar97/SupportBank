package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class ProcessCSV {
    private static final Logger LOGGER = LogManager.getLogger(ProcessCSV.class);

    private static String warningMessage = "";

    static void readCSV(String csvFile) throws IOException, ParseException {
        BufferedReader br;
        String line;
        int lineNum = 1;

        LOGGER.info("Attempt to read " + csvFile);

        br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        while ((line = br.readLine()) != null) {
            parseLine(line, lineNum);
            lineNum++;
        }

        if (!warningMessage.equals("")) System.out.println(warningMessage);
    }

    private static void parseLine(String line, int lineNum) throws ParseException {
        String cvsSplitBy = ",";
        String[] transaction = line.split(cvsSplitBy);

        Date date;
        Account from;
        Account to;
        String narrative;
        BigDecimal amount;

        try {
            date = processDate(transaction[0]);
            from = AccountRegistrar.findOrCreateAccount(transaction[1]);
            to = AccountRegistrar.findOrCreateAccount(transaction[2]);
            narrative = transaction[3];
            amount = new BigDecimal(transaction[4]);
        } catch (Exception e){
            LOGGER.error(String.format("Failed to parse line %d of input: %s",lineNum, line));
            warningMessage += String.format("Could not parse line %d: \n %s\n", lineNum, line);
            return;
        }

        Transaction transactionObj = new Transaction(date, from, to, narrative, amount);
        transactionObj.addToAccounts();
    }

    private static Date processDate(String dateString) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyy").parse(dateString);
    }
}