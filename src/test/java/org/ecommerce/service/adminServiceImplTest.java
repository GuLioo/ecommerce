package org.ecommerce.service;

import org.ecommerce.dto.ecommerceResult;
import org.ecommerce.entity.adminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;


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
        int result=adminService.deleteByPrimaryKey(82);
        logger.info("result={}",result);
    }

    @Test
    public void setRole() {
        int result=adminService.setRole(10,(short)2);
        logger.info("result={}",result);
    }


    @Test
    public void selectByName() {
        adminUser result=adminService.selectByName("a");
        logger.info("result={}",result);
    }




}