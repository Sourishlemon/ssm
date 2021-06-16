package cn.itcast.service;

import cn.itcast.domin.Emp;

import java.util.Map;

public interface EmpService {

    //删除
    String removeEmpByEmpno(Integer empno);

    //根据条件进行分页查询员工和部门数据
    Map<String, Object> findEmpAndDeptByLazyWithParams(Integer page, Integer limit, Emp emp);

    String removeBatchEmpByEmpno(Integer[] empnos);

    String addEmp(Emp emp);

    String changeEmp(Emp emp);
}
