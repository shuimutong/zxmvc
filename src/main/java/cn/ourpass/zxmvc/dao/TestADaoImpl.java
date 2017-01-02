package cn.ourpass.zxmvc.dao;

import cn.ourpass.zxmvc.annotation.XRepository;

//@XRepository
public class TestADaoImpl implements TestADao {
    @Override
    public void printA() {
        System.out.println("This is printA");
    }
    @Override
    public void printB(String s) {
        System.out.println("This is printB,and " + s);
    }
    @Override
    public void printC(int i) {
        System.out.println("This is printC, and " + i);
    }
}
