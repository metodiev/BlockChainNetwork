package com.blockchain.node.data;

public class ConfirmedBalances {

    private String hashAddress;
    private int moneySent ;

    public ConfirmedBalances(String hashAddress, int moneySent) {
        this.hashAddress = hashAddress;
        this.moneySent = moneySent;
    }

    public String getHashAddress() {
        return hashAddress;
    }

    public void setHashAddress(String hashAddress) {
        this.hashAddress = hashAddress;
    }

    public int getMoneySent() {
        return moneySent;
    }

    public void setMoneySent(int moneySent) {
        this.moneySent = moneySent;
    }
}
