package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") //url Mapping
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); //Model에 데이터 key-value로 데이터 담음
        return "hello"; // hello.html 파일로 넘김, View로 전환
    }

    @GetMapping("hello-mvc") //url Mapping
    public String helloMvc(@RequestParam("name") String name, Model model) {
        //@RequestParam은 쿼리 파라미터를 받아옴

        model.addAttribute("name", name);
        return "hello-template"; //hello-template.html 파일로 넘김, View로 전환
    }

    @GetMapping("hello-string")
    @ResponseBody //View를 거치지 않고, HTTP Body에 문자 내용을 직접 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //api방식, HTTP body에 객체를 반환함(json형식으로 반환됨)
    }
    /*
    * @ResponseBody
    * HTTP Body에 문자 내용을 직접 반환
    * viewResolver 대신에 HttpMessageConverter가 동작함
    * 기본 문자처리: StringHttpMessageConverter
    * 기본 객체처리: MappingJackson2HttpMessageConverter
    * byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음
    * */

    static class Hello {
       private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
