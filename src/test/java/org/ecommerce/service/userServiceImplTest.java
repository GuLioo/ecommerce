package org.ecommerce.service;

import org.ecommerce.dao.ordersDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.sql.Timestamp;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",  "classpath:spring/spring-service.xml"})
public class userServiceImplTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private userService userService;


    @Test
    public void executeSeckill() {

    }
}