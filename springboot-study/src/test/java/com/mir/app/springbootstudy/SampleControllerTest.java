package com.mir.app.springbootstudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void sampleViewTest() throws Exception{
        mockMvc.perform(get("/sampleView"))
                .andExpect(status().isOk())
                .andDo(print()) // -> jsp가 아니기때문에 서블릿엔진을 타지않아 렌더링결과를 확인 할 수 있음
                .andExpect(view().name("sampleView"))
                .andExpect(model().attribute("name",is("sample")))
                .andExpect(content().string(containsString("sample")));
    }
}
