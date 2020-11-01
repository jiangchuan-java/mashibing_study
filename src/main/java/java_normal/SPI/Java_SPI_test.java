package java_normal.SPI;

import java.util.ServiceLoader;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-12
 */
public class Java_SPI_test {

    public static void main(String[] args) {
        ServiceLoader<MyInterface> serviceLoader = ServiceLoader.load(MyInterface.class);
        System.out.println("java java_normal.SPI");
        for(MyInterface myInterface : serviceLoader){
            myInterface.say();
        }
    }
}
