package algorithm.dong_tai_gui_hua;


/**
 * @Des: 上楼梯，有多少种上法（一次一阶，或者一次二阶）
 * @Author: jiangchuan
 * <p>
 * @Date: 2020-05-01
 */
public class Floor {

    public static int upFloor(int n){
        if(n == 1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        return upFloor(n-1) + upFloor(n-2);
    }


    private static int[] floorSteps;

    public static int upFloorByRember(int n){
        if(floorSteps == null){
            floorSteps = new int[n+1];
        }
        if(floorSteps[n] > 0){
            return floorSteps[n];
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        floorSteps[n] = upFloorByRember(n-1) + upFloorByRember(n-2);
        return floorSteps[n];
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(upFloor(40));
        long end = System.currentTimeMillis();
        System.out.println("upFloor cost(ms)"+(end-start));
        start = System.currentTimeMillis();
        System.out.println(upFloorByRember(40));
        end = System.currentTimeMillis();
        System.out.println("upFloorByRember cost(ms)"+(end-start));
    }
}
