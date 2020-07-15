package org.ecommerce.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ecommerce.dto.Msg;
import org.ecommerce.entity.category;
import org.ecommerce.entity.product;
import org.ecommerce.service.salerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Component
@RequestMapping("/user")
public class userController {
    @Autowired
    private org.ecommerce.service.userService userService;


    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public String products(){
        return "products";
    }

    @RequestMapping(value = "/{pid}/productDetail",method = RequestMethod.GET)
    public String productDetail(Model model,@PathVariable("pid")Integer pid){
        product productDetail=userService.selectByPrimaryKey(pid);
        model.addAttribute("productDetail",productDetail);
        return "productDetail";
    }

    @RequestMapping(value = "/productDetailRefresh",method = RequestMethod.GET)
    @ResponseBody
    public product productDetailRefresh(Integer pid){
        product productDetailRefresh=userService.selectByPrimaryKey(pid);
        System.out.println("商品详情刷新成功");
        return productDetailRefresh;
    }

    @RequestMapping(value = "/categoryInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<category> categoryInfo(){
        List<category> category=userService.selectAllCate();
        System.out.println("查询目录成功");
        return category;
    }

    @RequestMapping(value = "/productInfo",method = RequestMethod.POST)
    @ResponseBody
    public Msg productInfo(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        System.out.println("进入productInfo");
        // 设置后台响应文本格式
        resp.setContentType("text/html;charset=utf-8");
        // 接收前台请求
        String pname = URLDecoder.decode(req.getParameter("name"),"UTF-8");
        String pnString=req.getParameter("pn");
        Integer pn=Integer.valueOf(pnString);
        System.out.println("pn="+pn+"  name="+pname);
        //在查询之前只需要调用，传入页码，以及每页的大小
        System.out.println("走到这里");
        PageHelper.startPage(pn, 1);
        List<product> product=userService.selectProBypname(pname);
        //输出用户
        for (product a : product) {
            System.out.println(a);
        }
        System.out.println("===================================");
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        //pageInfo里面封装了分页的详细信息，包括有我们查询出来的数据,页码导航传入连续显示的页数5
        PageInfo page = new PageInfo(product,5);
        System.out.println("pageNum="+page.getPageNum()+" pageSize="+page.getPageSize());
        return Msg.success().add("pageInfo", page);
    }


}
