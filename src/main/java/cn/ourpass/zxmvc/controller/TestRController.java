package cn.ourpass.zxmvc.controller;

import cn.ourpass.zxmvc.annotation.XController;
import cn.ourpass.zxmvc.annotation.XRequestMapping;

@XController
@XRequestMapping("testR/testt")
public class TestRController {
    @XRequestMapping("testA")
    public void testA() {
        System.out.println("Hi, this is TestA");
    }
    @XRequestMapping("testB")
    public void testB() {
        System.out.println("Hi, this is testB");
    }
    @XRequestMapping("testC")
    public void testC() {
        System.out.println("Hi, this is testC");
    }
}
