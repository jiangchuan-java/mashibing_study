package sort_algorithm.On2;
import sort_algorithm.Sorter;


/**
 * @Des: 选择排序
 * @Author: jiangchuan
 * <p>
 * @Date: 19-12-24
 */
public class SelectSort implements Sorter {

    public void sort(int[] array){
        long start = System.currentTimeMillis();;
        for (int i = 0; i < array.length; i++) {
            int small = array[i];
            int smallIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if(array[j] < small){
                    small = array[j];
                    smallIndex = j;
                }
            }
            int temp = array[i];
            array[i] = small;
            array[smallIndex] = temp;
        }
        long end = System.currentTimeMillis();;
        System.out.println("SelectSort "+array.length+" cos: "+(Double.valueOf(end-start))+"ms");
    }
}
