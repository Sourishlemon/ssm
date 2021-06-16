package cn.edu.usts.cs2019.dao.base;

/**
 * @program: JavaWebApp
 * @description: DAO基础类
 * @author: Mr.Lu, DCE, USTS.
 * @create: 2020-03-05 14:28
 **/

import java.io.Serializable;

/**
	 * 统一数据访问接口
	 */
	public interface IBaseDao<T, ID extends Serializable> {
		/**
		 * 新增实体对象
		 * @param: entity 实体对象
		 * @return: 成功true，失败false
		 */
		public boolean insert(T entity);

		/**
		 * 更新实体对象
		 * @param: entity
		 * @return: 成功true，失败false
		 */
		public boolean update(T entity);

		/**
		 * 删除实体对象
		 * @param: entity 实体对象
		 * @return: 成功true，失败false
		 */
		public boolean delete(T entity);

		/**
		 * 通过id查询，返回唯一查询对象
		 * @param: id 对象的id
		 * @return: 对象T
		 */
		public T findByID(ID id);

		/**
		 * 保存或更新实体对象
		 * @param: entity 实体对象
		 * @return: 成功true，失败false
		 */
		public boolean saveOrUpdate(T entity);
	}


 
