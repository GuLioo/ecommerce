package org.ecommerce.service;

import org.ecommerce.entity.category;
import org.ecommerce.entity.product;

import java.util.List;

public interface salerService {
    //查找全部目录
    List<category> selectAllCate();
    //增加商品：根据所给目录、商品名、价格、数量blabla 增加
    int insertPro(String pname,double marketPrice,String image,String pdesc,Integer pnum);
    //最多销售10种商品，要判断数量
    //减少商品：根据所给目录、商品名删除商品
    int deleteProByPrimaryKey(Integer pid);
    //找某一种类的商品：根据目录名查找商品
    List<product> selectProBypname(String pname);
    //更改商品信息
    //根据所给目录，商品名更改商品信息
    int updateByPrimaryKey(Integer pid, String pname, double marketPrice, String image ,String pdesc,Integer pnum);
}
