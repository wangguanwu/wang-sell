package com.weixin.sell.wangsell.interceptor;

import com.weixin.sell.wangsell.controller.LoginController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class LoginInterceptorTest {
    @Autowired
    LoginController loginContoller ;
    @Test
    public void preHandle()throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(loginContoller).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/login?username=wang&password=wang"));

    }
}