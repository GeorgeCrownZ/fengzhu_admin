package com.soft.web.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OprateLogAOP {
    public String model() default "";
    public String method() default "";
    public String remark() default "";
}
