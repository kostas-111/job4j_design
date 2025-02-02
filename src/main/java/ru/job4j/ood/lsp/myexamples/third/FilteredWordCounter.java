package ru.job4j.ood.lsp.myexamples.third;

import java.util.Set;

/*
Есть класс WordCounter, который считает слова в тексте, и его подкласс FilteredWordCounter,
который фильтрует определённые слова перед подсчётом.
класс FilteredWordCounter нарушает принцип LSP, так как меняет поведение метода countWords(),
исключая некоторые слова из подсчета, чего нет в базовом классе WordCounter.
Это может привести к тому, что код, рассчитанный на работу с объектами типа WordCounter,
получит неожиданный результат, если передать ему объект типа FilteredWordCounter.
 */
public class FilteredWordCounter extends WordCounter {
    private Set<String> filteredWords;

    public FilteredWordCounter(String text, Set<String> filteredWords) {
        super(text);
        this.filteredWords = filteredWords;
    }

    @Override
    public int countWords() {
        String[] words = text.split("\\s+");
        int count = 0;
        for (String word : words) {
            if (!filteredWords.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
}