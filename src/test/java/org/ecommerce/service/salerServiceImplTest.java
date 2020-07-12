package org.ecommerce.service;

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

public class salerServiceImplTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private salerService salerService;

    @Test
    public void insert() {
        int result=salerService.insertCate("test");
        logger.info("result={}",result);
    }

    @Test
    public void deleteBycname() {
        int result=salerService.deleteCateBycname("change");
        logger.info("result={}",result);
    }

    @Test
    public void updateByPrimaryKey() {
        int result=salerService.updateCateByPrimaryKey(9,"change");
        logger.info("result={}",result);
    }

    @Test
    public void selectAll() {
        List<category> result=salerService.selectAllCate();
        logger.info("result={}",result);
    }

    @Test
    public void insertPro() {
        int result=salerService.insertPro("testNum",12,"1","2",12);
        logger.info("result={}",result);
    }

    @Test
    public void deleteProByPrimaryKey() {
        int result=salerService.deleteProByPrimaryKey(18);
        logger.info("result={}",result);
    }

    @Test
    public void updateProNumByPrimaryKey() {
        int result=salerService.updateProNumByPrimaryKey(18,-1);
        logger.info("result={}",result);
    }

    @Test
    public void selectProBypname() {
        List<product> pros = salerService.selectProBypname("ann");
        for (product p : pros) {
            logger.info("result={}",p);
        }
    }

    @Test
    public void testUpdateByPrimaryKey() {
        int result=salerService.updateByPrimaryKey(19,null,4,null,null,null);
        logger.info("result={}",result);
    }
}