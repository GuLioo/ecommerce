package org.ecommerce.controller;

import java.util.List;

import org.ecommerce.entity.adminUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.github.pagehelper.PageInfo;

/**
 * 使用spring测试模块提供的测试请求功能，测试crud请求的正确性
 * spring4测试的时候，需要servlet3.0的支持
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/spring-dao.xml",  "classpath:spring/spring-service.xml"})
public class pageTest {
    //传入Springmvc的ioc
    @Autowired
    WebApplicationContext context;

    //虚拟mvc请求，获取到处理结果。
    MockMvc mockMvc;

    @Before
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //编写测试分页的方法
    @Test
    public void testPage() throws Exception{
        //模拟请求，拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/ecommerce_war/entrance/adminUserGet").param("pn", "1")).
                andReturn();

        //请求成功以后，请求域中会有pageInfo，可以取出pageInfo进行验证
        MockHttpServletRequest request = result.getRequest();
        PageInfo pi = (PageInfo) request.getAttribute("pageInfo");

        System.out.println("当前页码"+pi.getPageNum());
        System.out.println("总页码"+pi.getPages());
        System.out.println("总记录数"+pi.getTotal());
        System.out.println("在页面中需要连续显示的页码：");
        int[] nums = pi.getNavigatepageNums();
        for (int i : nums) {
            System.out.println(" "+i);
        }

        //获取员工数据
        List<adminUser> list = pi.getList();
        for (adminUser employee : list) {
            System.out.println("auid:"+employee.getAuid()+"===>Name：  "+employee.getUsername());
        }
    }
}
