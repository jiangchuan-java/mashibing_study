package algorithm.Ologn;

/**
 * @Des: 三路快排
 * @Author: jiangchuan
 * <p>
 * @Date: 2020-02-08
 */
public class QuickSortByThreeWay {

    public void sort(int[] array) {
        //array = new int[]{1,5,4,6,3,3,3,3,2,7,9,8,0};
        long start = System.currentTimeMillis();

        quickSort(array,0,array.length-1);

        long end = System.currentTimeMillis();;

        System.out.println("QuickSortByThreeWay " + array.length + " cos: " + (Double.valueOf(end-start)) + "ms");
    }

    //找一个标兵，将数组分为大于标兵与小于标兵与等于标兵三部分，当数组只要一个元素时停止排序
    private void quickSort(int[] array, int left, int right){
        if(left >= right){
            return;
        }
        int flag = array[left];
        int lt = left; //小于标兵，left当前元素不是,+1是left初始值是flag
        int gt = right+1; //大于标兵, right当前元素不是
        // [left+1,lt],[lt+1,i-1],[gt,right]
        for(int i = left+1; i < gt;){
            if(array[i] < flag){
                int temp = array[lt+1];
                array[lt+1] = array[i];
                array[i] = temp;
                lt++;
                i++;
            } else if(array[i] > flag){
                int temp = array[gt-1];
                array[gt-1] = array[i];
                array[i] = temp;
                gt--;
            } else {
                i++;
            }
        }
        int temp = array[lt];
        array[lt] = flag;
        array[left] = temp;
        quickSort(array,left,lt-1);
        quickSort(array,gt,right);
    }

}
