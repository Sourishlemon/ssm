package cn.itcast.service;

import cn.itcast.domin.User;

public interface UserService {
    //添加用户
    public void  add(User user);

    //校验用户名和密码
    public User checkUsernameAndPassword(String username, String password);


    //判断用户是否存在
    public boolean IsUsername( String username);


}
