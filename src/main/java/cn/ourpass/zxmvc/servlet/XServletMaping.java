package cn.ourpass.zxmvc.servlet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import cn.ourpass.zxdata.helpkits.AnnotationHelp;
import cn.ourpass.zxmvc.annotation.XController;
import cn.ourpass.zxmvc.annotation.XRequestMapping;
import cn.ourpass.zxmvc.bean.EntityBean;
import cn.ourpass.zxmvc.bean.RequestMappingBean;
import cn.ourpass.zxmvc.utils.ClassUtils;
import cn.ourpass.zxmvc.utils.RequestUtil;

import com.alibaba.fastjson.JSONObject;

/**
 * 对象初始管理
 * @author simple
 *
 */
public class XServletMaping {
    private final static Logger log = Logger.getLogger(XServletMaping.class);
    /**管理的bean集合**/
    private static Map<String, EntityBean> BEAN_MAP = new HashMap<String, EntityBean>();
    /**路径映射map**/
    private static Map<String, RequestMappingBean> URL_MAP = new HashMap<String, RequestMappingBean>();
    
    public static Map<String, RequestMappingBean> getUrlMap() {
        return URL_MAP;
    }
    public static Map<String, EntityBean> getBeanMap() {
        return BEAN_MAP;
    }
    
    public static void printRes() {
        if(BEAN_MAP != null && BEAN_MAP.size() > 0) {
            Set<String> keys = BEAN_MAP.keySet();
            System.out.println("BEAN_MAP");
            for(String s : keys) {
                System.out.println(s + ": " + JSONObject.toJSONString(BEAN_MAP.get(s)));
            }
        }
        if(URL_MAP != null && URL_MAP.size() > 0) {
            Set<String> keys = URL_MAP.keySet();
            System.out.println("URL_MAP");
            for(String s : keys) {
                System.out.println(s + ": " + JSONObject.toJSONString(URL_MAP.get(s)));
            }
        }
    }
    
    /**
     * 扫描所有bean
     */
    public static void scanPaths() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        List<EntityBean> beanList = ClassUtils.scanClasses(rootPath);
        if (beanList != null && beanList.size() > 0) {
            for (EntityBean eb : beanList) {
                Class clazz = eb.getClazz();
                //保存由框架管理的bean
                if(AnnotationHelp.isHasTheAnnotation(clazz, XController.class)) {
                    BEAN_MAP.put(clazz.getName(), eb);
                }
                //保存url映射关系
                if (AnnotationHelp.isHasTheAnnotation(clazz, XController.class)) {
                    List<String> parentPath = new ArrayList<String>();
                    if (AnnotationHelp.isHasTheAnnotation(clazz, XRequestMapping.class)) {
                        XRequestMapping xrm = (XRequestMapping) clazz.getAnnotation(XRequestMapping.class);
                        parentPath = Arrays.asList(xrm.value());
                    }
                    Method[] methods = clazz.getDeclaredMethods();
                    if (methods != null && methods.length > 0) {
                        for (Method m : methods) {
                            if (AnnotationHelp.isHasTheAnnotation(m, XRequestMapping.class)) {
                                XRequestMapping xrm = (XRequestMapping) m.getAnnotation(XRequestMapping.class);
                                String[] paths = xrm.value();
                                if (paths != null && paths.length > 0) {
                                    for (String cp : paths) {
                                        if(parentPath != null && parentPath.size() > 0) {
                                            for(String pp : parentPath) {
                                                String fullPath = pp + "/" + cp;
                                                fullPath = fullPath.replace("//", "/");
                                                if (fullPath.startsWith("/")) {
                                                    fullPath = fullPath.substring(1);
                                                }
                                                RequestMappingBean rmb = new RequestMappingBean(fullPath, m, clazz.getName());
                                                URL_MAP.put(fullPath, rmb);
                                            }
                                        }  else {
                                            String fullPath = cp;
                                            fullPath = fullPath.replace("//", "/");
                                            if (fullPath.startsWith("/")) {
                                                fullPath = fullPath.substring(1);
                                            }
                                            RequestMappingBean rmb = new RequestMappingBean(fullPath, m, clazz.getName());
                                            URL_MAP.put(fullPath, rmb);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
            }
        }
    }
    
    public static void returnJson(HttpServletRequest request,
            HttpServletResponse response, JSONObject result) {
        try {
            String callback = RequestUtil.getString(request, "callback", "");
            String encode = RequestUtil.getString(request, "encode", "utf-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setCharacterEncoding(encode);
            response.setContentType("application/json;charset=" + encode);
            StringBuilder sb = new StringBuilder();
            if (StringUtils.isEmpty(callback)) {
                sb.append(result.toString());
            } else {
                sb.append(filterHtml(callback)).append("(").append(result)
                        .append(");").toString();
            }

            byte[] data = sb.toString().getBytes(encode);
            response.setContentLength(data.length);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.getOutputStream().write(data, 0, data.length);
        } catch (Exception e) {
            log.error("print result error!", e);
        }
    }
    
    private static String filterHtml(String body) {
        if (body == null) {
            return "";
        }
        body = body.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;").replaceAll("'", "&quot;");
        return body;
    }
}
