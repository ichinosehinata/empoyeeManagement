package com.iweb.controller;

import com.iweb.service.DepartmentService;
import com.iweb.util.Print;
import com.iweb.view.DepartmentView;
import com.iweb.view.MainView;


/**
 * @author ichinose
 * @date 2023/6/12 9:19
 */
public class DepartmentController {
    public static void departmentManagementController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                跳转到添加部门页面
                Print.write("即将跳转到添加部门页面...");
                DepartmentView.addDepartmentView();
                break;
            case "2":
//                跳转到查询部门页面
                Print.write("即将跳转到查询部门页面...");
                DepartmentView.departmentInquiryView();
                break;
            case "3":
//                跳转到删除部门页面
                Print.write("即将跳转到删除部门页面...");
                DepartmentView.deleteDepartmentView();
                break;
            case "4":
//                跳转到修改部门页面
                Print.write("即将跳转到修改部门页面...");
                DepartmentView.updateDepartmentView();
                break;
            case "5":
//                返回上一步
                Print.write("即将返回上一步...");
                MainView.adminLoginSucceedView();
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                DepartmentView.departmentManagementView();
        }
    }

    public static void departmentInquiryController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                查询全部部门的信息
                DepartmentService.queryAllDepartment();
                break;
            case "2":
//                接收用户输入的模糊查询字段
                DepartmentView.departmentFuzzyQueryView();
                break;
            case "3":
//                返回上一步
                Print.write("即将返回上一步...");
                DepartmentView.departmentManagementView();
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                DepartmentView.departmentManagementView();
        }
    }
}
