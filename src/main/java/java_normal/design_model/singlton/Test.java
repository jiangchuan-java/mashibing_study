package java_normal.design_model.singlton;

import java.io.*;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/14 16:14
 */
public class Test {


    @org.junit.Test
    public void objInputStream() throws Exception{
        Volatile_Singlton volatile_singlton = Volatile_Singlton.getInstance();
        System.out.println("volatile_singlton hashCode() : " + volatile_singlton.hashCode());


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(out);
        oout.writeObject(volatile_singlton);

        InputStream inputStream = new ByteArrayInputStream(out.toByteArray());
        ObjectInputStream oin = new ObjectInputStream(inputStream);
        Volatile_Singlton newObject = (Volatile_Singlton) oin.readObject();

        System.out.println("newObject hashCode() : " + newObject.hashCode());
    }
}
