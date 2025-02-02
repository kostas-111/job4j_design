package ru.job4j.ood.lsp.myexamples.third;

public class WordCounter {
    String text;

    public WordCounter(String text) {
        this.text = text;
    }

    public int countWords() {
        return text.split("\\s+").length;
    }
}