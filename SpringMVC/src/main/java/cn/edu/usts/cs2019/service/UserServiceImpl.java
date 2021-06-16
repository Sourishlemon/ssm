package cn.edu.usts.cs2019.service;

import cn.edu.usts.cs2019.dao.base.IUserDao;
import cn.edu.usts.cs2019.entity.User;
import cn.edu.usts.cs2019.service.base.IUserService;

import java.util.List;

/**
 * @program: JavaWebApp
 * @description: User的IService的实现类
 * @author: Mr.Lu, DCE, USTS.
 * @create: 2020-03-07 15:52
 **/
public class UserServiceImpl implements IUserService {

	private IUserDao dao;    // DAO对象，采用接口的引用

	public void setDao(IUserDao dao) {
		this.dao = dao;
	}

	// 构造方法，初始化dao属性
	public UserServiceImpl(IUserDao dao) {
		this.dao = dao;
	}

	/**
	 * @param user
	 * @Description: 用户登录功能
	 * @Param: 请求的用户User对象
	 * @return: 存在返回指定的用户对象(封装其他信息)，失败返回null.
	 */
	@Override
	public User login(User user) {
		return dao.login(user);
	}

	/**
	 * @param list
	 * @Description: 批量增加用户信息, 如数据从外部(文件)导入。
	 * @Param: [list]增加的用户对象列表.
	 * @return: 成功true.
	 */
	@Override
	public boolean insert(List<User> list) {
		return dao.insert(list);
	}

	/**
	 * 新增实体对象
	 *
	 * @param entity 实体对象
	 * @return 成功true，失败false
	 */
	@Override
	public boolean insert(User entity) {
		return dao.insert(entity);
	}

	/**
	 * 更新实体对象
	 *
	 * @param entity
	 * @return 成功true，失败false
	 */
	@Override
	public boolean update(User entity) {
		return dao.update(entity);
	}

	/**
	 * 删除实体对象
	 *
	 * @param entity 实体对象
	 * @return 成功true，失败false
	 */
	@Override
	public boolean delete(User entity) {
		return dao.delete(entity);
	}

	/**
	 * 通过id查询，返回唯一查询对象
	 *
	 * @param id 对象的id
	 * @return 对象T
	 */
	@Override
	public User findByID(Integer id) {
		return dao.findByID(id);
	}

	/**
	 * 保存或更新实体对象
	 *
	 * @param entity 实体对象
	 * @return 成功true，失败false
	 */
	@Override
	public boolean saveOrUpdate(User entity) {
		return dao.saveOrUpdate(entity);
	}

}