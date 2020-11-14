package java_normal.singlton;

import org.junit.Test;

import java.io.Serializable;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-7
 */
public class Volatile_Singlton implements Serializable {

    private Volatile_Singlton(){

    }

    private static volatile Volatile_Singlton instance;

    public static Volatile_Singlton getInstance(){
        if(instance == null){
            synchronized (Volatile_Singlton.class) {
                if(instance == null){
                    instance = new Volatile_Singlton();
                }
            }
        }
        return instance;
    }

    /**
     * ObjectInputStream 在最终返回前调用
     * @return
     */
    public Object readResolve(){
        return getInstance();
    }

}
