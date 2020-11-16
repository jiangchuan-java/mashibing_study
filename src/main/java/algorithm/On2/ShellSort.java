package algorithm.On2;

import algorithm.Sorter;

/**
 * @Des: 希尔排序，有点归并的意思，分步插入排序
 * @Author: jiangchuan
 * <p>
 * @Date: 19-12-24
 */
public class ShellSort implements Sorter {

    //长度10，每隔5个一组，【0,5】【1,6】【2,7】【3,8】【4,9】
    //长度10，每隔2个一组，【0,3,6】【1,4,7】【2,5,8】【9
    public void sort(int[] array) {
        //array = new int[]{1,5,4,6,3,2,7,9,8,0};
        long start = System.currentTimeMillis();;

        int n = array.length;
        for(int gap = n/2; gap >=1; gap=gap/2){
            for(int i = 0;i<gap; i++){
                int sortedIndex = i;
                for(int j = i+gap; j<n; j=j+gap){
                    //int hasSorted = array[sortedIndex];
                    int temp = array[j];
                    int targetIndex = j;
                    for(int k = sortedIndex; k >=0; k=k-gap){
                        if(array[k] > temp){
                           array[k+gap] = array[k];
                           targetIndex = k;
                        } else {
                            break;
                        }
                    }
                    array[targetIndex] = temp;
                    sortedIndex = sortedIndex + gap;
                }

            }
        }

        long end = System.currentTimeMillis();;
        System.out.println("ShellSort " + array.length + " cos: " + (Double.valueOf(end-start)) + "ms");
    }
}
