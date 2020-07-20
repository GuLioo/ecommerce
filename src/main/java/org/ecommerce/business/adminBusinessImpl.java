package org.ecommerce.business;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ecommerce.dto.pageResult;
import org.ecommerce.dto.ecommerceResult;
import org.ecommerce.dto.userExecution;
import org.ecommerce.dto.userStateEnum;
import org.ecommerce.entity.adminUser;
import org.ecommerce.exception.*;
import org.ecommerce.service.adminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

//用户管理员实现类
@Service
public class adminBusinessImpl implements adminBusiness{
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private adminService adminService;

    /**
     * 返回分页后的对应权限的用户列表
     * 可设置每页大小及页数
     * @param uid
     * @param pn
     * @return
     */
    @Override
    public pageResult adminUserGet(short uid, Integer pn) {
        //使用PageHelper分页插件
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 3);
        //startPage后面紧跟的这个查询就是一个分页查询
        List<adminUser> adminUser=adminService.getAdminUserListByUid(uid);
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        //pageInfo里面封装了分页的详细信息，包括有我们查询出来的数据,页码导航传入连续显示的页数5
        PageInfo page = new PageInfo(adminUser,5);
        return pageResult.success().add("pageInfo", page);
    }

    /**
     *插入用户名、密码、用户类型，用户权限uid——0、1、2分别表示普通用户、产品销售商（只有一个）、网站管理员
     * 密码md5+salt加密，判断产品销售商数量是否为已为1，否则抛出"销售商数目超限"异常
     * @param username
     * @param password
     * @param uid
     * @return
     */
    @Override
    public ecommerceResult insertAdminUser(String username, String password, short uid) {
        try{
            String md5Pass=getMD5(password);
            if((short)(uid)==1){
                if(adminService.judgeSale()>=1){
                    throw new userInsertException("销售商数目超限");
                }
                else {
                    int adminInsert=adminService.insertAdminUser(username,md5Pass,uid);
                    userExecution execution = new userExecution(username, userStateEnum.Change_SUCCESS);
                    return new ecommerceResult<userExecution>(true, execution);
                }
            }
            else {
                int adminInsert=adminService.insertAdminUser(username,md5Pass,uid);
                userExecution execution = new userExecution(username, userStateEnum.Change_SUCCESS);
                return new ecommerceResult<userExecution>(true, execution);
            }
        }
        catch (userInsertException e1){
            userExecution execution = new userExecution(username, userStateEnum.ADD_SALER_ERROR);
            return new ecommerceResult<userExecution>(true, execution);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            userExecution execution = new userExecution(username, userStateEnum.INNER_ERROR);
            return new ecommerceResult<userExecution>(true, execution);
        }
    }



    /**
     * 根据用户id删除用户
     * @param auid
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Integer auid) {
        int adminDelete=adminService.deleteByPrimaryKey(auid);
        return  (adminDelete);
    }

    /**
     *设置用户权限类型
     * 保证用户产品销售商只有1个，对其数量判断，否则抛“销售商数目超限”
     * @param auid
     * @param uid
     * @return
     */
    @Override
    public ecommerceResult setRole(Integer auid, short uid) {
        adminUser changeAdmin=adminService.selectByAuid(auid);
        try{
            //若从其它变为1
            if(uid==1){
                if(adminService.judgeSale()>=1){
                    throw new userInsertException("销售商数目超限");
                }
                else {
                    adminService.setRole(auid,uid);
                    userExecution execution = new userExecution(changeAdmin.getUsername(), userStateEnum.Change_SUCCESS);
                    return new ecommerceResult<userExecution>(true, execution);
                }
            }
            else {
                adminService.setRole(auid,uid);
                userExecution execution = new userExecution(changeAdmin.getUsername(), userStateEnum.Change_SUCCESS);
                return new ecommerceResult<userExecution>(true, execution);
            }
        }
        catch (userInsertException e1){
            userExecution execution = new userExecution(changeAdmin.getUsername(), userStateEnum.ADD_SALER_ERROR);
            return new ecommerceResult<userExecution>(true, execution);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            userExecution execution = new userExecution(changeAdmin.getUsername(), userStateEnum.INNER_ERROR);
            return new ecommerceResult<userExecution>(true, execution);
        }

    }

    /**
     * 登陆判断
     * 输入用户名和密码，若不符合抛出密码错误、不存在用户异常，
     * 否则返回登陆用户类型进行相应页面跳转并将用户id保存session
     * @param getUserName
     * @param getPassword
     * @return
     * @throws userLoginException
     */
    @Override
    public ecommerceResult executeLogin(String getUserName, String getPassword, HttpSession session) throws userLoginException{
        try{
            adminUser adminUser1=adminService.selectByName(getUserName);
            if(adminUser1==null){
                throw new userLogin_NoUser_Exception("不存在用户");
            }
            else if(!adminUser1.getPassword().equals(getMD5(getPassword))){
                throw new userLogin_passwordError_Exception("密码错误");
            }
            else {
                Integer auid=adminUser1.getAuid();
                // 将用户id保存到session内
                session.setAttribute("auid", auid);
                //返回成功结果
                if(adminUser1.getUid()==0){
                    userExecution result= new userExecution(getUserName, userStateEnum.USER_LOGIN_SUCCESS,adminUser1);
                    return new ecommerceResult<userExecution>(true, result);
                }
                else if(adminUser1.getUid()==1){
                    userExecution result= new userExecution(getUserName, userStateEnum.SALER_LOGIN_SUCCESS,adminUser1);
                    return new ecommerceResult<userExecution>(true, result);
                }
                else {
                    userExecution result= new userExecution(getUserName, userStateEnum.ADMIN_LOGIN_SUCCESS,adminUser1);
                    return new ecommerceResult<userExecution>(true, result);
                }
            }
        }
        catch (userLogin_NoUser_Exception e1){
            userExecution execution = new userExecution(getUserName, userStateEnum.NO_USER);
            return new ecommerceResult<userExecution>(true, execution);
        }
        catch (userLogin_passwordError_Exception e1){
            userExecution execution = new userExecution(getUserName, userStateEnum.PASSWORD_ERROR);
            return new ecommerceResult<userExecution>(true, execution);
        }
        catch (userLoginException e){
            userExecution execution = new userExecution(getUserName, userStateEnum.INNER_ERROR);
            return new ecommerceResult<userExecution>(true, execution);
        }
    }

    //将密码md5加密
    private String getMD5(String password){
        String base=password+"/"+slat;
        String md5= DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
    //密码盐值
    private final String slat="asdfasvrg54mbesognoamg;s'afmaslgma";

    /**
     * 注销用户
    * @param session
     */
    @Override
    public void logOut(HttpSession session) {
        session.invalidate();
    }


}
