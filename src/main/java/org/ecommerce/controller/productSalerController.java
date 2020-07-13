package org.ecommerce.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ecommerce.dto.Msg;
import org.ecommerce.entity.adminUser;
import org.ecommerce.entity.category;
import org.ecommerce.entity.product;
import org.ecommerce.service.adminService;
import org.ecommerce.service.salerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Component
@RequestMapping("/saler")
public class productSalerController {
    @Autowired
    private salerService salerService;


    @RequestMapping(value = "/salerRefresh",method = RequestMethod.GET)
    public String products(){
        return "products";
    }

    @RequestMapping(value = "/salerDelete",method = RequestMethod.POST)
    @ResponseBody
    public String salerDelete(Integer pid) {
        System.out.println("进入salerDelete");
        System.out.println("pid="+pid);
        int result=salerService.deleteProByPrimaryKey(pid);
        if(result==1){
            System.out.println("result=删除成功");
            return "删除成功";
        }
        else {
            System.out.println("result=删除失败");
            return "删除失败";
        }
    }

    @RequestMapping(value = "/salerAdd",method = RequestMethod.POST)
    @ResponseBody
    public String salerAdd(String pname, Double marketPrice, String image ,String pdesc,Integer  pnum) {
        System.out.println("salerAdd");
        System.out.println("pname="+pname+"  marketPrice"+marketPrice+"  image="+image+"  pdesc="+pdesc+"  pnum="+pnum);
        int result=salerService.insertPro(pname,marketPrice,image,pdesc,pnum);
        if(result==1){
            System.out.println("result=添加成功");
            return "添加成功";
        }
        else {
            System.out.println("result=添加失败");
            return "添加失败";
        }
    }


    @RequestMapping(value = "/productChange",method = RequestMethod.POST)
    @ResponseBody
    public String productChange(Integer pid, String pname, String marketPrice, String image ,String pdesc,String  pnum) {
        System.out.println("进入productChange");
        Double price=null;
        Integer num=null;
        if(marketPrice!=""){
            price=Double.valueOf(marketPrice);
        }
        System.out.println("marketPrice="+marketPrice+" price="+price);
        if(pnum!=""){
            num=Integer.valueOf(pnum);
        }
        System.out.println("pnum="+pnum+" num="+num);
        System.out.println("pid="+pid+"  pname="+pname+" price="+price+" image="+image+" pdesc="+pdesc+" num="+num);
        int result=salerService.updateByPrimaryKey(pid,pname,price,image,pdesc,num);
        if(result==1){
            System.out.println("result=用户更改成功");
            return "用户更改成功";
        }
        else {
            System.out.println("result=用户更改失败");
            return "用户更改失败";
        }
    }


    @RequestMapping(value = "/categoryInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<category> categoryInfo(){
        List<category> category=salerService.selectAllCate();
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
        List<product> product=salerService.selectProBypname(pname);
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
