package ru.job4j.ood.lsp.myexamples.first;

public abstract class Account {
    protected double balance;

    public void deposit(double amount) {
        balance += amount;
    }

    public abstract void withdraw(double amount);
}