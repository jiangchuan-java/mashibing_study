package algorithm.Ologn;

import java.util.Objects;
import java.util.Random;

/**
 * 求一百万数据中top10 大的数据
 */
public class Dui {

    private Integer array[];

    private int count = 1;

    private int root = 0;

    public Dui(int capacity){
        this.array = new Integer[capacity+1];
    }

    public void deal(int i, int n){
        while (true){
            int small = i;
            if(i*2<=n && Objects.nonNull(array[i*2]) && array[i*2]<array[i]){
                small = i*2;
            }
            if(i*2+1<=n && Objects.nonNull(array[i*2+1]) && array[i*2+1]<array[small]){
                small = i*2+1;
            }
            if(small == i){
                break;
            }
            int temp = array[i];
            array[i] = array[small];
            array[small] = temp;
            i = small;
        }
    }

    public void dui(){
        for(int i=count/2;i>=1;i--){
            deal(i,array.length-1);
        }
    }

    public void removeTop(){
        while (array[1] !=null){
            System.out.println(array[1]);
            array[1] = array[count-1];
            array[count] = null;
            count--;
            dui();
        }
    }

    public void add(int data){
        if(data < root){
            System.out.println("元素小于堆顶元素，过滤");
        } else if (count<array.length-1){
            array[count] = data;
            count++;
            dui();
        } else {
            //当堆已经满了，且大于堆顶元素，则先删除堆顶元素，然后将该元素放入堆顶，并重新堆化
            array[1] = data;
            dui();
        }
        root = array[1];
    }


    public static void main(String[] args) {
        Dui dui = new Dui(10);
        Random random = new Random();
        for(int i=1;i<10000;i++){
            dui.add(random.nextInt(10000));
        }
        dui.removeTop();
    }

}
