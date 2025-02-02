package ru.job4j.ood.lsp.myexamples.first;

/*
сберегательный счёт
 */
public class SavingsAccount extends Account {

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Недостаточно средств");
        }
    }
}