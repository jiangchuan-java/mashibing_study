package java_.singlton;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-7
 */
public class Final_Singlton {

    private final int flag;

    private Final_Singlton(){
        flag = 1;
        /**
         * final 关键字能保证，在对象的引用被其他人看到时，
         * final属性一定被初始化，进而防止了创建对象的重排序
         */
    }
    private static Final_Singlton instance;

    public static Final_Singlton getInstance(){
        if(instance == null){
            synchronized (Final_Singlton.class) {
                if(instance == null){
                    return new Final_Singlton();
                }
            }
        }
        return instance;
    }
}
