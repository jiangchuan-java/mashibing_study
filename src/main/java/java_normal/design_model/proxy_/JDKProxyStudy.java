package java_normal.design_model.proxy_;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/22 19:59
 */
public class JDKProxyStudy {
    static {
        //保留代理类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }
    public static interface MoveAble {
        void move(String carName);
        void stop();
    }
    public static class MyCar implements MoveAble {
        @Override
        public void move(String carName) {
            System.out.println(carName + " my car move");
        }
        @Override
        public void stop() {
            System.out.println("my car stop");
        }
    }
    public static class ProxyHandler implements InvocationHandler {
        private MoveAble moveAble;
        public ProxyHandler(MoveAble moveAble) {
            this.moveAble = moveAble;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("proxy execute");
            return method.invoke(moveAble, args);
        }
    }
    public static void main(String[] args) {
        MoveAble mycar = new MyCar();
        MoveAble proxyMoveAble = (MoveAble) Proxy.newProxyInstance(
                        JDKProxyStudy.class.getClassLoader(),
                        new Class[]{MoveAble.class},
                        new ProxyHandler(mycar));
        proxyMoveAble.move("audi");
    }
}
