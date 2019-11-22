package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ParseException {
        getRunCommands();
    }

    private static void getRunCommands() throws IOException, ParseException {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter command:");

        while (myObj.hasNextLine()){
            String command = myObj.nextLine();
            parseCommand(command);
            System.out.println("\nEnter command:");
        }
    }

    private static void parseCommand(String command) throws IOException, ParseException {
        Pattern rImport = Pattern.compile("Import File (\\w[\\w.]+\\w)", Pattern.CASE_INSENSITIVE);
        Matcher importMatcher = rImport.matcher(command);

        Pattern rListAll = Pattern.compile("List All", Pattern.CASE_INSENSITIVE);
        Matcher allMatcher = rListAll.matcher(command);

        Pattern rList = Pattern.compile("List (\\w+\\s?\\w*)", Pattern.CASE_INSENSITIVE);
        Matcher nameMatcher = rList.matcher(command);

        Pattern rExit = Pattern.compile("exit|q(uit)?", Pattern.CASE_INSENSITIVE);
        Matcher exitMatcher = rExit.matcher(command);

        if (importMatcher.find()) {
            ProcessFile.processFile(importMatcher.group(1));
        }
        else if (allMatcher.find()) {
            AccountRegistrar.listAll();
        }
        else if (nameMatcher.find()) {
            AccountRegistrar.listTransactions(nameMatcher.group(1));
        } else if (exitMatcher.find()) {
            System.exit(0);
        }
        else {
            System.out.println(String.format("%s is invalid command.", command));
            System.out.println("Accepted commands: `Import File [filename]`, `List All` and `List [Account]`. To quit enter `Quit`.");
        }
    }

}




