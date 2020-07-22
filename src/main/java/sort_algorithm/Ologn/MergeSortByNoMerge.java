package sort_algorithm.Ologn;

import sort_algorithm.Sorter;

/**
 * @Des: 使用非递归的方式实现归并排序
 * @Author: jiangchuan
 * <p>
 * @Date: 20-1-6
 */
public class MergeSortByNoMerge implements Sorter {

    public void sort(int[] array) {
        //array = new int[]{1, 5, 4, 6, 3, 2, 7, 9, 8, 0};
        int n = array.length;
        if(n <=1){
            return;
        }
        //1,2,4,8,16,每次元素间隔翻倍，[0,2)、[0,4)、[0,8),左闭右开
        for (int i = 1; i <= n; i = i * 2) {
            for (int j = 0; j < n; j = j + 2 * i) {
                int begin = j;
                int end = j + 2 * i - 1;
                if(end > n){
                    end = n-1;
                }
                int middle = begin + (end - begin) / 2;
                if(end-begin<i){
                    middle = begin-1;
                    begin = begin-2*i;
                }
                //System.out.println(begin + "," + end);
                merge(array, begin, middle, end);
            }
        }
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
        if (p <= middle) {
            while (p <= middle) {
                temp[i] = array[p];
                i++;
                p++;
            }
        }
        if (q <= end) {
            while (q <= end) {
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
