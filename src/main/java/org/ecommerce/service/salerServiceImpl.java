package org.ecommerce.service;

import org.ecommerce.dao.categoryDao;
import org.ecommerce.dao.productDao;
import org.ecommerce.entity.category;
import org.ecommerce.entity.product;
import org.ecommerce.exception.ecommerceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
//产品销售商
@Service
public class salerServiceImpl implements salerService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private categoryDao categoryDao;
    @Resource
    private productDao productDao;

    /**
     * 查找全部目录
     * @return
     */
    @Override
    public List<category> selectAllCate() {
        return categoryDao.selectAll();
    }

    /**
     * 插入商品信息
     * @param pname
     * @param marketPrice
     * @param image
     * @param pdesc
     * @param pnum
     * @return
     */
    @Override
    public int insertPro(String pname, double marketPrice, String image, String pdesc, Integer pnum) {
        int result= productDao.insert(pname,marketPrice,image,pdesc,pnum);
        return result;
    }

    /**
     * 根据商品id删除商品
     * @param pid
     * @return
     */
    @Override
    public int deleteProByPrimaryKey(Integer pid) {
        return (productDao.deleteByPrimaryKey(pid));
    }

    /**
     * 根据商品种类名称搜索商品
     * @param pname
     * @return
     */
    @Override
    public List<product> selectProBypname(String pname) {
        List<product> products=productDao.selectBypname(pname);
        return products;
    }

    /**
     * 更新商品信息
     * @param pid
     * @param pname
     * @param marketPrice
     * @param image
     * @param pdesc
     * @param pnum
     * @return
     */
    @Override
    public int updateByPrimaryKey(Integer pid, String pname, double marketPrice, String image, String pdesc, Integer pnum) {
        return (productDao.updateByPrimaryKey(pid,pname,marketPrice,image,pdesc,pnum));
    }
}
