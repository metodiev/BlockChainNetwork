package com.blockchain.node.staticdata;

import com.blockchain.node.data.Wallet;

import java.util.ArrayList;
import java.util.List;

public class WalletStaticData {

    public static List<Wallet> wallets = new ArrayList<Wallet>();

    public static void addNewWallet(Wallet wallet){

        wallets.add(wallet);
    }
}
