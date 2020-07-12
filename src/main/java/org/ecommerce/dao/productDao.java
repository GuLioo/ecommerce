package org.ecommerce.dao;

import org.apache.ibatis.annotations.Param;
import org.ecommerce.entity.product;

import java.util.List;

public interface productDao {
    //增加商品
    int insert(@Param("pname") String pname, @Param("market_Price") double market_Price, @Param("image")String image, @Param("pdesc") String pdesc,@Param("pnum") Integer pnum);
    //根据pid删除商品
    int deleteByPrimaryKey(Integer pid);
    //根据pname删除商品信息
    int deleteBypname(String pname);
    //修改商品，成功返回1
    int updateByPrimaryKey(@Param("pid") Integer pid, @Param("pname") String pname,@Param("market_Price") double market_Price,@Param("image") String image ,@Param("pdesc") String pdesc,@Param("pnum") Integer pnum);
    //修改商品数量
    int updateNumByPrimaryKey(@Param("pid") Integer pid ,@Param("pnum") Integer pnum);
    //修改商品种类名称
    int updateNameByPrimaryKey(@Param("pid")Integer pid ,@Param("pname") String pname);
    //查询单个商品
    product selectByPrimaryKey(Integer pid);
    //查询所有商品
    List< product > selectAll();
    //查询所有商品数量
    int countProducyAll();
    //根据pname查询商品数量
    int countProducyByname( @Param("pname") String pname);
    //根据pname查询相应商品列表
    List< product > selectBypname(String pname);
}
