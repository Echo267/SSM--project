package lyh.Logon.Model;/*
 * @Auther:刘宇航
 * @Date:2023/4/4
 * @VERSON:1.0
 */

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 用户类
 */
public class User {

    private int id;
    @NotEmpty(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "用户名必须为英文字母")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*_)[A-Za-z\\d_]{8,}$", message = "密码必须包含字母、数字和下划线，长度至少为8位")
    private String userPwd;


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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }
}
