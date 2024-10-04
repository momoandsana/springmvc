package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.awt.color.ICC_ColorSpace;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    /*
        RequestMapping 2개도 가능
        /hello-basic, /hello-basic/ 모두 가능
     */
//    @RequestMapping({"/hello-basic","/hello-go"})
    @RequestMapping(value="/hello-basic", method= RequestMethod.GET)
    public String helloBasic()
    {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션 (코드보기)
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value="/mapping-get-v2")
    public String mappingGetV2()
    {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable String userId
     * ? 를 쓰는 쿼리파라미터 방식과 기능은 같음
     * 경로 변수와 메서드 파라미터를 다르게 사용하고 싶으면 밑에 처럼 쓰기
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data)
    {
        log.info("mappingPath userId={}",data);
        return "ok";
    }

//    경로 변수와 메서드 파라미터가 같기 때문에 밑에 경우도 가능함
//    @GetMapping("/mapping/{userId}")
//    public String mappingPath(@PathVariable String userId)
//    {
//        log.info("mappingPath userId={}",userId;
//        return "ok";
//    }

    /**
     * PathVariable 사용 다중
     * 여기에서는 경로 변수와 파라미터가 같아도 @PathVariable("userId") 설정하기
     * http://localhost:8080/mapping/users/userA/orders/100
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable("userId") String userId, @PathVariable("orderId") Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * 특정 파라미터 정보가 있어야지 매핑해줌
     *
     * http://localhost:8080/mapping-param?mode=debug
     *
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value="/mapping-param",params="mode=debug")
    public String mappingParam()
    {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     *
     * Headers 에서 키와 값을 여기서 설정한대로 넣어주기
     *
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value="/mapping-header",headers="mode=debug")
    public String mappingHeader()
    {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * 위에 방법보다 더 좋은 방법
     * json 일 때만 호출
     * 서버가 본인이 받고 싶은 형식 지정
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value="/mapping-consume",consumes="application/json")
    public String mappingConsumes()
    {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * 클라이언트가 받고 싶은 형식 지정
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value="/mapping-produce",produces="text/html")
    public String mappingProduces()
    {
        log.info("mappingProduces");
        return "ok";
    }

}
