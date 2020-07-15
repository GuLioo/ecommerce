package org.ecommerce.service;

import org.ecommerce.dto.userLoginExecution;
import org.ecommerce.entity.adminUser;
import org.ecommerce.entity.category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",  "classpath:spring/spring-service.xml"})
public class adminServiceImplTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private adminService adminService;

    @Test
    public void getAdminUserList() {
        List<adminUser> result=adminService.getAdminUserList();
        logger.info("result={}",result);
    }

    @Test
    public void getAdminUserListByUid() {
        List<adminUser> result=adminService.getAdminUserListByUid((short)0);
        logger.info("result={}",result);
    }

    @Test
    public void insertAdminUser() {
        int result=adminService.insertAdminUser("c","11",(short)2);
        logger.info("result={}",result);
    }

    @Test
    public void deleteByPrimaryKey() {
        int result=adminService.deleteByPrimaryKey(4);
        logger.info("result={}",result);
    }

    @Test
    public void setRole() {
        int result=adminService.setRole(10,(short)2);
        logger.info("result={}",result);
    }

    @Test
    public void countUserAll() {
        int result=adminService.countUserAll();
        logger.info("result={}",result);
    }

    @Test
    public void selectByName() {
        adminUser result=adminService.selectByName("a");
        logger.info("result={}",result);
    }

    @Test
    public void executeLogin() {
        userLoginExecution result=adminService.executeLogin("c","11");
        logger.info("result={}",result);
    }

    @Test
    public void updateDiscount() {
    }



}