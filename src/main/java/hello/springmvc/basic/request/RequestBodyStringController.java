package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.standard.inline.StandardTextInliner;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response)throws IOException
    {
        ServletInputStream inputStream=request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// Stream 은 바이트 코드이므로 항상 인코딩이 필요함

        log.info("meesageBody={}",messageBody);

        response.getWriter().write("ok");

    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter)throws IOException
            // HttpServletRequest 나 HttpServletResponse 대신 InputStream & responseWriter 받을 수 있음
    {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// Stream 은 바이트 코드이므로 항상 인코딩이 필요함

        log.info("meesageBody={}",messageBody);

        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity)throws IOException
    {
        String messageBody=httpEntity.getBody();

        log.info("meesageBody={}",messageBody);

        return new HttpEntity<>("ok");
    }
    /*
    요청이 올 때는 인수로 HttpEntity(RequestEntity)
    그 요청에 대한 응답을 할 때는 HttpEntity(ResponseEntity)를 반환
     */

//    @PostMapping("/request-body-string-v3")
//    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity)throws IOException
//    {
//        String messageBody=httpEntity.getBody();
//
//        log.info("meesageBody={}",messageBody);
//
//        return new ResponseEntity<String>("ok", HttpStatus.CREATED);// 상태코드 201을 넣어서 전송
//    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody)throws IOException
    {
        log.info("meesageBody={}",messageBody);

        return "ok";// 바로 @ResponseBody 를 사용해서 바로 이런 형식으로 내보낸다
    }
    /*

     */

}
