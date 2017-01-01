package cn.ourpass.zxmvc.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.ourpass.zxmvc.bean.EntityBean;

/**
 * 文件工具类
 * @author simple
 *
 */
public class FileUtils {
    /**
     * 遍历所有文件
     * @param rootPath
     * @return
     */
    public static List<String> scanAllFiles(String rootPath) {
        List<String> resFiles = new ArrayList<String>();
        File rootFile = new File(rootPath);
        if(!rootFile.exists()) {
            return resFiles;
        }
        if(rootFile.isDirectory()) {
            File[] files = rootFile.listFiles();
            for(File f : files) {
                List<String> childFiles = scanAllFiles(f.getPath());
                resFiles.addAll(childFiles);
            }
        } else if(rootFile.isFile()) {
            resFiles.add(rootPath); 
        }
        return resFiles;
    }
    
}
