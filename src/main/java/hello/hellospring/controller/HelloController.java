package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        // key : data / value : hello!!
        model.addAttribute("data", "hello!!");
        return "hello";     // templates/hello.html
    }

    @GetMapping("hello-mvc")    // default : required = true -> parameter를 안주면 error
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   // -> HttpMessageConverter/StringConverter // HTTP의 BODY부에 리턴 내용 직접 맵핑
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // "hello spring"
    }

    // API 방식 / json format
    // Json 반환이 Default
    // http://localhost:8080/hello-api/?name=spring!!?&age=12
    // HTTP Accep 헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합 -> HttpMessageConverter 선택
    @GetMapping("hello-api")
    @ResponseBody   // -> HttpMessageConverter/JsonConverter
    public Hello helloApi(@RequestParam("name") String name, @RequestParam("age") String age){
        Hello hello = new Hello();
        hello.setName(name);
        hello.setAge(age);
        return hello;
    }

    // property 접근 방식
    // JAVA bean 표준 규약
    // parameter는 private으로 접근은 getter/setter
    static class Hello{
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
