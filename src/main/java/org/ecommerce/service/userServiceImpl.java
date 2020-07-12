package org.ecommerce.service;

import org.ecommerce.dao.*;
import org.ecommerce.entity.category;
import org.ecommerce.entity.orders;
import org.ecommerce.entity.product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class userServiceImpl implements userService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private categoryDao categoryDao;
    @Resource
    private adminuserDao adminuserDao;
    @Resource
    private ordersDao ordersDao;
    @Resource
    private productDao productDao;

    @Override
    public int updateByPrimaryKey(Integer auid, String name, String email, String phone) {
        return adminuserDao.updateByPrimaryKey(auid,name,email,phone);
    }



    @Override
    public List<category> selectAllCate() {
        return categoryDao.selectAll();
    }

    @Override
    public List<product> selectProBypname(String pname) {
        return productDao.selectBypname(pname);
    }

    @Override
    public product selectByPrimaryKey(Integer pid) {
        return productDao.selectByPrimaryKey(pid);
    }

    @Override
    public int insert(Integer userId, Double money, short state) {
        return ordersDao.insert(userId,money,state);
    }

    @Override
    public List<orders> findOrderByUid(Integer userId) {
        return ordersDao.findOrderByUid(userId);
    }

    @Override
    public List<orders> findOrderByOid_State_Time(Integer oid, short state, Date start_time, Date end_time) {
        return ordersDao.findOrderByOid_State_Time(oid,state,start_time,end_time);
    }
}
