package org.ecommerce.service;

import org.ecommerce.dao.adminuserDao;
import org.ecommerce.dao.userDao;
import org.ecommerce.entity.adminUser;
import org.ecommerce.exception.userException;
import org.ecommerce.exception.userInsertException;
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
    @Resource
    private userDao userDao;

    private final String slat="asdfasvrg54mbesognoamg;s'afmaslgma";

    @Override
    public List<adminUser> getAdminUserList() {
        return adminuserDao.queryAll(0,100);
    }

    @Override
    public List<adminUser> getAdminUserListByUid(short uid) {
        return adminuserDao.getAdminUserListByUid(uid);
    }

    @Override
    public int insertAdminUser(String username, String password, short uid) {
        try{
            String md5Pass=getMD5(password);
            if(adminuserDao.judgeSale()>=1){
                throw new userInsertException("销售商数目超限");
            }
            else {
                int adminInsert=adminuserDao.insertAdminUser(username,md5Pass,uid);
                adminUser insertAdmin=adminuserDao.selectByName(username);
                int userInsert=1;
                if(uid==0){
                    userInsert=userDao.insert(insertAdmin.getAuid(),username,md5Pass);
                }
                return  (userInsert&adminInsert);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new userException("内部错误   "+e.getMessage());
        }
    }

    private String getMD5(String seckillId){
        String base=seckillId+"/"+slat;
        String md5= DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    public int deleteByPrimaryKey(Integer auid) {
        adminUser deleteAdmin=adminuserDao.selectByAuid(auid);
        short deleteUid=deleteAdmin.getUid();
        int adminDelete=adminuserDao.deleteByPrimaryKey(auid);
        int userDelete=1;
        if(deleteUid==0){
            userDao.deleteByPrimaryKey(auid);
        }
        return  (userDelete&adminDelete);
    }

    @Override
    public int setRole(Integer auid, short uid) {
        adminUser changeAdmin=adminuserDao.selectByAuid(auid);
        short orginUid=changeAdmin.getUid();
        Integer orginAuid=changeAdmin.getAuid();
        int result=1;
        //若从uid=0变为其它
        if(orginUid==0){
            int one=userDao.deleteByPrimaryKey(orginAuid);
            int two=adminuserDao.setRole(orginAuid,uid);
            result=one&two;
        }
        else if(uid==0){//从其它的变为0
            int one=adminuserDao.setRole(orginAuid,uid);
            int two=userDao.insert(orginAuid,changeAdmin.getUsername(),changeAdmin.getPassword());
            result=one&two;
        }
        else {//其它变为其它
            result=adminuserDao.setRole(orginAuid, uid);
        }
        return result;
    }

    @Override
    public int countUserAll() {
        return adminuserDao.countUserAll();
    }

    @Override
    public adminUser selectByName(String username) {
        return adminuserDao.selectByName(username);
    }
}
