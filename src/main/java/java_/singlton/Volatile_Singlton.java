package java_.singlton;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-7
 */
public class Volatile_Singlton {

    private Volatile_Singlton(){

    }

    private static volatile Volatile_Singlton instance;

    public static Volatile_Singlton getInstance(){
        if(instance == null){
            synchronized (Final_Singlton.class) {
                if(instance == null){
                    return new Volatile_Singlton();
                }
            }
        }
        return instance;
    }
}
