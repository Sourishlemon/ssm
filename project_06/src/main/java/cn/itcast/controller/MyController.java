package cn.itcast.controller;

import cn.itcast.domin.Dept;
import cn.itcast.domin.Emp;
import cn.itcast.service.DeptService;
import cn.itcast.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user/emp")
public class MyController {

    @Autowired
    private EmpService empService;

    @Autowired
    private DeptService deptService;

    //员工编号单个删除
    //但若返回的字符串中带有中文字符,则接收方页面将会出现乱码。此时需要使用
    //@RequestMapping 的 produces 属性指定字符集。
    @RequestMapping(value = "deleteEmp"/*,produces = "text/plain;charset=utf-8"*/)
    @ResponseBody
    public String deleteEmp(Integer empno) {
        try {
            return empService.removeEmpByEmpno(empno);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    //查询员工信息，实现分页，模糊查询
    @RequestMapping("empInfos")
    @ResponseBody
    public Map<String, Object> empInfos(Integer page, Integer limit, Emp emp) {
        Map<String, Object> map = null;
        try {
            map = empService.findEmpAndDeptByLazyWithParams(page, limit, emp);
            map.put("code", 0);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 200);
            map.put("msg", "访问数据失败");
        }
        return map;
    }

    //下拉列表部门的查询
    @RequestMapping("deptInfos")
    @ResponseBody
    public List<Dept> deptInfos() {
        try {
            return deptService.findDeptAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //按员工编号批量删除

    @RequestMapping(value = "deleteBatchEmp")
    @ResponseBody
    public String deleteBatchEmp(Integer[] empnos) {
        try {
            return empService.removeBatchEmpByEmpno(empnos);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    //添加
    @RequestMapping("insertEmp")
    @ResponseBody
    public String insertEmp(Emp emp) {

        try {
            return empService.addEmp(emp);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("changeEmp")
    @ResponseBody
    public String changeEmp(Emp emp) {
        try {
            return empService.changeEmp(emp);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}
