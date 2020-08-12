package java_.SPI;

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
        System.out.println("java java_.SPI");
        for(MyInterface myInterface : serviceLoader){
            myInterface.say();
        }
    }
}
