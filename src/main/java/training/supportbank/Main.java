package training.supportbank;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        AccountRegistrar accountRegistrar = new AccountRegistrar();
        ProcessCSV.readCSV(accountRegistrar);
        getCommands(accountRegistrar);
    }

    private static void getCommands(AccountRegistrar registrar) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter commands");

        String command = myObj.nextLine();

        Pattern rListAll = Pattern.compile("List All", Pattern.CASE_INSENSITIVE);
        Matcher allMatcher = rListAll.matcher(command);

        Pattern rList = Pattern.compile("List (\\w+\\s?\\w*)", Pattern.CASE_INSENSITIVE);
        Matcher nameMatcher = rList.matcher(command);

        if (allMatcher.find()) {
            registrar.listAll();
        }
        else if (nameMatcher.find()) {
            registrar.listTransactions(nameMatcher.group(1));
        }
        else {
            System.out.println(String.format("%s is invalid command.", command));
            System.out.println("Accepted commands: `List All` and `List [Account]`");
        }

    }
}




