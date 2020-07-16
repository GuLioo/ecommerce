package org.ecommerce.service;

import org.ecommerce.dao.*;
import org.ecommerce.entity.adminUser;
import org.ecommerce.entity.category;
import org.ecommerce.entity.orders;
import org.ecommerce.entity.product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    @Override
    public int updateByPrimaryKey(Integer auid, String name, String email, String phone) {
        return adminuserDao.updateByPrimaryKey(auid,name,email,phone);
    }

    @Override
    public adminUser userInfo(Integer auid) {
        return adminuserDao.selectByAuid(auid);
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
    public orders createOrder(product product, adminUser user) {
        String orderId=createId();
        System.out.println("orderId="+orderId);
        double finalPrice=product.getMarketPrice();
        if(user.getDiscount()!=1.0){
            finalPrice=product.getMarketPrice()*user.getDiscount();
        }
        Timestamp time = new Timestamp(System.currentTimeMillis());
        orders createOrder=new orders(orderId,user.getAuid(),finalPrice,user.getDiscount(),product.getPid(),product.getMarketPrice(),product.getPdesc(),product.getImage(),product.getPdesc(),product.getPname(),(short)0,time);
        return createOrder;
    }

    @Override
    public int insert(orders orders) {

        int result=ordersDao.insert(orders.getOid(),orders.getUserId(),orders.getOrderPrice(),orders.getUserDiscount(),orders.getProductId(),orders.getProductPrice(),orders.getProductName(),orders.getProductImage(),orders.getProductDesc(),orders.getProductCate(),(short)0,orders.getOrderTime());
        return result;
    }

    @Override
    public int reduceProduct(Integer pid) {
        int result=productDao.reduceProduct(pid);
        return result;
    }


    @Override
    public List<orders> findOrderByUid(Integer userId) {
        return ordersDao.findOrderByUid(userId);
    }

    @Override
    public List<orders> findOrderByOid_State_Time(String oid, short state, Date start_time, Date end_time) {
        return ordersDao.findOrderByOid_State_Time(oid,state,start_time,end_time);
    }

    //createId
    Integer number=0;//唯一数字,集群第一台=0，第二台=200000,第三台=400000
    int maxNum=200000;//最大值,集群第一台=200000，第二台=400000,第三台=600000
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");//年月日格式
    /**
     * 生成订单编号 17+ 位数
     *  思路:一个业务在1毫秒内并发的数量有多少，有一万那真是顶天了,意味着 一秒 有1000万的并发。kafaka,redis的性能不过10万，那我把 number的上限设置成20 万,那就是一秒200000*1000=2亿的并发,不够再集群
     *  宕机了，进程死了，这个跟程序没有关系。你别忘了前面精确到毫秒的17位数，我们只关心一毫秒内的并发问题
     * @return
     */
    public  String createId(){
        number++;//唯一数字自增
        if(number>=maxNum){ // 值的上限，超过就归零
            number=maxNum-200000;
        }
        return sdf.format(new Date())+number;//返回时间+一毫秒内唯一数字的编号，区分机器可以加字母ABC...
    }


}
