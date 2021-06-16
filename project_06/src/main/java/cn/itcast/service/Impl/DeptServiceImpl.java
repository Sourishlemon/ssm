package cn.itcast.service.Impl;

import cn.itcast.dao.DeptMapper;
import cn.itcast.domin.Dept;
import cn.itcast.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;
    @Override
    public List<Dept> findDeptAll() {
        return deptMapper.selectDeptAll();
    }
}