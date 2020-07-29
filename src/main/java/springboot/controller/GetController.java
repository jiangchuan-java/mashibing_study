package springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GetController {


    @RequestMapping(path = "/helloWorld", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld(HttpServletRequest request, HttpServletResponse response) throws Exception{
        return "I'm jiagnchuan";
    }

}
