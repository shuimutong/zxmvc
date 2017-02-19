package cn.ourpass.zxmvc.bean;

import java.lang.annotation.Annotation;

import com.alibaba.fastjson.JSONObject;

/**
 * 类对象
 * @author simple
 *
 */
public class EntityBean {
    /**
     * 类名
     */
    private String name;
    /**
     * class
     */
    private Class<? extends Annotation> clazz;
    /**
     * 实例对象
     */
    private Object o;
    
    public EntityBean(String name, Class<? extends Annotation> clazz) {
        super();
        this.name = name;
        this.clazz = clazz;
    }
    public EntityBean() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Class<?> getClazz() {
        return clazz;
    }
    public void setClazz(Class<? extends Annotation> clazz) {
        this.clazz = clazz;
    }
    
    public Object getO() {
        return o;
    }
    public void setO(Object o) {
        this.o = o;
    }
    @Override
    public String toString() {
        return "EntityBean:" + JSONObject.toJSONString(this);
    }
    
}
