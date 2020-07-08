package org.ecommerce.dao;

import org.apache.ibatis.annotations.Param;
import org.ecommerce.entity.category;

import java.util.List;

public interface categoryDao {
    //查询所有目录商品
    List<category> selectAll(@Param("offset") int offset, @Param("limit")int limit);
}
