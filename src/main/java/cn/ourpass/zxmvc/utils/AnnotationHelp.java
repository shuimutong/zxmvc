package cn.ourpass.zxmvc.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 注解帮助类
 * @author gaozx
 *
 */
public class AnnotationHelp {
	/**
	 * 判断方法是否被clazz注解
	 * @param method
	 * @param clazz
	 * @return
	 */
	public static boolean isHasTheAnnotation(Method method, Class<? extends Annotation> clazz) {
		return method.isAnnotationPresent(clazz);
	}
	/**
	 * 判断类是否被clazz注解
	 * @param sourceClass
	 * @param clazz
	 * @return
	 */
	public static boolean isHasTheAnnotation(Class<? extends Annotation> sourceClass, Class<? extends Annotation> clazz) {
		return sourceClass.isAnnotationPresent(clazz);
	}
	/**
	 * 判断属性是否被clazz注解
	 * @param field
	 * @param clazz
	 * @return
	 */
	public static boolean isHasTheAnnotation(Field field, Class<? extends Annotation> clazz) {
	    return field.isAnnotationPresent(clazz);
	}
}
