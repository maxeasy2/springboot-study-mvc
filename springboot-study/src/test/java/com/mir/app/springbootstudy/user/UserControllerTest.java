package com.mir.app.springbootstudy.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
//@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception{
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void helloCreateTest() throws Exception{
        String jsonData = "{\"username\":\"aaaa\", \"userid\":\"ssss\", \"age\":10}";
        mockMvc.perform(post("/hello/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //요청
                .accept(MediaType.APPLICATION_JSON_UTF8) //응답
                .content(jsonData))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.username",is(equalTo("aaaa"))))
                    .andExpect(jsonPath("$.age",is(equalTo(10))));
    }

    @Test
    public void helloCreateForXmlTest() throws Exception{
        String jsonData = "{\"username\":\"aaaa\", \"userId\":\"ssss\", \"age\":10}";
        mockMvc.perform(post("/hello/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //요청
                .accept(MediaType.APPLICATION_XML) //응답
                .content(jsonData))
                .andExpect(status().isOk())
                .andExpect(xpath("/User/username").string("aaaa"))
                .andExpect(xpath("/User/userId").string("ssss"));
    }
}
