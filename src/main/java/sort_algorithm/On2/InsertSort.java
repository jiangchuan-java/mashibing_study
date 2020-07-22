package sort_algorithm.On2;
import sort_algorithm.Sorter;

/**
 * @Des: 插入排序
 * @Author: jiangchuan
 * <p>
 * @Date: 19-12-24
 */
//插入排序
public class InsertSort implements Sorter {

    public void sort(int array[]){
        long start = System.currentTimeMillis();;
        int sortedCount = 0;
        //遍历无序节点
        for (int i = 1; i < array.length; i++) {
            int target = array[i];
            int targetIndex = i;
            for(int j=sortedCount;j>=0;j--){
                if(target < array[j]){
                    array[j+1] = array[j];
                    targetIndex = j;
                } else {
                    break;
                }
            }
            array[targetIndex] = target;
            sortedCount++;
        }
        long end = System.currentTimeMillis();;
        System.out.println("InsertSort "+array.length+" cos: "+(end-start)+"ms");
    }
}
