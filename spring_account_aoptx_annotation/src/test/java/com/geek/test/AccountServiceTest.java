package com.geek.test;

import com.geek.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    private IAccountService accountService;

    @Test
    public void testTransfer() {
        accountService.transfer("aaa", "bbb", 100f);
    }
    // java.sql.SQLException: Can't call commit when autocommit=true

    // 先 @After，后 @AfterReturning。

    //     @Before("pt1()")～１
    //     @AfterReturning("pt1()")～３
    //     @AfterThrowing("pt1()")～３
    //     @After("pt1()")～３
}
