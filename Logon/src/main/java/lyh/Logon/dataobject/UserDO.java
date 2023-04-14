package lyh.Logon.dataobject;/*
 * @Auther:刘宇航
 * @Date:2023/4/4
 * @VERSON:1.0
 */

import lyh.Logon.Model.User;

import javax.validation.constraints.NotEmpty;


public class UserDO {

    private int id;//id
    //@NotEmpty(message = "用户名不能为空")
    private String userName;//用户名
    //@NotEmpty(message = "密码不能为空")
    private String userPwd;//密码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    //把UserDO转成User类
    public User toModel() {
        User user = new User();
        user.setId(getId());
        user.setUserName(getUserName());
        user.setUserPwd(getUserPwd());
        return user;
    }

}
