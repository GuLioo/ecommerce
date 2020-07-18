package org.ecommerce.business;

import org.ecommerce.dto.userExecution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",  "classpath:spring/spring-service.xml"})

public class adminBusinessImplTest {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private adminBusiness adminBusiness;

    @Test
    public void getAdminUserList() {

    }


    @Test
    public void insertAdminUser() {

    }

    @Test
    public void deleteByPrimaryKey() {
        int result=adminBusiness.deleteByPrimaryKey(4);
        logger.info("result={}",result);
    }

    @Test
    public void setRole() {

    }

    @Test
    public void executeLogin() {

    }
}