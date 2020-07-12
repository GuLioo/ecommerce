package org.ecommerce.service;

import org.ecommerce.dao.adminuserDao;
import org.ecommerce.dto.userLoginExecution;
import org.ecommerce.dto.userStateEnum;
import org.ecommerce.entity.adminUser;
import org.ecommerce.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class adminServiceImpl implements adminService{
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private adminuserDao adminuserDao;


    private final String slat="asdfasvrg54mbesognoamg;s'afmaslgma";

    @Override
    public List<adminUser> getAdminUserList() {
        return adminuserDao.queryAll();
    }

    @Override
    public List<adminUser> getAdminUserListByUid(short uid) {
        return adminuserDao.getAdminUserListByUid(uid);
    }

    @Override
    public int insertAdminUser(String username, String password, short uid) {
        try{
            String md5Pass=getMD5(password);
            if((short)(uid)==1){
                if(adminuserDao.judgeSale()>=1){
                    throw new userInsertException("销售商数目超限");
                }
                else {
                    int adminInsert=adminuserDao.insertAdminUser(username,md5Pass,uid);
                    return adminInsert;
                }
            }
            else {
                int adminInsert=adminuserDao.insertAdminUser(username,md5Pass,uid);
                return  adminInsert;
            }
        }
        catch (userInsertException e1){
            throw e1;
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new ecommerceException("内部错误   "+e.getMessage());
        }
    }

    private String getMD5(String seckillId){
        String base=seckillId+"/"+slat;
        String md5= DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    public int deleteByPrimaryKey(Integer auid) {
        int adminDelete=adminuserDao.deleteByPrimaryKey(auid);
        return  (adminDelete);
    }

    @Override
    public int setRole(Integer auid, short uid) {
        try{
            adminUser changeAdmin=adminuserDao.selectByAuid(auid);
            Integer orginAuid=changeAdmin.getAuid();
            int result=1;
            //若从其它变为1
            if(uid==1){
                if(adminuserDao.judgeSale()>=1){
                    throw new userInsertException("销售商数目超限");
                }
                result=adminuserDao.setRole(orginAuid,uid);
            }
            else {
                result=adminuserDao.setRole(orginAuid, uid);
            }
            return result;
        }
        catch (userInsertException e1){
            throw e1;
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new ecommerceException("内部错误   "+e.getMessage());
        }

    }

    @Override
    public int countUserAll() {
        return adminuserDao.countUserAll();
    }

    @Override
    public adminUser selectByName(String username) {
        return adminuserDao.selectByName(username);
    }

    @Override
    public userLoginExecution executeLogin(String getUserName,String getPassword) throws ecommerceException,userLoginException{
        try{
            adminUser adminUser1=adminuserDao.selectByName(getUserName);
            if(adminUser1==null){
                throw new userLogin_NoUser_Exception("不存在用户");
            }
            else if(!adminUser1.getPassword().equals(getMD5(getPassword))){
                throw new userLogin_passwordError_Exception("密码错误");
            }
            else {
                return new userLoginExecution(getUserName, userStateEnum.LOGIN_SUCCESS,adminUser1);
            }
        }
        catch (userLogin_NoUser_Exception e1){
            throw e1;
        }
        catch (userLogin_passwordError_Exception e1){
            throw e1;
        }
        catch (userLoginException e){
            logger.error(e.getMessage(),e);
            throw new ecommerceException("内部错误"+e.getMessage());
        }
    }

    @Override
    public int updateDiscount(Integer auid, double discount) {
        return adminuserDao.updateDiscount(auid,discount);
    }
}
