package com.xx.springboot.controller;

import com.xx.springboot.dao.DepartmentDao;
import com.xx.springboot.dao.EmployeeDao;
import com.xx.springboot.entities.Department;
import com.xx.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;
import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        //放在请求域中
        model.addAttribute("emps",employees);
        return "/emp/list";
    }

    //到员工添加页面
    @GetMapping("/emp")
    public String addEmp(Model model){
        //显示所有部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //员工添加
    //springMVC自动将请求参数和入参的属性一一绑定，要求请求的参数和javabean入参的对象的属性一致
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //保存员工
        employeeDao.save(employee);
        //redirect 表示重定向到一个地址，/代表当前项目路径
        //forward 表示转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditpage(@PathParam("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        //显示所有部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

}
