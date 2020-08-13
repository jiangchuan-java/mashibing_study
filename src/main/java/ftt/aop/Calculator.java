package ftt.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Calculator {

    public Integer add(Integer i,Integer j){
        return i+j;
    }

    public Integer sub(Integer i,Integer j){
        return i-j;
    }

    public Integer mul(Integer i,Integer j){
        return i*j;
    }

    public Integer div(Integer i,Integer j){
        return i/j;
    }
}
