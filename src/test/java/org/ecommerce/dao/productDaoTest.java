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
        String pname=new String("test");
        Integer marketPrice=new Integer(2);
        String image=new String("11");
        String pdesc=new String("112");
        Integer pnum=12;
        int insertCount=productDao.insert(pname,marketPrice,image,pdesc,pnum);
        System.out.println(insertCount);
    }

    @Test
    public void deleteByPrimaryKey() {
        Integer pid=70;
        int count=productDao.deleteByPrimaryKey(pid);
        System.out.println(count);
    }

    @Test
    public void updateByPrimaryKey() {
        Integer pid=1;
        String image="爱仕达陶瓷养生壶.jpg";
        double price=399;
        int count=productDao.updateByPrimaryKey(pid,null,price,image,null,null);
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
        List<product> pros = productDao.selectAll();
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

    @Test
    public void updateNumByPrimaryKey() {
        System.out.println(productDao.updateNumByPrimaryKey(11,20));
    }

    @Test
    public void selectBypname() {
        List<product> pros = productDao.selectBypname("小招喵周边");
        for (product p : pros) {
            System.out.println(p);
            System.out.println("价格="+p.getMarketPrice());
        }
    }

    @Test
    public void deleteBypname() {
        System.out.println(productDao.deleteBypname("test"));
    }

    @Test
    public void updateNameByPrimaryKey()
    {
        System.out.println(productDao.updateNameByPrimaryKey(15,"111"));
    }

    @Test
    public void reduceProduct() {
        System.out.println(productDao.reduceProduct(40));
    }
}