package java_normal.singlton;

import org.junit.Test;

/**
 * 静态内部类
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/14 13:59
 */
public class InnerStatic_Singlton {

    private InnerStatic_Singlton(){
    }

    private static class InstanceHoldr {
        private static InnerStatic_Singlton instance = new InnerStatic_Singlton();
    }

    public static InnerStatic_Singlton getInstance() {
        return InstanceHoldr.instance;
    }
    /**
     * ObjectInputStream 在最终返回前调用
     * @return
     */
    public Object readResolve(){
        return getInstance();
    }
}

