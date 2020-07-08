package org.ecommerce.dao;

import org.ecommerce.entity.adminUser;
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
public class productDaoTest {
    @Resource
    private productDao productDao;

    @Test
    public void insert() {
        String pname=new String("abb");
        Integer marketPrice=new Integer(12);
        String image=new String("112");
        String pdesc=new String("112");
        Integer pnum=12;
        int insertCount=productDao.insert(pname,marketPrice,image,pdesc,pnum);
        System.out.println(insertCount);
    }

    @Test
    public void deleteByPrimaryKey() {
        Integer pid=10;
        int count=productDao.deleteByPrimaryKey(pid);
        System.out.println(count);
    }

    @Test
    public void updateByPrimaryKey() {
        Integer pid=10;
        String pname=new String("ab");
        Integer marketPrice=new Integer(100000);
        String image=new String("2");
        String pdesc=new String("2");
        Integer pnum=2;
        int count=productDao.updateByPrimaryKey(pid,pname,marketPrice,image,pdesc,pnum);
        System.out.println(count);
    }

    @Test
    public void selectByPrimaryKey() {
        Integer pid=1;
        product proDao=productDao.selectByPrimaryKey(pid);
        System.out.println(proDao);
        System.out.println(proDao.getImage());
    }

    @Test
    public void selectAll() {
        List<product> pros = productDao.selectAll(0, 100);
        for (product p : pros) {
            System.out.println(p);
        }
    }

    @Test
    public void countProducyAll() {
        int countAll=productDao.countProducyAll();
        System.out.println(countAll);
    }


    @Test
    public void countProducyByname() {
        String pname="厨具锅具";
        int count=productDao.countProducyByname(pname);
        System.out.println(count);
    }
}