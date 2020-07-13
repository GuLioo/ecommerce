package org.ecommerce.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ecommerce.dto.Msg;
import org.ecommerce.entity.adminUser;
import org.ecommerce.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private org.ecommerce.service.adminService adminService;

    @RequestMapping(value = "/adminUserGet",method = RequestMethod.GET)
    @ResponseBody
    public Msg adminUserGet(short uid,@RequestParam(value="pn",defaultValue="1") Integer pn, Model model){
        System.out.println("进入adminUserGet");
        //使用PageHelper分页插件
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 1);
        System.out.println("走到这里");
        //startPage后面紧跟的这个查询就是一个分页查询
        List<adminUser> adminUser=adminService.getAdminUserListByUid(uid);
        //输出用户
        for (adminUser a : adminUser) {
            System.out.println(a);
        }
        System.out.println("===================================");
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        //pageInfo里面封装了分页的详细信息，包括有我们查询出来的数据,页码导航传入连续显示的页数5
        PageInfo page = new PageInfo(adminUser,3);
        System.out.println("pageNum="+page.getPageNum()+" pageSize="+page.getPageSize());
        return Msg.success().add("pageInfo", page);
    }

    @RequestMapping(value = "/adminUser",method = RequestMethod.GET)
    public String adminUser(){
        return "adminUser";
    }

    @RequestMapping(value = "/adminUserRefresh",method = RequestMethod.GET)
    @ResponseBody
    public List<adminUser> adminUserRefresh(){
        List<adminUser> adminUser=adminService.getAdminUserList();
        return adminUser;
    }


    @RequestMapping(value = "/adminAdd",method = RequestMethod.POST)
    @ResponseBody
    public String adminAdd(String userName,String password,short uid) {
        System.out.println("进入adminUserAdd");
        System.out.println("username="+userName+"  password"+password+"  uid="+uid);
        int result=adminService.insertAdminUser(userName,password,uid);
        if(result==1){
            System.out.println("result=添加成功");
            return "添加成功";
        }
        else {
            System.out.println("result=添加失败");
            return "添加失败";
        }
    }


    @RequestMapping(value = "/adminDelete",method = RequestMethod.POST)
    @ResponseBody
    public String adminDelete(Integer auid) {
        System.out.println("进入adminUserDel");
        System.out.println("auid="+auid);
        int result=adminService.deleteByPrimaryKey(auid);
        if(result==1){
            System.out.println("result=删除成功");
            return "删除成功";
        }
        else {
            System.out.println("result=删除失败");
            return "删除失败";
        }
    }

    @RequestMapping(value = "/adminUserChange",method = RequestMethod.POST)
    @ResponseBody
    public String adminUserChange(Integer auid,short uid) {
        System.out.println("进入adminUserChange");
        System.out.println("auid="+auid+"  uid="+uid);
        int result=adminService.setRole(auid,uid);
        if(result==1){
            System.out.println("result=更改成功");
            return "更改成功";
        }
        else {
            System.out.println("result=更改失败");
            return "更改失败";
        }
    }

}
