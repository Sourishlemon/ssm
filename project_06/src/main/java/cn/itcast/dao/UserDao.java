package cn.itcast.dao;

import cn.itcast.domin.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


public interface UserDao {
    //添加用户
    @Insert("insert into user(username,password) values(#{username},#{password})")
    public int insert ( User user );


    //查询用户
    @Select("select * from user where username=#{username}")
    public User finbyUsername (String username );


}
