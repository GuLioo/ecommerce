package org.ecommerce.service;

import org.ecommerce.dao.*;
import org.ecommerce.dto.seckillStateEnum;
import org.ecommerce.dto.seckillExecution;
import org.ecommerce.entity.adminUser;
import org.ecommerce.entity.category;
import org.ecommerce.entity.orders;
import org.ecommerce.entity.product;
import org.ecommerce.exception.seckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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


    /**
     * 根据id查看用户信息
     * @param auid
     * @return
     */
    @Override
    public adminUser userInfo(Integer auid) {
        return adminuserDao.selectByAuid(auid);
    }

    /**
     * 显示所有目录
     * @return
     */
    @Override
    public List<category> selectAllCate() {
        return categoryDao.selectAll();
    }

    /**
     * 找某一种类的商品：根据目录名查找商品
     * @param pname
     * @return
     */
    @Override
    public List<product> selectProBypname(String pname) {
        return productDao.selectBypname(pname);
    }

    /**
     * 根据商品id获取商品信息
     * @param pid
     * @return
     */
    @Override
    public product selectByPrimaryKey(Integer pid) {
        return productDao.selectByPrimaryKey(pid);
    }




    /**
     * 根据用户id查询相应订单
     * @param userId
     * @return
     */
    @Override
    public List<orders> findOrderByUid(Integer userId) {
        return ordersDao.findOrderByUid(userId);
    }

    /**
     * 基于订单号、订单时间等条件搜索订单
     * @param userId
     * @param oid
     * @param state
     * @param start_time
     * @param end_time
     * @return
     */
    @Override
    public List<orders> findOrderByOid_State_Time(Integer userId,String oid, short state, Date start_time, Date end_time) {
        return ordersDao.findOrderByOid_State_Time(userId,oid,state,start_time,end_time);
    }

    /**
     * 根据商品id减库存
     * @param pid
     * @return
     */
    @Override
    public int reduceProduct(Integer pid) {
        return productDao.reduceProduct(pid);
    }

    /**
     * 根据商品id将那行上锁
     * @param pid
     * @return
     */
    @Override
    public product lockProduct(Integer pid) {
        return productDao.lockProduct(pid);
    }

    /**
     * 插入订单信息
     * @param oid
     * @param userId
     * @param orderPrice
     * @param userDiscount
     * @param productId
     * @param productPrice
     * @param productName
     * @param productImage
     * @param productDesc
     * @param productCate
     * @param orderState
     * @param orderTime
     * @return
     */
    @Override
    public int insert(String oid, Integer userId, Double orderPrice, Double userDiscount, Integer productId, Double productPrice, String productName, String productImage, String productDesc, String productCate, short orderState, Timestamp orderTime) {
        return ordersDao.insert(oid, userId, orderPrice, userDiscount, productId, productPrice, productName, productImage, productDesc, productCate, (short) 0, orderTime);
    }


}
