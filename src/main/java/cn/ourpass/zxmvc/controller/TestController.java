package cn.ourpass.zxmvc.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ourpass.zxmvc.annotation.XAutowired;
import cn.ourpass.zxmvc.annotation.XRequestMapping;
import cn.ourpass.zxmvc.service.TestAService;
import cn.ourpass.zxmvc.utils.RequestUtil;

//@XController
//@XRequestMapping("/test")
public class TestController {
    @XAutowired
    private TestAService testAService;
    
    @XRequestMapping("/testA")
    public void testA(HttpServletRequest request, HttpServletResponse reponse) {
        
        System.out.println("Hi, this is TestA");
        testAService.printA();
    }
    @XRequestMapping("/testB")
    public void testB(HttpServletRequest request, HttpServletResponse reponse) {
        System.out.println("Hi, this is testB");
        String s = RequestUtil.getString(request, "s", "");
        testAService.printB(s);
    }
    @XRequestMapping("/testC")
    public void testC(HttpServletRequest request, HttpServletResponse reponse) {
        System.out.println("Hi, this is testC");
        int i= RequestUtil.getInt(request, "i", 0);
        testAService.printC(i);
    }
    
    public static void main(String[] args) throws Exception {
        Method testM = TestController.class.getMethod("testC", null);
        long t1 = System.currentTimeMillis();
        Object o = TestController.class.newInstance();
        for(int i=0; i<10000; i++) {
            testM.invoke(o, null);
            Thread.sleep(10);
        }
        long t2 = System.currentTimeMillis();
        System.out.println("useTime:" + (t2 - t1));
    }
}
