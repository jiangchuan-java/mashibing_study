package algorithm.On2;

import algorithm.Sorter;

/**
 * @Des: 冒泡排序
 * @Author: jiangchuan
 * <p>
 * @Date: 19-12-23
 */
public class MaoPaoSort implements Sorter {

    public void sort(int[] array) {
        long start = System.currentTimeMillis();;
        //每轮都把最大的放在数组最后，直到遍历完所有的
        int biggerIndex = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < biggerIndex; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j]; //大数上浮的过程
                    array[j] = temp;
                }
            }
            biggerIndex--;
        }
        long end = System.currentTimeMillis();;
        System.out.println("MaoPaoSort " + array.length + " cos: " + (Double.valueOf(end-start)) + "ms");
    }

    public static void sort2(int[] array) {
        long start = System.currentTimeMillis();;
        //每轮都把最大的放在数组最后，直到遍历完所有的
        int biggerIndex = array.length-1;
        for (int i = 0; i < array.length; i++) {
            int biggest = array[0];
            for (int j = 0; j < biggerIndex; j++) {
                if (biggest > array[j + 1]) {
                    array[j] = array[j + 1];
                    if(j+1 == biggerIndex)
                        array[j+1] = biggest;
                } else {
                    array[j] = biggest;
                    biggest = array[j+1];
                }
            }
            biggerIndex--;
        }
        long end = System.currentTimeMillis();;
        System.out.println("MaoPaoSort2 " + array.length + " cos: " + (end - start) + "ms");
    }


}
