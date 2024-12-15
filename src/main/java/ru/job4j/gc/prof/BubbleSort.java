package ru.job4j.gc.prof;


/*
Сортивока пузырьком
 */
public class BubbleSort implements Sort {

    @Override
    public boolean sort(Data data) {
        int[] array = data.getClone();
        sort(array);
        return true;
    }

    public void sort(int[] array) {
        int out;
        int in;
        for (out = array.length - 1; out >= 1; out--) {
            for (in = 0; in < out; in++) {
                if (array[in] > array[in + 1]) {
                    swap(array, in, in + 1);
                }
            }
        }
    }

    public void swap(int[] array, int in1, int in2) {
        int temp = array[in1];
        array[in1] = array[in2];
        array[in2] = temp;
    }
}
