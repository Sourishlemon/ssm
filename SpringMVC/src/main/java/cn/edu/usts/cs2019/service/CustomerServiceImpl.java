package cn.edu.usts.cs2019.service;

import cn.edu.usts.cs2019.dao.base.ICustomerDao;
import cn.edu.usts.cs2019.dao.base.IUserDao;
import cn.edu.usts.cs2019.entity.Customer;
import cn.edu.usts.cs2019.entity.User;
import cn.edu.usts.cs2019.service.base.ICustomerService;

import java.util.List;

public class CustomerServiceImpl implements ICustomerService {

    private ICustomerDao dao;    // DAO对象，采用接口的引用

    public void setDao(ICustomerDao dao) {
        this.dao = dao;
    }

    // 构造方法，初始化dao属性
    public CustomerServiceImpl(ICustomerDao dao) {
        this.dao = dao;
    }

    /**
     * @param customer
     * @Description: 用户登录功能
     * @Param: 请求的用户User对象
     * @return: 存在返回指定的用户对象(封装其他信息)，失败返回null.
     */
    @Override
    public Customer login(Customer customer) {
        return dao.login(customer);
    }

    /**
     * @param list
     * @Description: 批量增加用户信息, 如数据从外部(文件)导入。
     * @Param: [list]增加的用户对象列表.
     * @return: 成功true.
     */
    @Override
    public boolean insert(List<Customer> list) {
        return dao.insert(list);
    }

    /**
     * 新增实体对象
     *
     * @param entity 实体对象
     * @return 成功true，失败false
     */
    @Override
    public boolean insert(Customer entity) {
        return dao.insert(entity);
    }

    /**
     * 更新实体对象
     *
     * @param entity
     * @return 成功true，失败false
     */
    @Override
    public boolean update(Customer entity) {
        return dao.update(entity);
    }

    /**
     * 删除实体对象
     *
     * @param entity 实体对象
     * @return 成功true，失败false
     */
    @Override
    public boolean delete(Customer entity) {
        return dao.delete(entity);
    }

    /**
     * 通过id查询，返回唯一查询对象
     *
     * @param id 对象的id
     * @return 对象T
     */
    @Override
    public Customer findByID(Integer id) {
        return dao.findByID(id);
    }

    /**
     * 保存或更新实体对象
     *
     * @param entity 实体对象
     * @return 成功true，失败false
     */
    @Override
    public boolean saveOrUpdate(Customer entity) {
        return dao.saveOrUpdate(entity);
    }

}
