package org.ecommerce.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ecommerce.dto.Msg;
import org.ecommerce.entity.adminUser;
import org.ecommerce.entity.category;
import org.ecommerce.entity.product;
import org.ecommerce.service.salerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Component
@RequestMapping("/saler")
public class productSalerController {
    @Autowired
    private salerService salerService;


    @RequestMapping(value = "/productSaler",method = RequestMethod.GET)
    public String productSaler(){
        return "productSaler";
    }

    @RequestMapping(value = "/salerDelete",method = RequestMethod.POST)
    @ResponseBody
    public String salerDelete(Integer pid) {
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



    @RequestMapping(value = "/salerChange",method = RequestMethod.POST)
    @ResponseBody
    public String salerChange(Integer pid, String pname, Double marketPrice, String image ,String pdesc,Integer  pnum) {
        System.out.println("进入productChange=========================================");
        System.out.println("pid="+pid+"  pname="+pname+" price="+marketPrice+" image="+image+" pdesc="+pdesc+" num="+pnum);
        int result=salerService.updateByPrimaryKey(pid,pname,marketPrice,image,pdesc,pnum);
        if(result==1){
            return "更改成功";
        }
        else {
            return "更改失败";
        }
    }


    @RequestMapping(value = "/categoryInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<category> categoryInfo(){
        System.out.println("查询目录成功=============================");
        List<category> category=salerService.selectAllCate();
        for (org.ecommerce.entity.category cate : category) {
            System.out.println(cate);
        }
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
        PageHelper.startPage(pn, 2);
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

    @RequestMapping(value="/addProduct",method = RequestMethod.POST)
    @ResponseBody
    public String save(MultipartFile file,HttpServletRequest request) throws IOException {
        System.out.println("进入图片存储======================================");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String name = multipartRequest.getParameter("cate");
        String price = multipartRequest.getParameter("price");
        double pPrice=Double.parseDouble(price);
        String description = multipartRequest.getParameter("desc");
        String num = multipartRequest.getParameter("num");
        Integer pNum=Integer.parseInt(num);
        System.out.println("name=="+name+"price======"+price+"  description====="+description+"   num==="+num);
        /**
         * 上传图片
         */
        //图片上传成功后，将图片的地址写到数据库
        String filePath = "C:\\Users\\谷粒\\Desktop\\coding\\ecommerce\\src\\main\\webapp\\resource\\images\\pic";//保存图片的路径,tomcat中有配置
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename="+originalFilename);
        //新的文件名字，使用uuid随机生成数+原始图片名字，这样不会重复
        String newFileName =originalFilename;
        //全路径：硬盘路径+文件名
        File targetFile = new File(filePath,newFileName);
        System.out.println("targetFile="+targetFile);
        //本地文件传到封装好的全路径
        file.transferTo(targetFile);


        //增加产品信息
        int result=salerService.insertPro(name,pPrice,newFileName,description,pNum);
        return "success"; //重定向到查询
    }

}
