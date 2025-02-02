package ru.job4j.ood.lsp.invariants;

/*
абонент какого-то оператора
 */
public class SomeOperatorSubscriber extends Subscriber {

    public SomeOperatorSubscriber(PhoneNumber phoneNumber) {
        super(phoneNumber);
    }

    @Override
    public void setPhoneNumber(PhoneNumber phoneNumber) {
        /*
        some specific logic;
        */

        /*
        Забыли сделать проверку. Возможно не валидное состояние
        */
        this.phoneNumber = phoneNumber;
    }
}