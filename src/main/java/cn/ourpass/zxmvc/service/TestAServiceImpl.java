package cn.ourpass.zxmvc.service;

import cn.ourpass.zxmvc.annotation.XAutowired;
import cn.ourpass.zxmvc.annotation.XService;
import cn.ourpass.zxmvc.dao.TestADao;

//@XService
public class TestAServiceImpl implements TestAService {
    @XAutowired
    private TestADao testADao;
    
    @Override
    public void printA() {
        testADao.printA();
    }
    @Override
    public void printB(String s) {
        testADao.printB(s);
    }
    @Override
    public void printC(int i) {
        testADao.printC(i);
    }
}
