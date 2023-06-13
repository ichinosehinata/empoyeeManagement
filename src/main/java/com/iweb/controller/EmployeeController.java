package com.iweb.controller;

import com.iweb.service.DepartmentService;
import com.iweb.service.EmployeeService;
import com.iweb.util.Print;
import com.iweb.view.AnnouncementView;
import com.iweb.view.DepartmentView;
import com.iweb.view.EmployeeView;
import com.iweb.view.MainView;

/**
 * @author ichinose
 * @date 2023/6/12 9:18
 */
public class EmployeeController {
    public static void employeeManagementController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                跳转到添加员工页面
                Print.write("即将跳转到添加员工页面...");
                EmployeeView.addEmployeeView();
                break;
            case "2":
//                跳转到查询员工页面
                Print.write("即将跳转到查询员工页面...");
                EmployeeView.employeeInquiryView();
                break;
            case "3":
//                跳转到删除员工页面
                Print.write("即将跳转到删除员工页面...");
                EmployeeView.deleteEmployeeView();
                break;
            case "4":
//                跳转到修改员工页面
                Print.write("即将跳转到修改员工页面...");
                EmployeeView.updateEmployeeView();
                break;
            case "5":
//                返回上一步
                Print.write("即将返回上一步...");
                MainView.adminLoginSucceedView();
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                EmployeeView.employeeManagementView();
        }
    }

    public static void employeeInquiryController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                查询全部员工的信息
                EmployeeService.queryAllEmployee();
                break;
            case "2":
//                接收用户输入的模糊查询字段
                EmployeeView.employeeFuzzyQueryView();
                break;
            case "3":
//                返回上一步
                Print.write("即将返回上一步...");
                EmployeeView.employeeManagementView();
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                EmployeeView.employeeManagementView();
        }
    }

    public static void updateEmployeeController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                修改员工姓名页面
                EmployeeView.updateEmployeeByNameView();
                break;
            case "2":
//                修改员工身份证号页面
                EmployeeView.updateEmployeeByIdNumberView();
                break;
            case "3":
//                修改员工手机号页面
                EmployeeView.updateEmployeeByPhoneView();
                break;
            case "4":
//                修改员工性别页面
                EmployeeView.updateEmployeeBySexView();
                break;
            case "5":
//                修改员工部门页面
                EmployeeView.updateEmployeeByDepartmentView();
                break;
            case "6":
//                修改员工职位页面
                EmployeeView.updateEmployeeByPostView();
                break;
            case "7":
//                返回上一步
                Print.write("即将返回上一步...");
                EmployeeView.employeeManagementView();
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                EmployeeView.employeeManagementView();
        }
    }
}
