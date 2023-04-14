package lyh.Logon.DAO;/*
 * @Auther:刘宇航
 * @Date:2023/4/4
 * @VERSON:1.0
 */

import lyh.Logon.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDAO {

    //添加用户
     int add(UserDO userDO);
    //根据用户名和密码查询用户信息
    UserDO getUser(@Param("name")String name,@Param("pwd")String pwd);
    //根据用户名查找用户
     UserDO getUserByName(@Param("name") String name);


}
