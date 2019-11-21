package training.supportbank;

import java.util.HashMap;

class AccountRegistrar {
    private static HashMap<String, Account> accounts;

    AccountRegistrar() {
        accounts = new HashMap<>();
    }

    Account findAccount(String name) {
        Account userAccount;
        if (accounts.containsKey(name)) {
            userAccount = accounts.get(name);
        }
        else {
            userAccount = new Account(name);
            accounts.put(name, userAccount);
        }
        return userAccount;
    }

    void listAll() {
        System.out.println(String.format("%-15s\t%8s", "Name", "Balance"));

        for (Account account : accounts.values()) {
            System.out.println(String.format("%-15s\t%8s", account.getName(), account.getBalanceString()));
        }
    }
}
