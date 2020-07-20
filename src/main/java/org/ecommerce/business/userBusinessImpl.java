package org.ecommerce.business;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ecommerce.dto.pageResult;
import org.ecommerce.dto.ecommerceResult;
import org.ecommerce.dto.seckillExecution;
import org.ecommerce.dto.seckillStateEnum;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//普通用户
@Service
public class userBusinessImpl implements userBusiness {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private org.ecommerce.service.userService userService;


    /**
     * 显示所有目录
     * @return
     */
    @Override
    public List<category> selectAllCate() {
        return userService.selectAllCate();
    }



    /**
     * 根据商品id获取商品信息
     * @param pid
     * @return
     */
    @Override
    public product selectByPrimaryKey(Integer pid) {
        return userService.selectByPrimaryKey(pid);
    }

    /**
     * 根据商品和用户创建订单
     * 暂不进行购买及数据库插入订单处理
     * @param product
     * @param user
     * @return
     */
    @Override
    public void createOrder(product product, adminUser user) {
        String orderId=createId();
        double finalPrice=product.getMarketPrice();
        if(user.getDiscount()!=1.0){
            finalPrice=product.getMarketPrice()*user.getDiscount();
        }
        Timestamp time = new Timestamp(System.currentTimeMillis());
        orders createOrder=new orders(orderId,user.getAuid(),finalPrice,user.getDiscount(),product.getPid(),product.getMarketPrice(),product.getPdesc(),product.getImage(),product.getPdesc(),product.getPname(),(short)0,time);
        uidOrder.put(user.getAuid(),createOrder);
    }

    //createOrderId
    static Integer number=0;//唯一数字
    static int maxNum=200000;//最大值
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");//年月日格式
    /**
     * 生成订单编号 17+ 位数
     * yyyyMMddHHmmssSSS+1ms的number
     * @return
     */
    public static  String createId(){
        number++;//唯一数字自增
        if(number>=maxNum){ // 值的上限，超过就归零
            number=maxNum-200000;
        }
        return sdf.format(new Date())+number;//返回时间+一毫秒内唯一数字的编号
    }
    //保存创建的订单，供插入数据库使用
    HashMap<Integer,orders> uidOrder=new HashMap<Integer,orders>();

    /**
     * 执行减库存、增加订单的事务
     * @return
     */
    @Transactional
    @Override
    public ecommerceResult executeSeckill(HttpSession session) {
        Integer uid=(Integer) session.getAttribute("auid");
        orders orders=uidOrder.get(uid);
        Integer pid=orders.getProductId();
        try {
            product product=userService.lockProduct(pid);
            int result1 = userService.reduceProduct(pid);
            if (result1 <= 0) {
                throw new seckillException("库存不足");
            } else {
                int result2 = userService.insert(orders.getOid(), orders.getUserId(), orders.getOrderPrice(), orders.getUserDiscount(), orders.getProductId(), orders.getProductPrice(), orders.getProductName(), orders.getProductImage(), orders.getProductDesc(), orders.getProductCate(), (short) 0, orders.getOrderTime());
                if (result2 <= 0) {
                    throw new Exception("插入订单出错");
                } else {
                    seckillExecution execution=new seckillExecution(orders.getOid(), seckillStateEnum.SUCCESS);
                    uidOrder.remove(uid);
                    return new ecommerceResult<seckillExecution>(true,execution);
                }
            }
        }
        catch (seckillException e1){
            seckillExecution execution=new seckillExecution(orders.getOid(), seckillStateEnum.UNDER_STOCK);
            uidOrder.remove(uid);
            return new ecommerceResult<seckillExecution>(true,execution);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            seckillExecution execution=new seckillExecution(orders.getOid(), seckillStateEnum.INNER_ERROR);
            uidOrder.remove(uid);
            return new ecommerceResult<seckillExecution>(true,execution);
        }
    }


    /**
     * 分页查询获取用户订单信息
     * @param pn
     * @param session
     * @return
     * @throws UnsupportedEncodingException
     */
    @Override
    public pageResult userOrdersInfo(Integer pn, HttpSession session) throws UnsupportedEncodingException {
        Integer uid=(Integer) session.getAttribute("auid");
        //使用PageHelper分页插件
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟的这个查询就是一个分页查询
        List<orders> orders=userService.findOrderByUid(uid);
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        //pageInfo里面封装了分页的详细信息，包括有我们查询出来的数据,页码导航传入连续显示的页数5
        PageInfo page = new PageInfo(orders,10);
        return pageResult.success().add("pageInfo", page);
    }

    /**
     * 根据商品id和session中的用户id创建订单
     * @param session
     * @param pid
     * @return
     */
    @Override
    public orders orderDetail(HttpSession session, Integer pid) {
        product product=userService.selectByPrimaryKey(pid);
        Integer uid=(Integer) session.getAttribute("auid");
        adminUser user=userService.userInfo(uid);
        createOrder(product,user);
        orders orderDetail=uidOrder.get(uid);
        return orderDetail;
    }

    /**
     * 根据商品类别名称获取商品列表进行分页操作
     * @param req
     * @param resp
     * @return
     * @throws UnsupportedEncodingException
     */
    @Override
    public pageResult productInfo(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        // 设置后台响应文本格式
        resp.setContentType("text/html;charset=utf-8");
        // 接收前台请求
        String pname = URLDecoder.decode(req.getParameter("name"),"UTF-8");
        String pnString=req.getParameter("pn");
        Integer pn=Integer.valueOf(pnString);
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 2);
        List<product> product=userService.selectProBypname(pname);
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        //pageInfo里面封装了分页的详细信息，包括有我们查询出来的数据,页码导航传入连续显示的页数5
        PageInfo page = new PageInfo(product,5);
        return pageResult.success().add("pageInfo", page);
    }

    /**
     * 根据输入订单编号前缀、起始、截至时间进行查询
     * 获取相应订单进行分页
     * @param pn
     * @param startTime
     * @param endTime
     * @param orderId
     * @param session
     * @return
     * @throws UnsupportedEncodingException
     */
    @Override
    public pageResult searchOrdersInfo(Integer pn, Date startTime, Date endTime, String orderId, HttpSession session) throws UnsupportedEncodingException {
        Integer uid=(Integer) session.getAttribute("auid");
        //使用PageHelper分页插件
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟的这个查询就是一个分页查询
        List<orders> orders=userService.findOrderByOid_State_Time(uid,orderId,(short) 0,startTime,endTime);
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        //pageInfo里面封装了分页的详细信息，包括有我们查询出来的数据,页码导航传入连续显示的页数5
        PageInfo page = new PageInfo(orders,3);
        return pageResult.success().add("pageInfo", page);
    }
}
