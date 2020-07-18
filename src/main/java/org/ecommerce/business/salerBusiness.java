package org.ecommerce.business;

import org.ecommerce.dto.Msg;
import org.ecommerce.entity.category;
import org.ecommerce.entity.product;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface salerBusiness {
    //查找全部目录
    List<category> selectAllCate();
    //减少商品：根据所给商品名删除商品
    int deleteProByPrimaryKey(Integer pid);
    //根据所给目录，商品名更改商品信息
    int updateByPrimaryKey(Integer pid, String pname, double marketPrice, String image ,String pdesc,Integer pnum);
    //查询商品分页信息
    Msg productInfo(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException;
    //增加商品
    void producAdd(MultipartFile file, HttpServletRequest request) throws IOException;
}
