package com.mir.app.springbootstudy;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Autowired
    private WebClient webClient;

    @Test
    public void sampleViewTest() throws Exception{
        mockMvc.perform(get("/sampleView"))
                .andExpect(status().isOk())
                .andDo(print()) // -> jsp가 아니기때문에 서블릿엔진을 타지않아 렌더링결과를 확인 할 수 있음
                .andExpect(view().name("sampleView"))
                .andExpect(model().attribute("name",is("sample")))
                .andExpect(content().string(containsString("sample")));
    }


    @Test
    public void sampleViewTestForHtmlUnit() throws Exception{
        HtmlPage page = webClient.getPage("/sampleView");
        HtmlHeading1 h1 = page.getFirstByXPath("//h1");
        assertThat(h1.getTextContent()).isEqualToIgnoringCase("sample");
    }
}
