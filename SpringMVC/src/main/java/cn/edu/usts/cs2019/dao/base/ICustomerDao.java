package cn.edu.usts.cs2019.dao.base;

import cn.edu.usts.cs2019.entity.Customer;

import java.util.List;

/**
 * @program: JavaWebApp
 * @description: User类的Dao接口
 * @author: Mr.Lu, DCE, USTS.
 * @create: 2020-03-07 15:34
 **/
public interface ICustomerDao extends IBaseDao <Customer, Integer>{

    /**
     * @Description: 用户登录功能
     * @Param: 请求的用户User对象
     * @return:  存在返回指定的用户对象(封装其他信息)，失败返回null.
     */
    public Customer login(Customer customer);

    /**
     * @Description: 批量增加用户信息,如数据从外部(文件)导入。
     * @Param: [list]增加的用户对象列表.
     * @return: 成功true.
     */
    public boolean insert(List<Customer> list);
}

