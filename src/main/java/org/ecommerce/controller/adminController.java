package org.ecommerce.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ecommerce.dto.Msg;
import org.ecommerce.dto.ecommerceResult;
import org.ecommerce.entity.adminUser;
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
    private org.ecommerce.business.adminBusiness adminBusiness;

    /**
     * 返回分页后的对应权限的用户列表
     * @param uid
     * @param pn
     * @return
     */
    @RequestMapping(value = "/adminUserGet",method = RequestMethod.POST)
    @ResponseBody
    public Msg adminUserGet(short uid,@RequestParam(value="pn",defaultValue="1") Integer pn){
        return adminBusiness.adminUserGet(uid,pn);
    }

    /**
     * 返回用户管理页面
     * @return
     */
    @RequestMapping(value = "/adminUser",method = RequestMethod.GET)
    public String adminUser(){
        return "adminUser";
    }


    /**
     * 添加用户
     * @param userName
     * @param password
     * @param uid
     * @return
     */
    @RequestMapping(value = "/adminUserAdd",method = RequestMethod.POST)
    @ResponseBody
    public ecommerceResult adminUserAdd(String userName, String password, short uid) {
        return adminBusiness.insertAdminUser(userName,password,uid);
    }

    /**
     * 删除用户
     * @param auid
     * @return
     */
    @RequestMapping(value = "/adminUserDelete",method = RequestMethod.POST)
    @ResponseBody
    public String adminUserDelete(Integer auid) {
        int result=adminBusiness.deleteByPrimaryKey(auid);
        if(result==1){
            return "删除成功";
        }
        else {
            return "删除失败";
        }
    }

    /**
     * 改变用户权限类型
     * @param auid
     * @param uid
     * @return
     */
    @RequestMapping(value = "/adminUserChange",method = RequestMethod.POST)
    @ResponseBody
    public ecommerceResult adminUserChange(Integer auid,short uid) {
        return adminBusiness.setRole(auid,uid);
    }

}
