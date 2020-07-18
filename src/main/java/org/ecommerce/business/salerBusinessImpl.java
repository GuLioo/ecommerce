package org.ecommerce.business;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ecommerce.dto.Msg;
import org.ecommerce.entity.category;
import org.ecommerce.entity.product;
import org.ecommerce.service.adminService;
import org.ecommerce.service.salerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

//产品销售商
@Service
public class salerBusinessImpl implements salerBusiness {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private org.ecommerce.service.salerService salerService;

    /**
     * 查询全部目录信息
    * @return
     */
    @Override
    public List<category> selectAllCate() {
        return salerService.selectAllCate();
    }

    /**
     * 根据商品id删除商品
     * @param pid
     * @return
     */
    @Override
    public int deleteProByPrimaryKey(Integer pid) {
        return salerService.deleteProByPrimaryKey(pid);
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
        return salerService.updateByPrimaryKey(pid,pname,marketPrice,image,pdesc,pnum);
    }

    /**
     * 查询商品分页信息
     * @param req
     * @param resp
     * @return
     * @throws UnsupportedEncodingException
     */
    @Override
    public Msg productInfo(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        // 设置后台响应文本格式
        resp.setContentType("text/html;charset=utf-8");
        // 接收前台请求
        String pname = URLDecoder.decode(req.getParameter("name"),"UTF-8");
        String pnString=req.getParameter("pn");
        Integer pn=Integer.valueOf(pnString);
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 2);
        List<product> product= salerService.selectProBypname(pname);
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        //pageInfo里面封装了分页的详细信息，包括有我们查询出来的数据,页码导航传入连续显示的页数5
        PageInfo page = new PageInfo(product,5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 根据提交表单信息增加商品
     * @param file
     * @param request
     * @throws IOException
     */
    @Override
    public void producAdd(MultipartFile file, HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String name = multipartRequest.getParameter("cate");
        String price = multipartRequest.getParameter("price");
        double pPrice=Double.parseDouble(price);
        String description = multipartRequest.getParameter("desc");
        String num = multipartRequest.getParameter("num");
        Integer pNum=Integer.parseInt(num);
        /**
         * 上传图片
         */
        //图片上传成功后，将图片的地址写到数据库
        String filePath = "C:\\Users\\谷粒\\Desktop\\coding\\ecommerce\\src\\main\\webapp\\resource\\images\\pic";//保存图片的路径,tomcat中有配置
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        //新的文件名字，使用uuid随机生成数+原始图片名字，这样不会重复
        String newFileName =originalFilename;
        //全路径：硬盘路径+文件名
        File targetFile = new File(filePath,newFileName);
        //本地文件传到封装好的全路径
        file.transferTo(targetFile);

        //增加产品信息
        int result=salerService.insertPro(name,pPrice,newFileName,description,pNum);
    }
}
