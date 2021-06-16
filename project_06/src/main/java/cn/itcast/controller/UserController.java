package cn.itcast.controller;

import cn.itcast.domin.User;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    * 登录
    * */
    @RequestMapping("/login")
    @ResponseBody
    public User login (@RequestBody User user) {
        System.out.println(user.getUsername());
        User user1 = userService.checkUsernameAndPassword(user.getUsername(), user.getPassword());

        return user1;
    }
    /*
    注册
    */
    @RequestMapping("/register")
    @ResponseBody
    public User register (@RequestParam(value= "registerUsername") String registerUsername, @RequestParam(value= "registerPassword") String registerPassword,@RequestParam(value= "registerWellPassword") String registerWellPassword) {
        System.out.println(registerUsername);
        System.out.println(registerPassword);
        System.out.println(registerWellPassword);
        Boolean user = userService.IsUsername(registerUsername);
        System.out.println(user);
        if (user == true && registerPassword.equals(registerWellPassword)) { //true:用户不存在,两次密码一样
            User user2 = new User();
            user2.setUsername(registerUsername);
            user2.setPassword(registerPassword);
            userService.add(user2);
            return user2;
        }
        return null;
    }
    @RequestMapping("/pageJump")
    public String pageJump (String str) {
        return str;
    }






}
