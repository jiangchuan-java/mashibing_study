package springboot.beida;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-20
 */
public class Student {

    private String name = "jiangchuan";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
