package lyh.Logon.Control;/*
 * @Auther:刘宇航
 * @Date:2023/4/4
 * @VERSON:1.0
 */

import lyh.Logon.DAO.UserDAO;
import lyh.Logon.Model.User;
import lyh.Logon.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserControl  {
    @Autowired
    private UserDAO userDAO;
    private UserService userService;

    @RequestMapping("/userMain")
    public String userMain(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("userInfo");
        model.addAttribute("user",user);

        return "userMain";

    }

    //访问登陆界面
    @RequestMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }
    //访问注册界面
    @RequestMapping("/register")
    public String sign(Model model) {
        User user = new User();
        model.addAttribute("user",user);

        return "sign";
    }





//
//    @RequestMapping("/main")
//    public String userMain() {
//
//    }
    //访问主界面




//    //登陆
//    @PostMapping("/is_login")
//    @ResponseBody
//    public Map  getUser(@RequestParam("name")String name,@RequestParam("pwd") String pwd) {
//        Map data = new HashMap<>();
//        UserDO userDO = userDAO.getUser(name,pwd);
//        if (userDO == null) {
//            data.put("result","用户名或密码错误");
//            return data;
//        }
//        data.put("result","登陆成功");
//        return  data;
//
//
//    }
//
//
//
//
//
//    @PostMapping("/reg")
//    @ResponseBody
//    public Map reg(@RequestParam("name")String name,@RequestParam("pwd")String pwd) {
//        Map data = new HashMap<>();
//        if (StringUtils.isEmpty(name)) {
//            data.put("userName","用户名不能为空");
//            return data;
//        }
//        if (StringUtils.isEmpty(pwd)) {
//            data.put("userPwd","密码不能为空");
//            return data;
//        }
//        //根据用户名查找是否存在
//        UserDO userDO = userDAO.getUserByName(name);
//        if (userDO != null) {
//            data.put("userName","用户名已存在");
//            return data;
//        }
//        UserDO userDO1 = new UserDO();
//        userDO1.setUserName(name);
//        userDO1.setUserPwd(pwd);
//        //进行注册
//        userDAO.add(userDO1);
//
//            data.put("info","注册成功");
//
//
//        return data;
//
//
//    }



}
