package com.gyh.user.service.impl;

import com.gyh.GyhApp;
import com.gyh.user.service.TransactionTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author guoyanhong
 * @date 2018/9/11 16:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GyhApp.class)
public class TransactionTestServiceImplTest {
    @Autowired
    private TransactionTestService transactionTestService;

    @Test
    public void rollbackForException1() {
        int type = 1;
        transactionTestService.rollbackForException(type);
    }

    @Test
    public void rollbackForException2() {
        int type = 2;
        transactionTestService.rollbackForException(type);
    }

    @Test
    public void rollbackForException3() {
        int type = 3;
        transactionTestService.rollbackForException(type);
    }

    @Test
    public void rollbackForException4() {
        int type = 4;
        transactionTestService.rollbackForException(type);
    }

    @Test
    public void rollbackForException14() {
        int type = 14;
        transactionTestService.rollbackForException(type);
    }

    @Test
    public void rollbackForException5() {
        int type = 5;
        transactionTestService.rollbackForException(type);
    }

    @Test
    public void rollbackForException6() {
        int type = 6;
        transactionTestService.rollbackForException(type);
    }
}