package com.mir.app.springbootstudy;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Controller
public class SampleController {

    @GetMapping("/sampleView")
    public String sampleView(Model model){
        model.addAttribute("name","sample");
        return "sampleView";
    }

    @GetMapping("/exError")
    public String error(){
        throw new SampleException();
    }

    //500에러 html 페이지 확인시 주석후 확인가능
    @ExceptionHandler(SampleException.class)
    public @ResponseBody AppError sampleError(SampleException e){
        AppError appError = new AppError();
        appError.setMessage("error");
        appError.setReason("kek");
        return appError;
    }

    @GetMapping("/hateoas")
    public @ResponseBody Resource<HateoasSample> hateoas() throws Exception{
        HateoasSample sample = new HateoasSample();
        sample.setName("aaaa");
        sample.setName("cccc");

        Resource<HateoasSample> sampleResource = new Resource(sample);
        sampleResource.add(linkTo(methodOn(SampleController.class).hateoas()).withSelfRel());
        return sampleResource;
    }
}
