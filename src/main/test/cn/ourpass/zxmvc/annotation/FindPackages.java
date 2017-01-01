package cn.ourpass.zxmvc.annotation;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import cn.ourpass.zxmvc.bean.EntityBean;

public class FindPackages {
    public static void main(String[] args) throws Exception {
        findClass();
    }
    
    public static void findClass() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(rootPath);
        Set<EntityBean> beans = findFileBeans(rootPath, rootPath);
        for(EntityBean e : beans) {
            System.out.println(e);
        }
    }
    
    public static Set<EntityBean> findFileBeans(String rootPath, String filePath) {
        Set<EntityBean> beans = new HashSet<EntityBean>();
        File rootFile = new File(filePath);
        if(rootFile.isDirectory()) {
            File[] files = rootFile.listFiles();
            for(File f : files) {
                Set<EntityBean> tbeans = findFileBeans(rootPath, f.getPath());
                beans.addAll(tbeans);
            }
        } else if(rootFile.isFile() && filePath.endsWith(".class")) {
            try {
                System.out.println("before:" + filePath);
                filePath = filePath.replace(rootPath, "");
                filePath = filePath.replace("/", ".").replace(".class", "");
                System.out.println("after:" + filePath);
                Class clazz = Class.forName(filePath);
                EntityBean bean = new EntityBean();
                bean.setName(clazz.getName());
                bean.setClazz(clazz);
                beans.add(bean);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        return beans;
    }
}
