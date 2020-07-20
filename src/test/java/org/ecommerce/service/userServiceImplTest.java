package org.ecommerce.service;

import org.ecommerce.dao.ordersDao;
import org.ecommerce.entity.adminUser;
import org.ecommerce.entity.category;
import org.ecommerce.entity.orders;
import org.ecommerce.entity.product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",  "classpath:spring/spring-service.xml"})
public class userServiceImplTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private userService userService;


    @Test
    public void userInfo() {
        adminUser adminUser=userService.userInfo(1);
        logger.info("adminUser={}",adminUser);
    }

    @Test
    public void selectAllCate() {
        List<category> list=userService.selectAllCate();
        logger.info("list={}",list);
    }

    @Test
    public void selectProBypname() {
        List<product> list=userService.selectProBypname("箱包手袋");
        logger.info("list={}",list);
    }

    @Test
    public void selectByPrimaryKey() {
        product product=userService.selectByPrimaryKey(1);
        logger.info("product={}",product);
    }

    @Test
    public void findOrderByUid() {
        List<orders> list=userService.findOrderByUid(1);
        logger.info("list={}",list);
    }

    @Test
    public void findOrderByOid_State_Time() {
        List< orders > list=userService.findOrderByOid_State_Time(2,"2020",(short)0,null,null);
        logger.info("list={}",list);
    }

    @Test
    public void reduceProduct() {
        int result=userService.reduceProduct(1);
        logger.info("result={}",result);
    }

    @Test
    public void lockProduct() {
        product product=userService.lockProduct(1);
        logger.info("product={}",product);
    }

}