package org.ecommerce.service;

import org.ecommerce.dao.categoryDao;
import org.ecommerce.dao.productDao;
import org.ecommerce.entity.category;
import org.ecommerce.entity.product;
import org.ecommerce.exception.ecommerceException;
import org.ecommerce.exception.salerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
@Service
public class salerServiceImpl implements salerService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private categoryDao categoryDao;
    @Resource
    private productDao productDao;

    @Override
    public int insertCate(String cname) {
        return categoryDao.insert(cname);
    }

    @Override
    public int deleteCateBycname(String cname) {
        int cateDelete=categoryDao.deleteBycname(cname);
        List<product> productByName=productDao.selectBypname(cname);
        int productDelete=1;
        if(productByName.size()!=0){
            productDelete=productDao.deleteBypname(cname);
        }
        return (productDelete&cateDelete);
    }

    @Override
    public int updateCateByPrimaryKey(Integer cid, String cname){
        category category=categoryDao.selectBycid(cid);
        String orginName=category.getCname();
        int cateUpdate=categoryDao.updateByPrimaryKey(cid, cname);
        List<product> productByName=productDao.selectBypname(orginName);
        int productUpdate=1;
        if(productByName.size()!=0){
            Iterator productChange=productByName.iterator();
            while (productChange.hasNext()){
                product nowProduct=(product) productChange.next();
                Integer pid=nowProduct.getPid();
                productUpdate=productUpdate&(productDao.updateNameByPrimaryKey(pid,cname));
            }
        }
        return (cateUpdate&productUpdate);
    }

    @Override
    public List<category> selectAllCate() {
        return categoryDao.selectAll();
    }

    @Override
    public int insertPro(String pname, double marketPrice, String image, String pdesc, Integer pnum) {
        try{
            int insertProCount=0;
            if(productDao.countProducyAll()<=10){
                insertProCount= productDao.insert(pname,marketPrice,image,pdesc,pnum);
            }
            else {
                throw new salerException("商品数目超限");
            }
            return insertProCount;
        }
        catch (salerException e1){
            throw e1;
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new ecommerceException("内部错误   "+e.getMessage());
        }
    }

    @Override
    public int deleteProByPrimaryKey(Integer pid) {
        return (productDao.deleteByPrimaryKey(pid));
    }

    @Override
    public int updateProNumByPrimaryKey(Integer pid, Integer pnum) {
        try{
            if(pnum<0)
                throw new salerException("商品数量设置错误");
            else
                return (productDao.updateNumByPrimaryKey(pid, pnum));
        }
        catch (salerException e1){
            throw e1;
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new ecommerceException("内部错误   "+e.getMessage());
        }
    }

    @Override
    public List<product> selectProBypname(String pname) {
        try{
            List<product> products=productDao.selectBypname(pname);
            if(products.size()==0){
                throw new salerException("不存在该分类的商品");
            }
            else {
                return products;
            }
        }
        catch (salerException e1){
            throw e1;
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new ecommerceException("内部错误   "+e.getMessage());
        }
    }

    @Override
    public int updateByPrimaryKey(Integer pid, String pname, double marketPrice, String image, String pdesc, Integer pnum) {
        return (productDao.updateByPrimaryKey(pid,pname,marketPrice,image,pdesc,pnum));
    }
}
