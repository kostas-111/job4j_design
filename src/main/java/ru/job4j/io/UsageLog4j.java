package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        long varOne = 56785L;
        char varTwo = 'k';
        float varThree = 3.14F;
        boolean varFour = true;
        int varFive = 34;
        short varSix = 32000;
        byte varSeven = 120;
        double varEight = 54.5;

        LOG.info("Целые типы переменных. byte: {}, short: {}, int: {}, long: {}, char: {} ", varSeven, varSix, varFive, varOne, varTwo);
        LOG.info("Вещественные типы переменных. float: {}, double: {}", varThree, varEight);
        LOG.info("Булевый тип переменных. boolean: {}", varFour);
    }
}