package sort_algorithm.Ologn;
import sort_algorithm.Sorter;


/**
 * @Des: 归并排序
 * @Author: jiangchuan
 * <p>
 * @Date: 19-12-26
 */
public class MergeSort implements Sorter {

    public void sort(int[] array) {
        //array = new int[]{1,5,4,6,3,2,7,9,8,0};
        long start = System.currentTimeMillis();;
        devide(array, 0, array.length - 1);
        long end = System.currentTimeMillis();;
        System.out.println("MergeSort " + array.length + " cos: " + (Double.valueOf(end-start)) + "ms");
    }

    //拆分[begin, end] 双闭区间进行拆分，直到begin = end时，只有一个元素则不可再继续拆分
    private void devide(int[] array, int begin, int end) {
        if (begin == end) {
            return;
        }
        int middle = begin + (end - begin) / 2;
        devide(array, begin, middle);
        devide(array, middle + 1, end);
        merge(array, begin, middle, end);
    }



    //合并操作
    private void merge(int[] array, int begin, int middle, int end) {
        int[] temp = new int[end - begin + 1];
        int i = 0;
        int p = begin;
        int q = middle + 1;
        while (p <= middle && q <= end) {
            if (array[p] < array[q]) {
                temp[i] = array[p];
                p++;
            } else {
                temp[i] = array[q];
                q++;
            }
            i++;
        }
        if (p <=middle) {
            while (p <= middle) {
                temp[i] = array[p];
                i++;
                p++;
            }
        }
        if (q <= end) {
            while (q <= end){
                temp[i] = array[q];
                i++;
                q++;
            }
        }
        int k = 0;
        for (int j = begin; j <= end; j++) {
            array[j] = temp[k];
            k++;
        }
    }
}
