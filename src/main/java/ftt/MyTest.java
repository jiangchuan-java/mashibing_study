package ftt;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MyAnnotion
public class MyTest {
    int x;
    int y;

    public MyTest(){

    }

    public MyTest(int x,int y){
        this.x = x;
        this.y = y;
    }

//    public static void main(String[] args) {
//        MyTest myTest = new MyTest(2,2);
//        MyTest myTest1 = new MyTest(7,7);
//        double len = Math.sqrt(Math.pow((myTest.x - myTest1.x),2) + Math.pow((myTest.y - myTest1.y),2));
//        System.out.println(len);
//
//
//    }

    public static void main(String[] args) throws NoSuchMethodException {
        Annotation[] test01s = MyTest.class.getAnnotations();
        System.out.println(Arrays.toString(test01s));

    }

}
