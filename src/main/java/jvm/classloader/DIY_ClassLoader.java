package jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * 自定义实现classLoader类
 *
 * Created by fengtingting on 2020/7/12.
 */
public class DIY_ClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String allPathClassName) throws ClassNotFoundException {
        String filePath = allPathClassName.replace(".","/").concat(".class");
        try {
            FileInputStream fins = new FileInputStream("d:/jiangchuan/"+filePath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b=fins.read())!=-1){
                baos.write(b);
            }
            byte[] bytes = baos.toByteArray();
            baos.close();
            fins.close();
            return defineClass("jvm.classloader.Student", bytes, 0, bytes.length);
        }catch (Exception e){

        }
        throw new ClassNotFoundException();
    }

    public static void main(String[] args) throws Exception{
        ClassLoader diyClassLoader = new DIY_ClassLoader();
        Class c = diyClassLoader.loadClass("mashibing_study.src.main.java.jvm.jvm.classloader.Student");
        Method[] methods = c.getDeclaredMethods();
        for(Method m : methods){
            if(m.getName() == "say"){
                m.invoke(c.newInstance(),null);
            }
        }

        System.out.println(c.getClassLoader());
        System.out.println(c.getClassLoader().getParent());
        System.out.println(c.getClassLoader().getParent().getParent());
        System.out.println(c.getClassLoader().getParent().getParent().getParent());
    }
}
