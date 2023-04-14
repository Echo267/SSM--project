package lyh.Logon.api;/*
 * @Auther:刘宇航
 * @Date:2023/4/5
 * @VERSON:1.0
 */




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lyh.Logon.Model.Result;
import lyh.Logon.Model.User;
import lyh.Logon.Service.UserService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/user/API")
public class UserAPI {

    private static final Logger LOG = LoggerFactory.getLogger(UserAPI.class);
    @Autowired
    private UserService userService;


    /**
     * 用户注册
     * @param user 用户类
     * @return
     */
    @PostMapping("/reg")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult errors, Model model) {

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            return "sign";
        }

        Result<User> userResult =userService.register(user.getUserName(),user.getUserPwd());

        //判断是否成功
        if (userResult.isSuccess()) {

            //登陆成功设置请求的响应状态码为202
            return "login";

        }
        model.addAttribute("isSuccess",userResult.isSuccess());

        return "sign";

    }

    /**
     * 登陆逻辑判断
     * @param user
     * @param errors
     * @param
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("user") User user, BindingResult errors,
                                              Model model, HttpServletRequest request) {
        Result<User> userResult = new Result<>();

        if (errors.hasErrors()){

            for (ObjectError error : errors.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            userResult.setSuccess(false);
            model.addAttribute("isSuccess",userResult.isSuccess());

            return "login";

        } else {

            userResult = userService.login(user.getUserName(),user.getUserPwd());

            //判断是否成功
            if (userResult.isSuccess()) {
                HttpSession session = request.getSession();
                session.setAttribute("userInfo",userResult.getData());

                model.addAttribute("isSuccess",userResult.isSuccess());

                System.out.println(userResult.getData());
                //登陆成功设置请求的响应状态码为202
                new ResponseEntity<>(userResult, HttpStatus.valueOf(202));
                return "/userMain";
            }
            else {
                //登陆失败设置请求的响应状态码为404
                new ResponseEntity<>(userResult, HttpStatus.valueOf(404));
                model.addAttribute("isSuccess",false);
                return "login";
            }
        }



    }
//    @PostMapping("/login")
//    public ResponseEntity<Result<User>> login(@Valid @ModelAttribute("user") User user, BindingResult errors,
//                                              HttpServletResponse response, HttpServletRequest request) {
//        Result<User> userResult = new Result<>();
//
//        if (errors.hasErrors()){
//
//            userResult.setSuccess(false);
//
//        } else {
//
//            userResult = userService.login(user.getUserName(),user.getUserPwd());
//
//            //判断是否成功
//            if (userResult.isSuccess()) {
//                HttpSession session = request.getSession();
//                session.setAttribute("userInfo",userResult.getData());
//            //登陆成功设置请求的响应状态码为202
//                return new ResponseEntity<>(userResult, HttpStatus.valueOf(202));
//            }
//        }
//
//        //登陆失败设置请求的响应状态码为404
//        return new ResponseEntity<>(userResult,HttpStatus.valueOf(404));
//
//    }

    /**
     * 用户退出登陆
     * @param request
     * @return 返回登陆页面
     */
    @GetMapping("/logout")
    public RedirectView logout(  HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.removeAttribute("userInfo");
        return new RedirectView("/user/login");

    }


}
