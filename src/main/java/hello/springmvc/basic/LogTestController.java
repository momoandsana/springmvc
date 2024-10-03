package hello.springmvc.basic;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // return "ok" 같이 하면 string 을 그대로 화면 소스에 넣어버려. jsp 같은 파일로 가는게 아니라
public class LogTestController {
//    private final Logger log= LoggerFactory.getLogger(getClass());
    // 내 클래스 지정, 롬복 sl4j 어노테이션 쓰면 이거 안 써도 돼

    @RequestMapping("/log-test")
    public String logTest()
    {
        String name="Spring";

        log.trace("trace log={}",name);
        log.debug("debug log={}", name);
        log.warn("warn log={}",name);
        log.error("error log={}",name);
        log.info("info log={}",name);

        /*
         운영서버에서는 info, warning, error 만 사용
         개발할 때만 trace debug 사용
         application.properties 에서 로그레벨 변경 가능
        */


        return "ok";
    }

}
