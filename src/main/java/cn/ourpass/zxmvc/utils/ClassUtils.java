package cn.ourpass.zxmvc.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import cn.ourpass.zxdata.helpkits.AnnotationHelp;
import cn.ourpass.zxmvc.annotation.XAutowired;
import cn.ourpass.zxmvc.bean.EntityBean;

/**
 * 
 * <p>Title: ClassUtils</p>
 * <p>Description: 类工具类</p>
 * <p>Company: @DaoJia[for good things better]</p>
 * <p>Author: gaozx</p>
 * <p>Date: 2017年1月2日</p>
 */
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
    
    /**
     * 初始化Bean
     * @param entityBeanMap
     * @param destEntityBean
     */
    public static EntityBean initBeans(Map<String, EntityBean> entityBeanMap, EntityBean destEntityBean) {
        if(entityBeanMap == null || entityBeanMap.size() < 1 || destEntityBean == null) {
            return null;
        } 
        if(destEntityBean.getO() == null) {
            Class clazz = destEntityBean.getClazz();
            try {
                destEntityBean.setO(clazz.newInstance());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            Field[] fields = clazz.getDeclaredFields();
            for(Field f : fields) {
                //依赖注入
                if(AnnotationHelp.isHasTheAnnotation(f, XAutowired.class)) {
                    f.setAccessible(true);
                    try {
                        Object fVal = f.get(destEntityBean.getO());
                        //依赖值为空，需要注入
                        if(fVal == null) {
                            Class fieldClazz = f.getType();
                            //获取依赖的对象
                            EntityBean relayEntityBean = entityBeanMap.get(fieldClazz.getName());
                            //通过类名获取不到或者是接口，查询它的子类
                            if(relayEntityBean == null || relayEntityBean.getClazz().isInterface() 
                                    || Modifier.isAbstract(relayEntityBean.getClazz().getModifiers())) {
                                for(String s : entityBeanMap.keySet()) {
                                    EntityBean likeEntityBean = entityBeanMap.get(s);
                                    if(fieldClazz.isAssignableFrom(likeEntityBean.getClazz())) {
                                        relayEntityBean = likeEntityBean;
                                        break;
                                    }
                                }
                            }
                            if(relayEntityBean != null) {
                                initBeans(entityBeanMap, relayEntityBean);
                                //给依赖的属性注入值
                                if(relayEntityBean.getO() != null) {
                                    f.set(destEntityBean.getO(), relayEntityBean.getO());
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return destEntityBean;
    }
}
