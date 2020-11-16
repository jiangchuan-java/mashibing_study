package algorithm.util;

import algorithm.Sorter;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 19-12-23
 */
public class SortHelper {

    private static Random random = new Random();

    //创建n个数据项的无序数组，无重复项
    public static void buildRandomArray(int[] array, int n){
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0;i<array.length;i++){
            int num = random.nextInt(n);
            while (set.contains(num)){
                num = random.nextInt(n);
            }
            set.add(num);
            array[i] = num;
        }
    }

    //创建n个数据项的无序数组，多数都是重复的
    public static void buildRepeatArray(int[] array, int n){
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0;i<array.length;i++){
            int num = random.nextInt(n);
            array[i] = num;
        }
    }

    public static void incrPower10Rnadom(Sorter sorter, int n){
        for(int i=0;i<n;i++){
            int size = Double.valueOf(Math.pow(10,i)).intValue();
            int[] array = new int[size];
            buildRandomArray(array,size+1);
            sorter.sort(array);

        }
    }

    //创建n个数据项的无序数组，可能有重复项
    public static void buildSortArray(int[] array, int n){
        for(int i=0;i<array.length;i++){
            array[i] = i;
        }
        for(int i = 0; i<n; i++){
            int j = random.nextInt(array.length);
            array[j] = j;
        }
    }

    //创建n个数据项的有序数组，可能有重复项
    public static void buildSortArrayDesc(int[] array, int n){
        int length = array.length;
        for(int i=0;i<array.length;i++){
            array[i] = length;
            --length;
        }
        for(int i = 0; i<n; i++){
            int j = random.nextInt(array.length);
            array[j] = j;
        }
    }

    //复制一个新的数组
    public static int[] copyArray(int[] array){
        int[] arrayCopy = new int[array.length];
        for(int i=0;i<array.length;i++){
            arrayCopy[i] = array[i];
        }
        return arrayCopy;
    }

    //检查数组是否是有序的
    public static void checkArray(int[] array){
        for(int i=0;i<array.length-1;i++){
            if(array[i] > array[i+1]){
                throw new RuntimeException("array not order");
            }
        }
    }
}
