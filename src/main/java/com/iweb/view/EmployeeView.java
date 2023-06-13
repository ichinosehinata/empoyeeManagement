package com.iweb.view;

import com.iweb.controller.AnnouncementController;
import com.iweb.controller.EmployeeController;
import com.iweb.pojo.Announcement;
import com.iweb.pojo.Department;
import com.iweb.pojo.Employee;
import com.iweb.pojo.Post;
import com.iweb.service.AnnouncementService;
import com.iweb.service.EmployeeService;
import com.iweb.util.Print;
import com.iweb.util.StringUtil;

/**
 * @author ichinose
 * @date 2023/6/12 15:47
 */
public class EmployeeView {
    /**
     * 管理员员工管理页面
     */
    public static void employeeManagementView(){
        Print.write("1.添加员工");
        Print.write("2.查询员工");
        Print.write("3.删除员工");
        Print.write("4.修改员工");
        String str=Print.read();
        EmployeeController.employeeManagementController(str);
    }

    public static void addEmployeeView(){
        Print.write("请输入需要添加的员工姓名");
        String name=Print.read();
        Print.write("请输入需要添加的员工身份证号");
        String idNumber=Print.read();
        Print.write("请输入需要添加的员工手机号");
        String phone=Print.read();
        Print.write("请输入需要添加的员工性别");
        String sex=Print.read();
        Print.write("请输入需要添加的员工部门");
        String department=Print.read();
        Print.write("请输入需要添加的员工职位");
        String post=Print.read();
        Employee employee=new Employee(name,idNumber,phone,sex,new Department(department),new Post(post));
        EmployeeService.addEmployee(employee);
    }

    public static void employeeInquiryView(){
        Print.write("1.查询所有员工");
        Print.write("2.模糊查询");
        Print.write("3.返回上一步");
        String str=Print.read();
        EmployeeController.employeeInquiryController(str);
    }
    public static void employeeFuzzyQueryView(){
        Print.write("请输入字符串进行模糊查询");
        String str= Print.read();
        EmployeeService.employeeFuzzyQuery(str);
    }
    public static void deleteEmployeeView(){
        Print.write("请输入需要删除的编号:");
        String str = Print.read();
        EmployeeService.deleteEmployee(str);
    }
    public static void updateEmployeeView(){
        Print.write("1.修改员工姓名");
        Print.write("2.修改员工身份证号");
        Print.write("3.修改员工手机号");
        Print.write("4.修改员工性别");
        Print.write("5.修改员工部门");
        Print.write("6.修改员工职位");
        Print.write("7.返回上一步");
        String str=Print.read();
        EmployeeController.updateEmployeeController(str);
    }
    public static void updateEmployeeByNameView(){
        Print.write("请输入需要修改的编号：");
        String id=Print.read();
        Print.write("请输入修改之后的员工姓名：");
        String name = Print.read();
        Employee employee=new Employee();
        if (StringUtil.isInteger(id)){
            int eid=Integer.parseInt(id);
            employee.setEid(eid);
            employee.setEName(name);
            EmployeeService.updateEmployee(employee);
        }else {
            Print.write("输入的编号中包含不是数字的字符，请重新输入");
            EmployeeView.employeeManagementView();
        }
    }
    public static void updateEmployeeByIdNumberView(){
        Print.write("请输入需要修改的编号：");
        String id=Print.read();
        Print.write("请输入修改之后的员工身份证号：");
        String idNumber = Print.read();
        Employee employee=new Employee();
        if (StringUtil.isInteger(id)){
            int eid=Integer.parseInt(id);
            employee.setEid(eid);
            employee.setEidNumber(idNumber);
            EmployeeService.updateEmployee(employee);
        }else {
            Print.write("输入的编号中包含不是数字的字符，请重新输入");
            EmployeeView.employeeManagementView();
        }
    }
    public static void updateEmployeeByPhoneView(){
        Print.write("请输入需要修改的编号：");
        String id=Print.read();
        Print.write("请输入修改之后的员工手机号：");
        String phone = Print.read();
        Employee employee=new Employee();
        if (StringUtil.isInteger(id)){
            int eid=Integer.parseInt(id);
            employee.setEid(eid);
            employee.setEPhone(phone);
            EmployeeService.updateEmployee(employee);
        }else {
            Print.write("输入的编号中包含不是数字的字符，请重新输入");
            EmployeeView.employeeManagementView();
        }
    }
    public static void updateEmployeeBySexView(){
        Print.write("请输入需要修改的编号：");
        String id=Print.read();
        Print.write("请输入修改之后的员工性别：");
        String sex = Print.read();
        Employee employee=new Employee();
        if (StringUtil.isInteger(id)){
            int eid=Integer.parseInt(id);
            employee.setEid(eid);
            employee.setESex(sex);
            EmployeeService.updateEmployee(employee);
        }else {
            Print.write("输入的编号中包含不是数字的字符，请重新输入");
            EmployeeView.employeeManagementView();
        }
    }
    public static void updateEmployeeByDepartmentView(){
        Print.write("请输入需要修改的编号：");
        String id=Print.read();
        Print.write("请输入修改之后的员工部门：");
        String departmentName = Print.read();
        Employee employee=new Employee();
        if (StringUtil.isInteger(id)){
            int eid=Integer.parseInt(id);
            employee.setEid(eid);
            employee.getDepartment().setDName(departmentName);
            EmployeeService.updateEmployee(employee);
        }else {
            Print.write("输入的编号中包含不是数字的字符，请重新输入");
            EmployeeView.employeeManagementView();
        }
    }
    public static void updateEmployeeByPostView(){
        Print.write("请输入需要修改的编号：");
        String id=Print.read();
        Print.write("请输入修改之后的员工职位：");
        String postName = Print.read();
        Employee employee=new Employee();
        if (StringUtil.isInteger(id)){
            int eid=Integer.parseInt(id);
            employee.setEid(eid);
            employee.getPost().setPName(postName);
            EmployeeService.updateEmployee(employee);
        }else {
            Print.write("输入的编号中包含不是数字的字符，请重新输入");
            EmployeeView.employeeManagementView();
        }
    }
}
