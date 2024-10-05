package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.color.ICC_ColorSpace;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}",username,age);

        response.getWriter().write("ok");
    }

    @ResponseBody // 이거만 restcontroller 처럼 쓰고 싶다면
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge)
    {
        log.info("username={}, age={}",memberName,memberAge);
        return "ok"; // restcontroller 어노테이션이 없으면 ok 라는 뷰를 찾게 된다. 문자 그대로를 전송하고 싶으면 restcontroller 어노테이션이 필요하다
    }

    /*
    @RequestParam 은 request.getParameter("") 하는 것과 동일한 효과를 가진다
     */


    /*
    v3,v4 의 이름 생략 방법은 이제 안 먹힌다
     */
    @ResponseBody // 이거만 restcontroller 처럼 쓰고 싶다면, 변수 이름 필수
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age)
    {
        log.info("username={}, age={}",username, age);
        return "ok"; // restcontroller 어노테이션이 없으면 ok 라는 뷰를 찾게 된다. 문자 그대로를 전송하고 싶으면 restcontroller 어노테이션이 필요하다
    }

    @ResponseBody // 이거만 restcontroller 처럼 쓰고 싶다면, 변수 이름 필수
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age)
    {
        log.info("username={}, age={}",username,age);
        return "ok"; // restcontroller 어노테이션이 없으면 ok 라는 뷰를 찾게 된다. 문자 그대로를 전송하고 싶으면 restcontroller 어노테이션이 필요하다
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(value="username",required=true) String username,// 없으면 400 에러
            @RequestParam(value="age",required = false) Integer age)// 없으면 500 에러, 값이 없으면 null 이 들어가야 하는데 int 에는 Null 이 없어서, Integer 로 써야 함
    {          // 기본값이 required=true 임
        log.info("username={},age={}",username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(value="username",required=true,defaultValue="guest") String username,// 없으면 400 에러
            @RequestParam(value="age",required = false,defaultValue="1") int age)// 없으면 500 에러, 값이 없으면 null 이 들어가야 하는데 int 에는 Null 이 없어서, Integer 로 써야 함
    {          // 기본값이 required=true 임
        log.info("username={},age={}",username,age);
        return "ok";
    }// default 는 빈 문자도 처리해줌(빈 문자 받으면 default 값으로)


    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamDefault(@RequestParam Map<String,Object> paramMap)
    {
        log.info("username={}, age={}",paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }
}
