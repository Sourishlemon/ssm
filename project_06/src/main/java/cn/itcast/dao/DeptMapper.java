package cn.itcast.dao;

import cn.itcast.domin.Dept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeptMapper {
    int deleteByPrimaryKey(Integer deptno);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer deptno);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    @Select("select * from dept")
    List<Dept> selectDeptAll();

}
