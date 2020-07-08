package org.ecommerce.dao;

import org.ecommerce.entity.orders;
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
public class ordersDaoTest {
    @Resource
    private ordersDao orderDao;

    @Test
    public void insert() {
        int userId=new Integer(2);
        double money=20;
        int insertCount=orderDao.insert(userId,money, (short) 0);
        System.out.println(insertCount);
    }

    @Test
    public void selectAll() {
        List<orders> pros = orderDao.selectAll(0, 100);
        for (orders p : pros) {
            System.out.println(p);
        }
    }

    @Test
    public void findOrderByUid() {
        Integer uid=12;
        List<orders> pros = orderDao.findOrderByUid(uid);
        for (orders p : pros) {
            System.out.println(p);
        }
    }

    @Test
    public void countProducyAll() {
        int countAll=orderDao.countProducyAll();
        System.out.println(countAll);
    }
}