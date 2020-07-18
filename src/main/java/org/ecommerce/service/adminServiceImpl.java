package org.ecommerce.service;

import org.ecommerce.dao.adminuserDao;
import org.ecommerce.entity.adminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//用户管理员实现类
@Service
public class adminServiceImpl implements adminService{
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private adminuserDao adminuserDao;


    /**
     *获取全部用户列表
     * @return
     */
    @Override
    public List<adminUser> getAdminUserList() {
        return adminuserDao.queryAll();
    }

    /**
     *获取指定uid的用户列表
     * @param uid
     * @return
     */
    @Override
    public List<adminUser> getAdminUserListByUid(short uid) {
        return adminuserDao.getAdminUserListByUid(uid);
    }

    /**
     *插入用户名、密码、用户类型，用户权限uid——0、1、2分别表示普通用户、产品销售商（只有一个）、网站管理员
     * @param username
     * @param password
     * @param uid
     * @return
     */
    @Override
    public int insertAdminUser(String username, String password, short uid) {
        int adminInsert=adminuserDao.insertAdminUser(username,password,uid);
        return adminInsert;
    }



    /**
     * 根据用户id删除用户
     * @param auid
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Integer auid) {
        int adminDelete=adminuserDao.deleteByPrimaryKey(auid);
        return  (adminDelete);
    }

    /**
     * 根据所给用户id设置用户权限
     * @param auid
     * @param uid
     * @return
     */
    @Override
    public int setRole(Integer auid, short uid) {
        int result=adminuserDao.setRole(auid,uid);
        return result;
    }

    /**
     * 查询用户名返回用户对象
     * @param username
     * @return
     */
    @Override
    public adminUser selectByName(String username) {
        return adminuserDao.selectByName(username);
    }

    /**
     * 判断是否已存在uid=1产品销售商,若小于等于1则s.t.
     * @return
     */
    @Override
    public int judgeSale() {
        return adminuserDao.judgeSale();
    }

    /**
     * 根据auid查询用户
     * @param auid
     * @return
     */
    @Override
    public adminUser selectByAuid(Integer auid) {
        return adminuserDao.selectByAuid(auid);
    }

}
