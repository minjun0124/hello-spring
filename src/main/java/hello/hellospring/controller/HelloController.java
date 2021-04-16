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
    @ResponseBody   // -> HttpMessageConverter/StringConverter
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // hello
    }

    // API 방식 / json format
    // http://localhost:8080/hello-api/?name=spring!!?&age=12
    @GetMapping("hello-api")
    @ResponseBody   // -> HttpMessageConverter/JsonConverter
    public Hello helloApi(@RequestParam("name") String name, @RequestParam("age") String age){
        Hello hello = new Hello();
        hello.setName(name);
        hello.setAge(age);
        return hello;
    }

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
