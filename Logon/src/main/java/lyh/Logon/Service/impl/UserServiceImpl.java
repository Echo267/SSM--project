package lyh.Logon.Service.impl;/*
 * @Auther:刘宇航
 * @Date:2023/4/5
 * @VERSON:1.0
 */

import lyh.Logon.DAO.UserDAO;
import lyh.Logon.Model.Result;
import lyh.Logon.Model.User;
import lyh.Logon.Service.UserService;
import lyh.Logon.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Component
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDAO userDAO;

    //短代码的详细解释
    private static final String MESSAGE_600 = "用户名为空";
    private static final String MESSAGE_601 = "密码为空";
    private static final String MESSAGE_602 = "用户名已存在";
    private static final String MESSAGE_603 = "密码不正确";


    @Override//注册
    public Result<User> register(String userName, String pwd) {
        Result<User> userResult = new Result<>();
        if (StringUtils.isEmpty(userName)) {
            userResult.setCode("600");
            userResult.setMessage(MESSAGE_600);
            return userResult;
        }
        if (StringUtils.isEmpty(pwd)) {
            userResult.setCode("601");
            userResult.setMessage(MESSAGE_601);
            return userResult;
        }
        UserDO userDO = userDAO.getUserByName(userName);
        if (userDO != null) {
            userResult.setCode("602");
            userResult.setMessage(MESSAGE_602);
            return userResult;
        }

        UserDO userDO1 = new UserDO();
        userDO1.setUserName(userName);
        userDO1.setUserPwd(pwd);
        userDAO.add(userDO1);//添加入数据库
        userResult.setSuccess(true);
        //userDAO->User用来存储用户信息
        User user = userDO1.toModel();
        //存储用户记录
        userResult.setData(user);


        return userResult;
    }

    @Override//登陆
    public Result<User> login(String userName, String pwd) {
        Result<User> userResult = new Result<>();
        if (StringUtils.isEmpty(userName)) {
            userResult.setCode("600");
            userResult.setMessage(MESSAGE_600);
            return userResult;
        }
        if (StringUtils.isEmpty(pwd)) {
            userResult.setCode("601");
            userResult.setMessage(MESSAGE_601);
            return userResult;
        }
        //根据用户名判断
        UserDO userDO = userDAO.getUserByName(userName);
        if (userDO == null) {
            userResult.setCode("602");
            userResult.setMessage(MESSAGE_602);
            return userResult;
        }
        if (!StringUtils.equals(userDO.getUserPwd(),pwd)) {
            userResult.setCode("603");
            userResult.setMessage(MESSAGE_603);
            return userResult;
        }

            userResult.setSuccess(true);

            // 将 UserDO 对象转化为 User 对象
            User user = userDO.toModel();
            userResult.setData(user);
            return userResult;

    }
}
