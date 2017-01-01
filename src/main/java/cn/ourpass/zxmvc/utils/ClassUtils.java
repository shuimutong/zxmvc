package cn.ourpass.zxmvc.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import cn.ourpass.zxmvc.bean.EntityBean;

public class ClassUtils {
    private final static Logger log = Logger.getLogger(ClassUtils.class);
    
    /**
     * 遍历所有类
     * @param rootPath
     * @return
     */
    public static List<EntityBean> scanClasses(String rootPath) {
        List<EntityBean> beanList = new ArrayList<EntityBean>();
        List<String> fileNameList = FileUtils.scanAllFiles(rootPath);
        if(fileNameList != null && fileNameList.size() > 0) {
            for(String s : fileNameList) {
                if(s.endsWith(".class")) {
                    String className = filterClassName(rootPath, s);
                    try {
                        Class clazz = Class.forName(className);
                        EntityBean bean = new EntityBean(clazz.getName(), clazz);
                        beanList.add(bean);
                    } catch (ClassNotFoundException e) {
                        log.warn("scanClassesException", e);
                    }
                }
            }
        }
        return beanList;
    }
    
    /**
     * 过滤得到类名
     * @param rootPath
     * @param longName
     * @return
     */
    public static String filterClassName(String rootPath, String longName) {
        String className = "";
        if(StringUtils.isNotBlank(longName)) {
            if(StringUtils.isNotBlank(rootPath)) {
                className = longName.replace(rootPath, "");
            }
            className = className.replace("/", ".").replace(".class", "");
        }
        return className;
    }
}
