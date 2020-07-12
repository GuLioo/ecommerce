package org.ecommerce.service;

import org.ecommerce.entity.category;
import org.ecommerce.entity.product;

import java.util.List;

public interface salerService {
    //配置种类
    //增加目录
    int insertCate(String cname);
    //删除目录：则product中相关目录都删除
    int deleteCateBycname(String cname);
    //更改目录名称：则product中原始目录名称都改变
    int updateCateByPrimaryKey(Integer cid, String cname);
    //查找全部目录
    List<category> selectAllCate();
    //配置种类数量
    //增加商品：根据所给目录、商品名、价格、数量blabla 增加
    int insertPro(String pname,double marketPrice,String image,String pdesc,Integer pnum);
    //最多销售10种商品，要判断数量
    //减少商品：根据所给目录、商品名删除商品
    int deleteProByPrimaryKey(Integer pid);
    //更改商品数量：修改商品的pnum属性
    int updateProNumByPrimaryKey(Integer pid ,Integer pnum);
    //找某一种类的商品：根据目录名查找商品
    List<product> selectProBypname(String pname);
    //更改商品信息
    //根据所给目录，商品名更改商品信息
    int updateByPrimaryKey(Integer pid, String pname, double marketPrice, String image ,String pdesc,Integer pnum);
}
