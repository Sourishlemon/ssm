package cn.edu.usts.cs2019.dao;

import cn.edu.usts.cs2019.dao.base.IUserDao;
import cn.edu.usts.cs2019.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaWebApp
 * @description: User类的Dao实现
 * @author: Mr.Lu, DCE, USTS.
 * @create: 2020-03-07 15:45
 **/
public class UserDaoImpl implements IUserDao {

    private List<User> list = new ArrayList<User>();
    /**
     * Description: 构造方法
     */
    public UserDaoImpl() {
        // 专为内存数据对象设计：初始化
        if(list == null) {
            list = new ArrayList<User>();
        }
            list.add(new User(201,"admin","admin123"));
    //        list.add(new User(201,"admin","admin321", "13913013200",
//                "东方大道3099号", new Date(2001,12,3),"信息中心"));
    }

    /**
     * @description:
     * @param: [user]
     * @return: 成功，返回 cn.edu.usts.cs2018.entity.User对象，否则为null.
     */
    @Override
    public User login(User user) {
        User result = null;

        for( User u : list) {
            if(u.getName().equals(user.getName()) &&
                    u.getPassword().equals(user.getPassword())) {
                result = u;
            }
        }

        return result;
    }

    // 批量插入数据
    @Override
    public boolean insert(List<User> list) {
        boolean result = true ;
        for ( User user : list ) {
            if(! insert(user)) {  // 失败
                result = false;
                break;
            }
        }
        return result;
    }

    // 插入数据
    @Override
    public boolean insert(User entity) {
        boolean flag = false;

        for(User u : list){
            if(u.getName().equals(entity.getName()))
            {
                flag = true;
                break;
            }
        }
        if(!flag)
            list.add(entity);

        return !flag;

    }

    // 更新
    public boolean update(User entity) {
        return saveOrUpdate(entity) ;
    }

    // 删除
    @Override
    public boolean delete(User entity) {


        return false;
    }

    @Override
    public User findByID(Integer integer) {

        return null;
    }

    @Override
    public boolean saveOrUpdate(User user) {
        boolean flag = false;

        for(User u : list){
            if(u.getId() == (user.getId()))  // 查找指定的对象，找到进行更新。
            {
                flag = true;
                u.setName( user.getName());
                break;
            }
        }

        return flag;

    }

    // 其他未实现的方法
    //

}
