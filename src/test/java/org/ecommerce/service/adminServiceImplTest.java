package org.ecommerce.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",  "classpath:spring/spring-service.xml"})
public class adminServiceImplTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private adminService adminService;


    @Test
    public void deleteByPrimaryKey() {
        int result=adminService.deleteByPrimaryKey(7);
        logger.info("result={}",result);
    }

    @Test
    public void testInsertAdminUser() {
        int result=adminService.insertAdminUser("test","111",(short) 1);
    }
}