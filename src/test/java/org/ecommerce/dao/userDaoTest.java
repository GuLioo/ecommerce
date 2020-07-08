package org.ecommerce.dao;

import org.apache.ibatis.annotations.Param;
import org.ecommerce.entity.adminUser;
import org.ecommerce.entity.user;
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
public class userDaoTest {
    @Resource
    private userDao userDao;


    @Test
    public void deleteByPrimaryKey() {
        int count=userDao.deleteByPrimaryKey(5);
        System.out.println(count);
    }

    @Test
    public void updateByPrimaryKey() {
        int count=userDao.updateByPrimaryKey(5,"小李","15164592672@163.com",null);
        System.out.println(count);
    }

    @Test
    public void selectByPrimaryKey() {
        user u=userDao.selectByPrimaryKey(5);
        System.out.println(u);
    }

    @Test
    public void selectAll() {
        List<user> users = userDao.selectAll(0, 100);
        for (user u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testInsert() {
        int count=userDao.insert(5, "hello","iiii");
        System.out.println(count);
    }

    @Test
    public void updateDiscount() {
        int count=userDao.updateDiscount(5, 0.8);
        System.out.println(count);
    }
}