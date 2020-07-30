package springboot.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-29
 */
@Component()
@Scope("prototype")
public class Teacher {

    @Autowired
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
