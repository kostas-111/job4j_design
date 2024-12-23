package ru.job4j.gc.prof;

/*
Сортировка методом вставки
 */
public class InsertSort implements Sort {

    @Override
    public boolean sort(Data data) {
        int[] array = data.getClone();
        sort(array);
        return true;
    }

    private void sort(int[] array) {
        int in;
        int out;
        for (out = 1; out < array.length; out++) {
            int temp = array[out];
            in = out;
            while (in > 0 && array[in - 1] >= temp) {
                array[in] = array[in - 1];
                --in;
            }
            array[in] = temp;
        }
    }
}
