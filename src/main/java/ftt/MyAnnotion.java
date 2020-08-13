package ftt;

import java.lang.annotation.*;

/**
 * retention -> 标示注解保存的范围
 * 1: source -> 注解会被编译器丢弃
 * 2: class -> 注解会被保存到class file中，但不会被jvm运行时保留
 * 3: runtime -> 注解会被保存到class file中，且会被jvm在运行时保存，因此可以通过反射的方式读取它们。
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 * target -> 标示注解作用的范围，编译器会做检查，
 * 比如声明是method，则编译器会检查注解是否在在方法上使用，如不是，会提示报错
 */
@Target(ElementType.TYPE)
public @interface MyAnnotion {
}
