package java_.proxy;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-5-19
 */
public class HelloWorld implements Say{

    public void say(){
        System.out.println("hello world !");
    }

    public void eat() {
        System.out.println("eat meat !");
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        System.out.println(HelloWorld.class.getName());
        helloWorld.say();
        helloWorld.eat();
    }
}
