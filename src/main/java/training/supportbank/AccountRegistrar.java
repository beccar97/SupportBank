package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

class AccountRegistrar {
    private static final Logger LOGGER = LogManager.getLogger(AccountRegistrar.class);

    private static HashMap<String, Account> accounts = new HashMap<>();

    static Account findOrCreateAccount(String name) {
        Account userAccount;
        if (accounts.containsKey(name.toLowerCase())) {
            userAccount = accounts.get(name.toLowerCase());
        }
        else {
            userAccount = new Account(name);
            accounts.put(name.toLowerCase(), userAccount);
        }
        return userAccount;
    }

    private static Account findAccount(String name) throws Exception {
        if (accounts.containsKey(name.toLowerCase())) {
            return accounts.get(name.toLowerCase());
        } else {
            throw new Exception("Account does not exist");
        }
    }

    static void listAll() {
        LOGGER.info("List all transactions");
        System.out.println(String.format("%-15s\t%8s", "Name", "Balance"));

        for (Account account : accounts.values()) {
            System.out.println(String.format("%-15s\t%8s", account.getName(), account.getBalanceString()));
        }
    }

    static void listTransactions(String name) {
        Account account;
        try {
            account = findAccount(name);
            account.printTransactions();
        } catch (Exception e){
            LOGGER.error(String.format("Failed to log transactions for %s. Error: %s", name, e));
            System.out.println("Could not log transactions for %s. Please ensure this user exists.");

        }
    }
}
