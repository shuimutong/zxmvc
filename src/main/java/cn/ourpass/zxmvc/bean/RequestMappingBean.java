package cn.ourpass.zxmvc.bean;

import java.lang.reflect.Method;

/**
 * url映射bean
 * @author simple
 *
 */
public class RequestMappingBean {
    /**
     * url
     */
    private String url;
    /**
     * 对应方法
     */
    private Method method;
    /**
     * 对应类名称
     */
    private String clazzName;
    
    public RequestMappingBean(String url, Method method, String clazzName) {
        super();
        this.url = url;
        this.method = method;
        this.clazzName = clazzName;
    }
    public RequestMappingBean() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Method getMethod() {
        return method;
    }
    public void setMethod(Method method) {
        this.method = method;
    }
    public String getClazzName() {
        return clazzName;
    }
    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }
    
}
