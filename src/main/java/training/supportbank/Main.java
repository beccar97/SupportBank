package training.supportbank;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        String csvFile = (args.length == 1) ? args[0] : "Transactions2014.csv";

        AccountRegistrar accountRegistrar = new AccountRegistrar();
        ProcessCSV.readCSV(accountRegistrar, csvFile);

        while (true) {
            getCommands(accountRegistrar);
        }
    }

    private static void getCommands(AccountRegistrar registrar) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter command:");
        String command = myObj.nextLine();

        Pattern rListAll = Pattern.compile("List All", Pattern.CASE_INSENSITIVE);
        Matcher allMatcher = rListAll.matcher(command);

        Pattern rList = Pattern.compile("List (\\w+\\s?\\w*)", Pattern.CASE_INSENSITIVE);
        Matcher nameMatcher = rList.matcher(command);

        Pattern rExit = Pattern.compile("exit|q(uit)?", Pattern.CASE_INSENSITIVE);
        Matcher exitMatcher = rExit.matcher(command);

        if (allMatcher.find()) {
            registrar.listAll();
        }
        else if (nameMatcher.find()) {
            registrar.listTransactions(nameMatcher.group(1));
        } else if (exitMatcher.find()) {
            System.exit(0);
        }
        else {
            System.out.println(String.format("%s is invalid command.", command));
            System.out.println("Accepted commands: `List All` and `List [Account]`. To quit enter `Quit`.");
        }

    }
}




