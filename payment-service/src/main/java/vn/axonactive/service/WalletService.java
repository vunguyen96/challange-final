package vn.axonactive.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import vn.axonactive.model.Wallet;

@ApplicationScoped
public class WalletService {
    private Set<Wallet> wallets;

    public WalletService() {
        init();
    }

    public void init() {
        wallets = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        wallets.add(new Wallet("1", "test1", 100));
        wallets.add(new Wallet("2", "test2", 200));
        wallets.add(new Wallet("3", "test3", 300));
        wallets.add(new Wallet("4", "test4", 400));
    }

    public Set<Wallet> getAll() {
        return wallets;
    }

    public Optional<Wallet> getByUserName(String userName) {
        return wallets.stream().filter(wallet -> wallet.getUserName().equals(userName)).findFirst();
    }

    public Wallet pay(Wallet wallet, int cost) {
        wallet.setBalance(wallet.getBalance() - cost);
        return wallet;
    }

}