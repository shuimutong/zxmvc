package cn.ourpass.zxmvc.controller;

import java.lang.reflect.Method;

import cn.ourpass.zxmvc.annotation.XController;
import cn.ourpass.zxmvc.annotation.XRequestMapping;

@XController
@XRequestMapping("/test")
public class TestController {
    @XRequestMapping("/testA")
    public void testA() {
        System.out.println("Hi, this is TestA");
    }
    @XRequestMapping("/testB")
    public void testB() {
        System.out.println("Hi, this is testB");
    }
    @XRequestMapping("/testC")
    public void testC() {
        System.out.println("Hi, this is testC");
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
