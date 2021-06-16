package cn.itcast.service.Impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domin.User;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;
    //添加用户
    @Override
    public void add ( User user ) {
        userdao.insert(user);
    }

    //校验用户名和密码
    @Override
    public User checkUsernameAndPassword (String username, String password ) {
        User user = userdao.finbyUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }


        return null;
    }

    //校验用户名是否存在
    @Override
    public boolean IsUsername ( String username ) {

        User user = userdao.finbyUsername(username);
        if (user == null) { //用户不存在
            return true;
        }
        return false;

    }




}
