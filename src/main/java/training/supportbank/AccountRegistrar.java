package training.supportbank;

import java.util.HashMap;

class AccountRegistrar {
    private static HashMap<String, Account> accounts;

    AccountRegistrar() {
        accounts = new HashMap<>();
    }

    Account findAccount(String name) {
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

    void listAll() {
        System.out.println(String.format("%-15s\t%8s", "Name", "Balance"));

        for (Account account : accounts.values()) {
            System.out.println(String.format("%-15s\t%8s", account.getName(), account.getBalanceString()));
        }
    }

    void listTransactions(String name) {
        Account account = findAccount(name);
        account.printTransactions();
    }
}
