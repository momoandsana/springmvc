package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello","age":20}
 * content-type : application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {
     private ObjectMapper objectMapper=new ObjectMapper();

     @PostMapping("/request-body-json-v1")
     public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response)throws IOException
     {
         ServletInputStream inputStream=request.getInputStream();
         String messageBody= StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
         // 바이트스트림으로 전송 받음. 변환 필요

         log.info("messageBody={}",messageBody);
         HelloData helloData=objectMapper.readValue(messageBody,HelloData.class);
         log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());

         response.getWriter().write("ok");
     }

     @ResponseBody
     @PostMapping("/request-body-json-v2")
     public String requestBodyJsonV2(@RequestBody String messageBody)throws IOException
     {
         log.info("messageBody={}",messageBody);
         HelloData helloData=objectMapper.readValue(messageBody,HelloData.class);// objectMapper 쓰는 것도 귀찮음
         log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());

        return "ok";// @ResponseBody 때문에 jsp 가 아니라 body 에 그대로 문자가 String 으로 들어간다
     }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV2(@RequestBody HelloData data)throws IOException
    {
        /*
        @RequestBody 에 직접 만든 객체를 지정
        http 메시지 컨버터가 우리가 원하는 객체로 변환해줌
        생성된 helloData 를 다시 잘 넣어준다
         */
        log.info("username={}, age={}",data.getUsername(),data.getAge());

        return "ok";// @ResponseBody 때문에 jsp 가 아니라 body 에 그대로 문자가 String 으로 들어간다
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity)
    {
        HelloData data=httpEntity.getBody();
        log.info("username={},age={}",data.getUsername(),data.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data)
    {
        log.info("username={}, age={}",data.getUsername(),data.getAge());
        return data;// 그냥 String 이 아니라 해당 객체가 json 으로 바뀌고 http 메시지 바디에 바로 넣는 경우
    }

    /*
    @RequestBody 요청
    json 요청 -> http 메시지 컨버터-> 객체

    @ResponseBody
    객체 -> http 메시지 컨버터 -> json 응답
     */


}
