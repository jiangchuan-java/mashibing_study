package jvm.classloader;

/**
 * Created by fengtingting on 2020/7/11.
 */
public class Clasloader_01 {

    public static void main(String[] args) throws ClassNotFoundException{
        ClassLoader classLoader = Clasloader_01.class.getClassLoader();
        System.out.println(classLoader);

        String bootClassPath = System.getProperty("sun.boot.class.path");
        bootClassPath = bootClassPath.replaceAll(";", System.lineSeparator());
        System.out.println("root.dir= "+bootClassPath);
        System.out.println("-----------------------------");

        String var0 = System.getProperty("java.ext.dirs");
        var0 = var0.replaceAll(";", System.lineSeparator());
        System.out.println("ext.dir= "+var0);
        System.out.println("-----------------------------");

        String var1 = System.getProperty("java.class.path");
        var1 = var1.replaceAll(";", System.lineSeparator());
        System.out.println("app.dir= "+var1);
        System.out.println("-----------------------------");


        Clasloader_01.class.getClassLoader().loadClass("xxx");




    }
}
