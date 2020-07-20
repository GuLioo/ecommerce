package org.ecommerce.business;

import org.ecommerce.dto.ecommerceResult;
import org.ecommerce.dto.pageResult;
import org.ecommerce.dto.userExecution;
import org.ecommerce.dto.userStateEnum;
import org.ecommerce.entity.adminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",  "classpath:spring/spring-service.xml"})

public class adminBusinessImplTest {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private adminBusiness adminBusiness;



    @Test
    public void insertAdminUser() {
        ecommerceResult result=adminBusiness.insertAdminUser("c","11",(short)2);
        logger.info("result={}",result.getData());
    }

    @Test
    public void deleteByPrimaryKey() {
        int result=adminBusiness.deleteByPrimaryKey(81);
        logger.info("result={}",result);
    }

    @Test
    public void setRole() {
        ecommerceResult result=adminBusiness.setRole(82,(short)0);
        logger.info("result={}",result.getData());
    }

    @Test
    public void executeLogin() {
/*        ecommerceResult result=adminBusiness.executeLogin("add","1111");
        logger.info("result={}",result.getData());*/
    }

    @Test
    public void adminUserGet() {
        pageResult pageResult=adminBusiness.adminUserGet((short)(0),1);
        logger.info("pageResult={}",pageResult.getExtend());
    }


}