package sort_algorithm;

import sort_algorithm.Ologn.MergeSortByNoMerge;
import sort_algorithm.util.SortHelper;


/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 19-12-23
 */
public class SortTest {

    public static void main(String[] args) {


        Sorter mergeSort = new MergeSortByNoMerge();
        int[] array = new int[50];
        SortHelper.buildRandomArray(array,50);
        System.out.println("-----------------");
        mergeSort.sort(array);
        for(int i : array){
            System.out.println(i);
        }
    }
}
