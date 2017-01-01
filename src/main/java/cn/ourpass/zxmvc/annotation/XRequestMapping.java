package cn.ourpass.zxmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller注解
 * @author simple
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface XRequestMapping {
    /**
     * 关联路径
     * @return
     */
    String[] value() default {};
    
    /**
     * 请求类型
     * @return
     */
    RequestMethod[] method() default {};
}
