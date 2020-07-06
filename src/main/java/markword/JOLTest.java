package markword;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-6
 */
public class JOLTest {

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(ClassLayout.parseInstance(test).toPrintable());

        System.out.println("size : " + GraphLayout.parseInstance(test).totalSize());
    }
}
