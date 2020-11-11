package Spring.Springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/9 22:26
 */
@Controller
@ResponseBody
public class MyController {

    @RequestMapping("/hello")
    public String hello(String name, String password) {
        return "world";
    }
}
