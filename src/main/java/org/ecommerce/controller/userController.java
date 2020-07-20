package org.ecommerce.controller;

import org.ecommerce.dto.pageResult;
import org.ecommerce.dto.ecommerceResult;
import org.ecommerce.dto.seckillExecution;
import org.ecommerce.entity.category;
import org.ecommerce.entity.orders;
import org.ecommerce.entity.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Component
@RequestMapping("/user")
public class userController {
    @Autowired
    private org.ecommerce.business.userBusiness userBusiness;

    //商品列表页及相关请求
    /**
     * 商品列表页
     * @return
     */
    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public String products(){
        return "products";
    }

    /**
     * 查看目录信息
     * @return
     */
    @RequestMapping(value = "/categoryInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<category> categoryInfo(){
        List<category> category=userBusiness.selectAllCate();
        return category;
    }

    /**
     * 将某类商品进行分页操作
     * 根据种类名称获取商品列表
     * @param req
     * @param resp
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/productInfo",method = RequestMethod.POST)
    @ResponseBody
    public pageResult productInfo(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        return userBusiness.productInfo(req, resp);
    }




    //商品详情页及相关请求
    /**
     * 根据商品id获取商品信息并跳转至商品详情页
     * @param model
     * @param pid
     * @return
     */
    @RequestMapping(value = "/{pid}/productDetail",method = RequestMethod.GET)
    public String productDetail(Model model,@PathVariable("pid")Integer pid){
        product productDetail=userBusiness.selectByPrimaryKey(pid);
        model.addAttribute("productDetail",productDetail);
        return "productDetail";
    }

    /**
     * 获取商品图片的地址
     * @param pid
     * @return
     */
    @RequestMapping(value = "/productDetailRefresh",method = RequestMethod.GET)
    @ResponseBody
    public product productDetailRefresh(Integer pid){
        product productDetailRefresh=userBusiness.selectByPrimaryKey(pid);
        return productDetailRefresh;
    }




    //订单详情页及相关请求
    /**
     * 根据商品id和session中的用户id创建订单并转到订单详情页
     * @param session
     * @param model
     * @param pid
     * @return
     */
    @RequestMapping(value = "/{pid}/orderDetail",method = RequestMethod.GET)
    public String orderDetail(HttpSession session,Model model, @PathVariable("pid")Integer pid){
        orders orderDetail=userBusiness.orderDetail(session, pid);
        model.addAttribute("orderDetail",orderDetail);
        return "orderDetail";
    }

    /**
     * 获取商品图片的地址
     * @param pid
     * @return
     */
    @RequestMapping(value = "/orderDetailRefresh",method = RequestMethod.GET)
    @ResponseBody
    public product orderDetailRefresh(Integer pid){
        product productDetailRefresh=userBusiness.selectByPrimaryKey(pid);
        return productDetailRefresh;
    }

    /**
     * 执行插入生成订单，减库存的事务
     * @return
     */
    @RequestMapping(value = "/executeSeckill",method = RequestMethod.POST)
    @ResponseBody
    public ecommerceResult<seckillExecution> executeSeckill(HttpSession session){
        return userBusiness.executeSeckill(session);
    }




    //用户订单信息页及其相关请求
    /**
     * 查看当前用户的订单信息页
     * @return
     */
    @RequestMapping(value="/userOrders",method = RequestMethod.GET)
    public String userOrders(){
        return "userOrders";
    }

    /**
     * 分页查询获取用户订单信息
     * @param pn
     * @param session
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/userOrdersInfo",method = RequestMethod.POST)
    @ResponseBody
    public pageResult userOrdersInfo(@RequestParam(value="pn",defaultValue="1") Integer pn, HttpSession session) throws UnsupportedEncodingException {
        return userBusiness.userOrdersInfo(pn, session);
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
    @RequestMapping(value = "/searchOrdersInfo",method = RequestMethod.POST)
    @ResponseBody
    public pageResult searchOrdersInfo(@RequestParam(value="pn",defaultValue="1")Integer pn, @DateTimeFormat(pattern="yyyy-MM-dd")Date startTime, @DateTimeFormat(pattern="yyyy-MM-dd")Date endTime, String orderId, HttpSession session) throws UnsupportedEncodingException {
        return userBusiness.searchOrdersInfo(pn, startTime, endTime, orderId, session);
    }



}
