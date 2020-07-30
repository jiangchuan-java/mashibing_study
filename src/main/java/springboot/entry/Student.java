package springboot.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-20
 */
@Component()
//@Scope("prototype")
public class Student {

    @Autowired
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
