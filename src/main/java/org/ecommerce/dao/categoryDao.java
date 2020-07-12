package org.ecommerce.dao;

import org.apache.ibatis.annotations.Param;
import org.ecommerce.entity.category;

import java.util.List;

public interface categoryDao {
    //查询所有目录商品
    List<category> selectAll();
    //增加目录信息
    int insert(String cname);
    //删除目录信息
    int deleteBycname(String cname);
    //更改目录信息
    int updateByPrimaryKey(@Param("cid") Integer cid,@Param("cname")  String cname);
    //根据目录名称查找目录id
    category selectBycname(String cname);
    //根据目录id查找目录名称
    category selectBycid(Integer cid);
}
