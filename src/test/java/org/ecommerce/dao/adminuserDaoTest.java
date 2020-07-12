package org.ecommerce.dao;

import org.ecommerce.entity.adminUser;
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
public class adminuserDaoTest {
    @Resource
    private adminuserDao adminuserDao;

    @Test
    public void deleteByPrimaryKey() {
        int deleteCount=adminuserDao.deleteByPrimaryKey(6);
        System.out.println(deleteCount);
    }

    @Test
    public void insertAdminUser() {
        String username=new String("abb");
        String password=new String("112");
        short uid=0;
        int insertCount=adminuserDao.insertAdminUser(username,password,uid);
        System.out.println(insertCount);
    }

    @Test
    public void queryAll() {
        List<adminUser> adminUsers = adminuserDao.queryAll();
        for (adminUser adminUser : adminUsers) {
            System.out.println(adminUser);
        }
    }

    @Test
    public void setRole() {
        Integer auid=12;
        short uid=1;
        int insertCount=adminuserDao.setRole(auid,uid);
        System.out.println(insertCount);
    }

    @Test
    public void countUserAll() {
        int count=adminuserDao.countUserAll();
        System.out.println(count);
    }

    @Test
    public void selectByName() {
        String id="ann";
        adminUser adminuser=adminuserDao.selectByName(id);
        System.out.println(adminuser);
    }

    @Test
    public void getAdminUserListByUid() {
        short uid=2;
        List<adminUser> adminUsers = adminuserDao.getAdminUserListByUid(uid);
        for (adminUser adminUser : adminUsers) {
            System.out.println(adminUser);
        }
    }

    @Test
    public void selectByAuid() {
        Integer auid=7;
        adminUser adminuser=adminuserDao.selectByAuid(auid);
        System.out.println(adminuser);
    }

    @Test
    public void judgeSale() {
        System.out.println(adminuserDao.judgeSale());
    }

    @Test
    public void updateDiscount() {
        int count=adminuserDao.updateDiscount(9, 0.8);
        System.out.println(count);
    }

    @Test
    public void updateByPrimaryKey() {
        int count=adminuserDao.updateByPrimaryKey(12,"小李",null,null);
        System.out.println(count);
    }
}