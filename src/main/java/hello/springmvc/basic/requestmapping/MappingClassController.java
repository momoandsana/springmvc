package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

/**
 * 회원 목록 조회: GET `/users`
 * 회원 등록: POST `/users`
 * 회원 조회: GET `/users/{userId}`
 * 회원 수정: PATCH `/users/{userId}`
 * 회원 삭제: DELETE `/users/{userId}`
 */

@RestController

public class MappingClassController {
    // 회원 목록 조회
    @GetMapping("/mapping/users")
    public String users(){
        return "get users";
    }
    // 회원 등록
    @PostMapping("/mapping/users")
    public String addUser()
    {
        return "post user";
    }
    // 개별 회원 조회
    @GetMapping("/mapping/users/{userId}")
    public String findUser(@PathVariable("userId") String userId)
    {
        return "get userId="+userId;
    }
    // 회원 정보 수정
    @PatchMapping("/mapping/users/{userId}")
    public String updateUser(@PathVariable("userId") String userId)
    {
        return "update userId="+userId;
    }

    // 회원 삭제
    @DeleteMapping("/mapping/users/{userId}")
    public String deleteUser(@PathVariable("userId") String userId)
    {
        return "delete userId="+userId;
    }
}
