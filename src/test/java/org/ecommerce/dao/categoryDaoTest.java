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
        List<category> pros = categoryDao.selectAll();
        for (category p : pros) {
            System.out.println(p);
        }
    }

    @Test
    public void insert() {
        int count=categoryDao.insert("no");
        System.out.println(count);
    }


    @Test
    public void updateByPrimaryKey()
    {
        System.out.println(categoryDao.updateByPrimaryKey(9,"test"));
    }

    @Test
    public void selectBycname() {
        String name="aaa";
        System.out.println(categoryDao.selectBycname(name));
    }

    @Test
    public void deleteBycname() {
        System.out.println(categoryDao.deleteBycname("test"));
    }

    @Test
    public void selectBycid() {
        System.out.println(categoryDao.selectBycid(9));
    }
}