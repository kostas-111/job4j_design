package ru.job4j.ood.lsp.postconditions;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

class WorkDays implements Iterable<Integer> {

    private Map<LocalDate, Integer> workDays = new LinkedHashMap<>();

    public void add(LocalDate date, int hours) {
        workDays.put(date, hours);
    }

    @Override
    public Iterator<Integer> iterator() {
        return workDays.values().iterator();
    }
}