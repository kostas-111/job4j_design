package ru.job4j.ood.lsp.myexamples.first;

/*
У нас есть базовый класс Account, представляющий банковский счёт, и два его подкласса:
SavingsAccount (сберегательный счёт) и CheckingAccount (текущий счёт).
Класс CheckingAccount вводит дополнительное ограничение на сумму снятия средств,
которое отсутствует в базовом классе Account. Это нарушает принцип LSP,
так как методы подкласса не соответствуют контракту базового класса.
Клиентский код, ожидающий использовать любой тип счёта через абстрактый класс Account,
может столкнуться с неожиданной ошибкой, если ему передадут объект типа CheckingAccount.
 */
public class CheckingAccount extends Account {

    @Override
    public void withdraw(double amount) {
        if (amount > 1000) {
            throw new IllegalArgumentException("Превышен лимит снятия");
        } else if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Недостаточно средств");
        }
    }
}