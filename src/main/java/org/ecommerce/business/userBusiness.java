package org.ecommerce.business;

import org.ecommerce.dto.pageResult;
import org.ecommerce.dto.ecommerceResult;
import org.ecommerce.entity.adminUser;
import org.ecommerce.entity.category;
import org.ecommerce.entity.orders;
import org.ecommerce.entity.product;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public interface userBusiness {
    //显示所有目录
    List<category> selectAllCate();
    //查询单个商品
    product selectByPrimaryKey(Integer pid);
    //生成订单信息来显示但不进行数据库插入
    void createOrder(product product, adminUser user);
    //执行减库存、增加订单的事务
    ecommerceResult executeSeckill(HttpSession session);
    //分页查询获取用户订单信息
     pageResult userOrdersInfo(Integer pn, HttpSession session) throws UnsupportedEncodingException;
    //根据pid获取订单详情
    orders orderDetail(HttpSession session,Integer pid);
    //根据商品类别名称获取商品列表进行分页操作
    pageResult productInfo(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException;
    //根据输入订单编号前缀、起始、截至时间进行查询，获取相应订单进行分页
    pageResult searchOrdersInfo(@RequestParam(value="pn",defaultValue="1")Integer pn, @DateTimeFormat(pattern="yyyy-MM-dd")Date startTime, @DateTimeFormat(pattern="yyyy-MM-dd")Date endTime, String orderId, HttpSession session) throws UnsupportedEncodingException;

    }
