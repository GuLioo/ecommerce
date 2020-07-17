package org.ecommerce.dao;

import org.ecommerce.entity.orders;
import org.ecommerce.entity.product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
/*        Integer userId=0;
        Double money=123.4;
        short state=0;
        Timestamp t = new Timestamp(System.currentTimeMillis());
        */
    }

    @Test
    public void selectAll() {
        List<orders> pros = orderDao.selectAll();
        for (orders p : pros) {
            System.out.println(p);
        }
    }

    @Test
    public void findOrderByUid() {
        Integer uid=2;
        List<orders> pros = orderDao.findOrderByUid(uid);
        for (orders p : pros) {
            System.out.println(p);
        }
    }

    @Test
    public void countProducyAll() {
        int countAll=orderDao.countProductAll();
        System.out.println(countAll);
    }

    @Test
    public void findOrderByOid_State_Time() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStringFrom = "2020-07-15";
        Date fromTime = sdf.parse(dateStringFrom);
        String dateStringTo = "2020-07-16";
        Date toTime = sdf.parse(dateStringTo);
        List<orders> pros = orderDao.findOrderByOid_State_Time(2,"20200715",(short)0,fromTime,toTime);
        for (orders p : pros) {
            System.out.println(p);
        }
    }
}