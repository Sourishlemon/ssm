package cn.itcast.service.Impl;

import cn.itcast.dao.EmpMapper;
import cn.itcast.domin.Emp;
import cn.itcast.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public String removeEmpByEmpno(Integer empno) {

        if(empMapper.deleteByPrimaryKey(empno)>0){
            return "success";
        }else{
            return "fail";
        }
    }

    @Override
    public Map<String, Object> findEmpAndDeptByLazyWithParams(Integer page, Integer limit, Emp emp) {
        /*开启分页查询*/
        PageHelper.startPage(page,limit);

        List<Emp> list = empMapper.selectEmpByParams(emp);

        /*封装数据*/
        PageInfo pageInfo=new PageInfo(list);

        Map<String, Object> map=new HashMap<>();

        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());

        return map;
    }

    @Override
    public String removeBatchEmpByEmpno(Integer[] empnos) {
        if(empMapper.deleteBatchEmpByEmpNo(empnos)>0){
            return "success";
        }else{
            return "fail";
        }
    }

    @Override
    public String addEmp(Emp emp) {
        if(empMapper.insertSelective(emp)>0){
            return "success";
        }else{
            return "fail";
        }
    }

    @Override
    public String changeEmp(Emp emp) {
        if(empMapper.updateByPrimaryKeySelective(emp)>0){
            return "success";
        }else{
            return "fail";
        }
    }
}