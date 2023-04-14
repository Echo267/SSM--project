package lyh.Logon.Service;/*
 * @Auther:刘宇航
 * @Date:2023/4/5
 * @VERSON:1.0
 */


import lyh.Logon.Model.Result;
import lyh.Logon.Model.User;

public interface UserService {
    /**
     * 注册用户
     * @param userName
     * @param pwd
     * @return
     */
    public Result<User> register(String userName, String pwd);
    /**
     * 执行登录逻辑，登录成功返回 User 对象
     *
     * @param userName
     * @param pwd
     * @return
     */
    public Result<User> login(String userName, String pwd);

}
