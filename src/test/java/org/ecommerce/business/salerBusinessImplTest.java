package org.ecommerce.business;

import org.ecommerce.dto.pageResult;
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

public class salerBusinessImplTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private salerBusiness salerBusiness;

    @Test
    public void selectAllCate() {
        List<category> list=salerBusiness.selectAllCate();
        logger.info("list={}",list);
    }

    @Test
    public void deleteProByPrimaryKey() {
        int result=salerBusiness.deleteProByPrimaryKey(104);
        logger.info("result={}",result);
    }

    @Test
    public void updateByPrimaryKey() {
        int result=salerBusiness.updateByPrimaryKey(104,null,4,null,null,null);
        logger.info("result={}",result);
    }

}