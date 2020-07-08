package org.ecommerce.dao;

import org.ecommerce.entity.category;
import org.ecommerce.entity.product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring的配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class categoryDaoTest {

    @Resource
    private categoryDao categoryDao;

    @Test
    public void selectAll() {
        List<category> pros = categoryDao.selectAll(0, 100);
        for (category p : pros) {
            System.out.println(p);
        }
    }
}