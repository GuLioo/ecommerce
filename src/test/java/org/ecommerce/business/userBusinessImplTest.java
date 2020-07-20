package org.ecommerce.business;

import org.ecommerce.entity.category;
import org.ecommerce.entity.product;
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

public class userBusinessImplTest {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private userBusiness userBusiness;

    @Test
    public void selectAllCate() {
        List<category> list=userBusiness.selectAllCate();
        logger.info("list={}",list);
    }

    @Test
    public void selectByPrimaryKey() {
        product product=userBusiness.selectByPrimaryKey(1);
        logger.info("product={}",product);
    }

}