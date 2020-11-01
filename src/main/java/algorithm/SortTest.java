package algorithm;


/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 19-12-23
 */
public class SortTest {

    public static void main(String[] args) {


//        Sorter mergeSort = new MergeSortByNoMerge();
//        int[] array = new int[50];
//        SortHelper.buildRandomArray(array,50);
//        System.out.println("-----------------");
//        mergeSort.sort(array);
//        for(int i : array){
//            System.out.println(i);
//        }

        int a = 10;
        int b = 20;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
