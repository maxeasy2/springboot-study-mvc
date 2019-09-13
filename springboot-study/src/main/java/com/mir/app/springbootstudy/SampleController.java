package com.mir.app.springbootstudy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/sampleView")
    public String sampleView(Model model){
        model.addAttribute("name","sample");
        return "sampleView";
    }
}
