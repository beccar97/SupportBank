package training.supportbank;

import java.util.HashMap;

class AccountRegistrar {
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

    static void listAll() {
        System.out.println(String.format("%-15s\t%8s", "Name", "Balance"));

        for (Account account : accounts.values()) {
            System.out.println(String.format("%-15s\t%8s", account.getName(), account.getBalanceString()));
        }
    }

    static void listTransactions(String name) {
        Account account = findOrCreateAccount(name);
        account.printTransactions();
    }
}
