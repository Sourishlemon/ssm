package cn.itcast.dao;

import cn.itcast.domin.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {
    int deleteByPrimaryKey(Integer empno);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Integer empno);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

    //嵌套查询，员工表和部门表
    List<Emp> selectEmpAndDeptAll();

    //条件查询，姓名（模糊），职位，部门
    List<Emp> selectEmpByParams(Emp emp);

    int deleteBatchEmpByEmpNo(@Param("empnos") Integer[] empnos);
}