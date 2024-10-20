package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav=new ModelAndView("response/hello")
                .addObject("data","hello!");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV1(Model model){
       model.addAttribute("data","hello! v2");
        return "response/hello"; // String 으로 반환하면서 이렇게 response/hello 하면 뷰로 반환, templates(뷰 템플릿) 경로 기준으로
    }

    @RequestMapping("/response/hello")// 권장 x
    public void responseViewV3(Model model)
    {
        model.addAttribute("data","hello!");
    }
}
